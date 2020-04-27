<%@ page language="java" import="java.util.*"
         pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
    <link rel="stylesheet"
          href="${path}/res/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${path}/res/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="${path}/res/css/one-css/user.css" media="all"/>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>个人信息</legend>
    <div class="layui-field-box">
        <form class="layui-form">
            <input type="hidden" name="id" value="${sysUser.id}">
            <div class="user_left">
                <div class="layui-form-item">
                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-block">
                        <input type="text" name="loginName" value="${sysUser.loginName }" disabled class="layui-input layui-disabled">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">真实姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" value="${sysUser.name }" placeholder="请输入真实姓名" lay-verify="required" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-block">
                        <input type="tel" name="mobile" value="${sysUser.mobile }" placeholder="请输入手机号码" lay-verify="required|phone" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" name="email" value="${sysUser.email }" placeholder="请输入邮箱" lay-verify="required|email" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="user_right">
                <button type="button" class="layui-btn"
                        id="test1">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
                <input type="file" name="file" class="layui-upload-file">
                <p>请选择一张图片上传作为头像</p>
                <img src="${path }${sysUser.img}" class="layui-circle" id="imgg" width="200px" height="200px"/>
                <input type="hidden" id="img" name="img" value="${sysUser.img }">
            </div>
            <div class="layui-form-item" style="margin-left:5%;">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="changeUser"><i class="fa fa-save"></i>&nbsp;保存信息</button>
                    <!-- <button type="reset" class="layui-
                    btn layui-btn-primary">重置</button> -->
                </div>
            </div>
        </form>
    </div>
</fieldset>
<script type="text/javascript"
        src="${path}/res/layui/layui.js"></script>
<script type="text/javascript" src="${path}/res/js/custom-js/userInfo.js"></script>
<script type="text/javascript">
    var path = "${path}";
</script>
</body>
</html>