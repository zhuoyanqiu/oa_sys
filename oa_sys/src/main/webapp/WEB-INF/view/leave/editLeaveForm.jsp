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
    <legend><a name="methodRender">编辑请假单</a></legend>
</fieldset>
<form class="layui-form leaveEditForm" lay-filter="layui-form">

    <input type="hidden" name="taskId" value="${leave.taskId}"/>
    <input type="hidden" name="pass" value="true"/>

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
        <button class="layui-btn layui-block" lay-filter="editLeave" lay-submit>提交</button>
    </div>
</form>
<script type="text/javascript" src="${path}/res/layui/layui.js"></script>
<script type="text/javascript" src="${path}/res/js/custom-js/leave.js"></script>
<script type="text/javascript">
    var path = "${path}";

    layui.use('form', function(){
        var $ = layui.$;
        var form = layui.form;

        form.val('layui-form', {
            "title": "${leave.title}" // "name": "value"
            ,"reason": "${leave.reason}"
            ,"days": ${leave.days}
        });

        form.on('submit(editLeave)',function(data){
            var ajaxReturnData;
            //登陆验证
            $.ajax({
                url: path + '/leave/leaveEditSave',
                type: 'post',
                async: false,
                data: data.field,
                success: function (data) {
                    ajaxReturnData = data;
                }
            });
            //结果回应
            if (ajaxReturnData == '0') {
                top.layer.msg('保存成功', {icon: 1});
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭                        //刷新父页面
                parent.location.reload();
            } else {
                top.layer.msg('保存失败', {icon: 5});
            }
            return false;
        });



    });

</script>
</body>
</html>