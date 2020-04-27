<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all" >
    <link rel="stylesheet" href="${path}/res/css/public.css" media="all" />
</head>
<body>
<table class="layui-table" id="list" lay-filter="tables"></table>
<script type="text/html" id="flinkbar">
    {{#  if(d.taskName === undefined){ }}
    未知
    {{#  } else { }}
    {{#  if(d.taskName === '调整申请'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="repeatLeaveForm"><i class="layui-icon">&#xe642;</i>调整请假单</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="endFlow"><i class="layui-icon">&#xe640;</i>结束流程</a>
    {{#  } else if(d.taskName === '请假通过'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="successFinishflow"><i class="layui-icon">&#xe642;</i>审核通过，结束流程</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-xs" lay-event="pass"><i class="layui-icon">&#xe642;</i>通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="nopass"><i class="layui-icon">&#xe640;</i>不通过</a>
    {{#  } }}
    {{#  } }}


</script>
<script type="text/html" id="status">
    {{#  if(d.status === undefined){ }}
    未知
    {{#  } else { }}
    {{#  if(d.status === '0'){ }}
    可用
    {{#  } else { }}
    禁用
    {{#  } }}
    {{#  } }}
</script>

<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<script type="text/javascript" src="${path}/res/js/custom-js/leave.js"></script>
<script type="text/javascript">
    var path = "${path}";
</script>

</body>
</html>
