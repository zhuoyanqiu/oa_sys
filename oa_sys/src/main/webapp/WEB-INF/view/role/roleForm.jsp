<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/21
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*"
         pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <link rel="Shortcut Icon" href="/favicon.ico"/>
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="${path}/res/layui/css/layui.css" media="all">
</head>
<style type="text/css" media="screen">
    .one-tj {
        margin-left: 10px;
        margin-right: 25px;
    }

    .one-btn-btn {
        width: 230px;
        padding-top: 10px;
        margin: 0 auto;
    }

    .layui-form .layui-form-label {
        padding-left: 15px;
        color: #666666;
    }

    .layui-form .layui-input-block {
        width: 300px;
    }

    .layui-form .layui-input-block input, .layui-form .layui-input-block textarea {
        color: #aaaaaa;
    }
</style>
<body>
<div class="laoneluid">
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend><a name="methodRender">角色添加</a></legend>
    </fieldset>
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" value="" class="layui-input linksName" lay-verify="required"
                       placeholder="请输入角色名称">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色状态</label>
            <div class="layui-input-block">
                <select id="useableId" name="useable" lay-verify="required">
                    <option value="0">可用</option>
                    <option value="1">禁用</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item one-btn-btn">
            <button class="layui-btn one-tj" lay-submit lay-filter="add">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>
<!-- 加载js文件 -->
<script type="text/javascript" src="${path
}/res/layui/layui.js"></script>
<script type="text/javascript">
    var path = "${path}";
    layui.use(['form', 'layer', 'jquery'], function () {
        var $ = layui.$,
            form = layui.form,
            layer = layui.layer;
        form.on('submit(add)', function (data) {
            var ajaxReturnData;
            //登陆验证
            $.ajax({
                url: path + '/sysRole/save',
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
                parent.layer.close(index); //再执行关闭
                //刷新父页面
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
