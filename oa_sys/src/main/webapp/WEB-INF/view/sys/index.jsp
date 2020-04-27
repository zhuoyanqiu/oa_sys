<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/22
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>OA后台管理系统</title>
    <link rel="shortcut icon" href="${path }/res/images/Ologo.ico" type="image/x-icon" />

    <link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all" >
    <link rel="stylesheet" href="${path}/res/font-awesome-4.7.0/css/font-awesome.css" />
    <link rel="stylesheet" href="${path}/res/css/one-css/layout.css" media="all" />

</head>

<body>
<div class="layui-layout layui-layout-admin beg-layout-container">
    <div class="layui-header beg-layout-header">
        <div class="beg-layout-main beg-layout-logo">
            <a href="#" target="_blank" class="one-logo">OA系统</a>
        </div>
        <div class="beg-layout-main beg-layout-side-toggle" style="border-radius: 10%;">
            <i class="fa fa-bars" aria-hidden="true"></i>
        </div>
        <!--一级菜单-->
        <div class="beg-layout-main beg-layout-menu" id="menu">
            <ul class="layui-nav beg-layout-nav" lay-filter="">

            </ul>
        </div>
        <div class="beg-layout-main beg-layout-panel">
            <ul class="layui-nav beg-layout-nav" lay-filter="user">
                <li class="layui-nav-item">
                    <a href="javascript:;" data-tab="true" class="admin-side-full">
                        <i class="fa fa-arrows-alt" aria-hidden="true"></i>
                        全屏
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" data-tab="true" class="clearCache">
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                        清除缓存
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" data-tab="true" class="showNotice" id="showNotice">
                        <i class="fa fa-bullhorn" aria-hidden="true"></i>
                        系统公告
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" class="beg-layou-head">
                        <img src="${path }<shiro:principal property='img'/>" class="layui-nav-img">
                        <span><%-- <shiro:principal property='loginName'/> --%></span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-tab="true" data-url='${path}/sysUser/personInfo'>
                                <i class="fa fa-user-circle" aria-hidden="true"></i>
                                <cite>个人信息</cite>
                            </a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-tab="true" data-url="${path }/user/pwd.do">
                                <i class="fa fa-gear" aria-hidden="true"></i>
                                <cite>修改密码</cite>
                            </a>
                        </dd>
                        <dd>
                            <a id="logout" href="javascript:;">
                                <i class="fa fa-sign-out" aria-hidden="true"></i>
                                <cite>注销</cite>
                            </a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!--侧边导航栏-->
    <div class="layui-side beg-layout-side" >
        <div class="layui-side-scroll">
            <!-- 管理员信息      -->
            <div class="user-info">
                <div class="photo">
                    <img src="${path }<shiro:principal property='img'/>" alt="">
                </div>
                <p><shiro:principal property='loginName'/>您好！欢迎登录</p>
            </div>
            <ul class="layui-nav layui-nav-tree"  lay-filter="side" id="side">
            </ul>

        </div>

    </div>
    <!--内容区域-->
    <div class="layui-body beg-layout-body">
        <div class="layui-tab layui-tab-brief layout-nav-card" lay-filter="layout-tab" style="border: 0; margin: 0;box-shadow: none; height: 100%;">
            <ul class="layui-tab-title">
                <li class="layui-this">
                    <a href="javascript:;">
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <cite>后台首页</cite>
                    </a>
                </li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe src="${path }/login/main"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!--页脚-->
    <div class="layui-footer beg-layout-footer">

    </div>
</div>

<script src="${path}/res/layui_menu/layui.js"></script>
<script src="${path}/res/js/layout.js"></script>
<script type="text/javascript">
    var path = "${path}";
</script>

</body>
</html>
