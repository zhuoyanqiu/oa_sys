<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <link rel="Shortcut Icon" href="/favicon.ico"/>
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="${path
}/res/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${path}/res/font-awesome-4.7.0/css/font-awesome.css"/>
    <%-- <link rel="stylesheet" type="text/css" href="${path
    }/common/css/gobal.css" media="all">
    <link rel="stylesheet" type="text/css" href="${path
    }/common/css/animate.css" media="all">
    <link rel="stylesheet" type="text/css" href="${path
    }/system/css/common.css" media="all">
    <link rel="stylesheet" type="text/css" href="${path
    }/system/css/user.css" media="all"> --%>
    <!-- ztree -->
    <script src="${path}/res/zTree/js/jquery-1.4.4.min.js">
    </script>
    <script src="${path}/res/zTree/js/jquery.ztree.all.js">
    </script>
    <script src="${path}/res/zTree/js/jquery.ztree.core.js">
    </script>
    <script type="text/javascript"
            src="${path}/res/zTree/js/jquery.ztree.excheck.js"></script>
    <link href="${path}/res/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="">
    <div class="layui-row">
        <div class="layui-col-md9">
            <div class="layui-fluid ">
                <div class="layui-row animated bounceIn">
                    <div class="layui-col-lg12 layui-col-md12 layui-col-sm12 layui-col-xs12">
                        <fieldset class="layui-elem-field layui-field-title site-title">
                            <legend><a name="color-design">角色管理</a></legend>
                        </fieldset>
                        <div class="layui-btn-group one-group" id="one_group">
                            <button class="layui-btn" data-type="add"><i class="layui-icon">&#xe61f;</i><cite>增加角色
                            </cite></button>
                            <!-- <button class="layui-btn
                            layui-btn-normal" data-type="edit"><i class="layui-
                            icon">&#xe642;</i><cite>修改角色</cite></button>
                            <button class="layui-btn layui-
                            btn-danger" data-type="del"><i class="layui-icon">&#xe640;
                            </i><cite>删除角色</cite></button> -->
                        </div>
                    </div>
                    <div class="layui-col-lg10 layui-col-md10 layui-col-sm12 layui-col-xs12">
                        <div class="role-tables">
                            <table id="roleTables" lay-filter="roleTables"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-fluid">
                <fieldset class="layui-elem-field layui-field-title site-title">
                    <legend><a name="methodRender">
                        <div class="layui-inline"
                             id="roleName"></div>
                        <div class="layui-inline">权限编辑
                        </div>
                    </a></legend>
                </fieldset>
                <a class="layui-btn savepermisson_btn" data-type="savepermisson"><i class="layui-icon">&#xe615;</i>保存权限</a>
                <div class="zTreeDemoBackground left">
                    <div id="menuTreeId"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="rolebar">
    <a class="layui-btn layui-btn-xs" lay-event="edit"> <i class="layui-icon">&#xe642;</i>编辑</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="authorize"><i class="layui-icon">&#xe620;</i>权限设置</a>
    {{# if(d.useable === '0'){ }}
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="disable"><i class="fa fa-ban"></i>&nbsp;禁用</a>
    {{# } else { }}
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="able"><i class="fa fa-circle-o"></i>&nbsp;置为可用</a>
    {{# } }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>
<script type="text/html" id="status">
    {{# if(d.useable === undefined){ }}
    未知
    {{# } else { }}
    {{# if(d.useable === '0'){ }}
    可用
    {{# } else { }}
    禁用
    {{# } }}
    {{# } }}
</script>
<!-- 加载js文件 -->
<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<%-- <script type="text/javascript" src="${path
}/system/js/common.js"></script> --%>
<script type="text/javascript" src="${path }/res/js/custom-js/role.js"></script>
<script type="text/javascript">
    var path = "${path}";
    var roleId = "";
</script>
</body>
</html>