<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2020/4/21
Time: 20:35
To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>菜单管理</title>
<!-- load css -->
<link rel="stylesheet" type="text/css"
href="${path}/res/layui/css/layui.css" media="all" >
<link rel="stylesheet" href="${path}/res/font-awesome-4.7.0/css/font-awesome.css" />
</head>
<body>
<div class="layui-fluid">
<!-- <div class="layui-container"> -->
<div class="">
<div class="layui-row">
<div class="layui-col-md2">
<!-- 左边 开始-->
<div id="menuTreeId"></div>
<!-- 左边 结束-->
</div>
<div class="layui-col-md10">
<!-- 右边 开始 -->
<blockquote class="layui-elem-quote one-btn">
<div class="layui-inline">
<div class="layui-input-inline">
<input type="text" name="search" value=""
id="search_input" placeholder="请输入菜单名称"
class="layui-input ">
</div>
<a class="layui-btn search_btn" data-type="getSelect"><i class="layui-icon">&#xe615;</i>查询</a>
</div>
<div class="layui-inline">
<a class="layui-btn layui-bg-pale add_menu_btn" data-type="addLink"><i class="layui-icon">&#xe608;</i>添加菜单</a>
</div>
<div class="layui-inline">
<a class="layui-btn layui-btn-danger" data-type="delLink"><i class="layui-icon">&#xe640;</i>批量删除</a>
</div>
</blockquote>
<!-- 友链数据列表 -->
<div class="flinkTable">
<table class="layui-table" id="flinklist" lay-filter="flinkTables"></table>
</div>
<!-- 右边 结束-->
</div>
</div>
</div>
</div>
<script type="text/html" id="flinkbar">
<a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
<a class="layui-btn layui-btn-danger layui-btn-xs"
lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
<a class="layui-btn layui-btn-normal layui-btn-xs"
lay-event="add"><i class="layui-icon">&#xe608;</i>添加下一节点</a>
</script>
<script type="text/html" id="type">
{{# if(d.type === '0'){ }}
目录菜单
{{# } else { }}
权限菜单
{{# } }}
</script>
<script type="text/html" id="linkTpl">
<i class="fa {{d.icon}}"></i>
</script>
<!-- 加载js文件 -->
<script src="${path}/res/layui/layui.js"></script>
<script src="${path}/res/js/custom-js/menu.js">
</script>
<script type="text/javascript">
var path = "${path}";
</script>
</body>
</html>
