<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/13
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${path}/res/layui/css/layui.css" edia="all">
    <link rel="stylesheet" href="${path}/res/css/public.css" media="all"/>
</head>
<body>

<blockquote class="layui-elem-quote quoteBox">
    <form class="layui-form">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" class="layui-input login_name" id="loginName_select" placeholder="请按登录名搜索"
                       lay-filter="loginName_select"/>
            </div>
            <div class="layui-input-inline" style="width:87px">
                <select name="sex" lay-verify="required" class="sex" lay-filter="sex_select">
                    <option value="">性别</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <%--            <a class="layui-btn search_btn" data-type="reload"><i class="layui-icon">&#xe615;</i>搜索</a>--%>
        </div>
        <shiro:hasPermission name="user:add">
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal addLink_btn"><i class="layui-icon">&#xe608;</i>添加用户</a>
            </div>
        </shiro:hasPermission>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn"><i class="layui-icon">&#xe640;</i>批量删除</a>
        </div>
    </form>
</blockquote>

<table class="layui-table" id="list" lay-filter="tables"></table>
<script type="text/html" id="flinkbar">
    <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i></a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
</script>
<%--<script type="text/html" id="statusTemp">
     if(${s}=="0"){
    <input type="checkbox" name="status" lay-skin="switch" checked lay-text="可用|禁用" value= "0" lay-filter="status"/>

</script>--%>
<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<script type="text/javascript" src="${path}/res/js/custom-js/userList.js"></script>
<script type="text/javascript">
    const path = "${path}";
</script>
</body>
</html>
