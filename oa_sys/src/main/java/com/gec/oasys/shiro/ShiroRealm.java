package com.gec.oasys.shiro;

import com.gec.oasys.dao.SysMenuMapper;
import com.gec.oasys.dao.SysUserMapper;
import com.gec.oasys.pojo.SysMenu;
import com.gec.oasys.pojo.SysUser;
import com.gec.oasys.service.SysMenuService;
import com.gec.oasys.service.SysUserService;
import com.sun.istack.internal.NotNull;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.NestedServletException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 权限分配
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Integer roleId = sysUser.getRoleId();
        List<SysMenu> sysMenuList = sysMenuService.queryMenuByRoleId(roleId);
        Set<String> permissions = new HashSet<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getPermission() != null && !"".equals(sysMenu.getPermission())) {
                permissions.add(sysMenu.getPermission());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        SysUser sysUser = new SysUser();
        sysUser.setLoginName(username);
        sysUser.setPwd(password);

        SysUser resultSysUser = sysUserService.querySysUserByLoginNameAndPwd(sysUser);

        if (resultSysUser == null || "".equals(resultSysUser.getLoginName())) {
            throw new IncorrectCredentialsException("账号或者密码错误！");
        }

        if ("1".equals(resultSysUser.getStatus())) {
            throw new LockedAccountException("用户被禁用！");

        }

        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {

            return new SimpleAuthenticationInfo(resultSysUser, password, resultSysUser.getLoginName() + "" + resultSysUser.getPwd());
        }else {
            throw new AuthenticationException("账号、密码不能为空");
        }
    }
}