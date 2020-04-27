<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all" >
    <link rel="stylesheet" href="${path}/res/css/public.css" media="all" />
</head>
<body class="childrenBody">

<fieldset class="layui-elem-field layui-field-title site-title">
    <legend><a name="methodRender">填写请假单</a></legend>
</fieldset>
<form class="layui-form linksAdd">


    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
            <input type="text" name="title" value="" class="layui-input" lay-verify="required" placeholder="请输入标题" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请假原因</label>
        <div class="layui-input-block">
            <input type="text" name="reason" value="" class="layui-input" lay-verify="required" placeholder="请输入原因" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请假天数</label>
        <div class="layui-input-block">
            <input type="text" name="days" value="" class="layui-input" lay-verify="required" placeholder="请输入天数" />
        </div>
    </div>

    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="addLeave" lay-submit>提交</button>
    </div>
</form>
<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<script type="text/javascript" src="${path}/res/js/custom-js/leave.js"></script>
<script type="text/javascript">
    var path = "${path}";
</script>
</body>
</html>