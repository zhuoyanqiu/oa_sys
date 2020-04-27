<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${path}/res/css/public.css" media="all"/>
    <title></title>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field layui-field-title site-title">
    <legend><a name="methodRender">编辑用户</a></legend>
</fieldset>
<form class="layui-form linksEdit">
    <input type="hidden" id="id" name="id" value="${sysUser.id}">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="loginName" value="${sysUser.loginName}" class="layui-input" lay-verify="required" placeholder="请输入用户名"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="text" name="pwd" value="${sysUser.pwd}" class="layui-input" lay-verify="required" placeholder="请输入密码"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上传图片</label>
        <div class="layui-input-block">
            <input type="file" id="img" name="img" class="layui-upload-file" value="${sysUser.img }">
<%--            <img src="${path }${sysUser.img}" class="layui-circle" id="imgg" width="200px" height="200px"/>--%>
            <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
            <input type="file" name="file" class="layui-upload-file">
            <input type="hidden" id="img" name="img" value="${sysUser.img }">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" value="${sysUser.email}" class="layui-input" lay-verify="required" placeholder="请输入邮箱"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男">
            <input type="radio" name="sex" value="女" title="女" checked>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系方式</label>
        <div class="layui-input-block">
            <input type="text" name="mobile" value="${sysUser.mobile}" class="layui-input" lay-verify="required" placeholder="请输入联系方式"/>
        </div>
    </div>
  <%--  <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <input type="checkbox" name="switch" lay-skin="switch">
            <input type="radio" name="status" value="0" title="可用">
            <input type="radio" name="status" value="1" title="不可用" checked>
        </div>
    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" value="${sysUser.name}" class="layui-input" lay-verify="required" placeholder="请输入真实姓名"/>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">角色名</label>
        <div class="layui-input-block">
            <select name="roleId" id="roleId_select" lay-verify="required">
                <c:forEach items="${sysRoleList}" var="sysRole">
                    <option value="${sysRole.id}">${sysRole.name}</option>
                </c:forEach>

            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所属机构</label>
        <div class="layui-input-block">
            <input type="hidden" id="orgId" name="orgId" value="">
            <input type="text" id="tree" lay-filter="tree" class="layui-input" name="title">
        </div>
    </div>

    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="editLink" lay-submit>提交</button>
    </div>
</form>
<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base: '../res/js/'
    }).extend({
        treeSelect: 'treeSelect'
    });
    var path = "${path}";
    var parentdepartmentid = "${sysUser.orgId}";
    layui.use(['form', 'layer', 'jquery', 'treeSelect'], function () {
        var $ = layui.$,
            form = layui.form,
            treeSelect = layui.treeSelect,
            layer = layui.layer;
        treeSelect.render({
            // 选择器
            elem: '#tree',
            // 数据
            data: path + '/sysDepartment/treeDataJson?id=0',
            // 异步加载方式：get/post，默认get
            type: 'get',
            // 占位符
            placeholder: '请选择部门',
            // 是否开启搜索功能：true/false，默认false
            search: true,
            // 点击回调
            click: function (d) {
                $("#parentdepartmentid").val(d.current.id);
                console.log(d.current.id);
            },
            // 加载完成后的回调函数
            success: function (d) {
                console.log(d);
                // 选中节点，根据id筛选
                treeSelect.checkNode('tree', parentdepartmentid);
                // 获取zTree对象，可以调用zTree方法
                var treeObj = treeSelect.zTree('tree');
                console.log(treeObj);
                // 刷新树结构
                treeSelect.refresh();
            }
        });
        //select赋值
        $("#roleId_select").find("option[value='" + ${sysUser.roleId} +"']").attr("selected", true);
        form.render('select');
    });
</script>
<script type="text/javascript" src="${path}/res/js/custom-js/userList.js"></script>
<script type="text/javascript">
    var path = "${path}";
</script>
</body>
</html>