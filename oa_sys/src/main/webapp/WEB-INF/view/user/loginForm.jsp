<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/17
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="${path }/res/images/Ologo.ico" type="image/x-icon" />
    <title>OA后台管理系统登录</title>
    <link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all" >
    <link rel="stylesheet" href="${path}/res/font-awesome-4.7.0/css/font-awesome.css" />
    <link rel="stylesheet" href="${path}/res/css/one-css/login.css" media="all" />
    <script language="JavaScript">
        if (window != top)
            top.location.href = location.href;
    </script>
</head>

<body class="beg-login-bg">
<div class="beg-login-box">
    <header>
        <h1>OA后台管理系统登录</h1>
    </header>
    <div class="beg-login-main">
        <form action="" class="layui-form" method="post">
            <div class="avatar layui-anim-upbit">
                <img src="${path}/res/images/login/admin1.jpg" width="100px" alt="">
            </div>
            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe612;</i>
                </label>
                <input type="text" name="loginName" lay-verify="required" autocomplete="off" placeholder="这里输入登录名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe642;</i>
                </label>
                <input type="password" name="pwd" required lay-verify="required|password" autocomplete="off" placeholder="这里输入密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <!--<div class="beg-pull-left beg-login-remember">
                    <label>记住帐号？</label>
                    <input type="checkbox" name="rememberMe" value="true" lay-skin="switch" checked title="记住帐号">
                </div>-->
                <div class="beg-pull-right">
                    <button class="layui-btn " lay-submit lay-filter="submit">
                        <i class="layui-icon">&#xe609;</i> 登录
                    </button>
                </div>
                <div class="beg-clear"></div>
            </div>
        </form>
    </div>
    <footer>
    </footer>
</div>

<script src="${path}/res/layui/layui.js"></script>
<script src="${path}/res/js/custom-js/login.js"></script>
<script type="text/javascript">
    var path = "${path}";
</script>
</body>
</html>