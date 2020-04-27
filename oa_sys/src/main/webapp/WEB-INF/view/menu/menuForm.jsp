<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/inc/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${path}/res/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${path}/res/css/public.css" media="all"/>
    <title></title>
</head>
<body class="childrenBody">
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend><a name="methodRender">添加菜单</a></legend>
    </fieldset>
    <form class="layui-form linksAdd">

    <div class="layui-form-item">
    <label class="layui-form-label">菜单名</label>
    <div class="layui-input-block">
    <input type="text" name="title" value="" class="layui-input linksName" lay-verify="required" placeholder="请输入菜单名">
    </div>
    </div>

    <div class="layui-form-item">
    <label class="layui-form-label">父级菜单</label>
    <div class="layui-input-block">
    <input type="hidden" id="pid" name="pid" value="">
    <input type="text" id="tree" lay-filter="tree" class="layui-input" name="title">
    </div>
    </div>

    <div class="layui-form-item">
    <label class="layui-form-label">图标</label>
    <div class="layui-input-block">
    <input type="text" name="icon" value="" class="layui-input linksName" lay-verify="required" placeholder="请输入图标">
    </div>
    </div>

    <div class="layui-form-item">
    <label class="layui-form-label">路径</label>
    <div class="layui-input-block">
    <input type="text" name="href" value="" class="layui-input linksName" lay-verify="required" placeholder="请输入路径">
    </div>
    </div>

    <div class="layui-form-item">
    <label class="layui-form-label">排序</label>
    <div class="layui-input-block">
    <input type="text" name="sort" value="" class="layui-input linksName" lay-verify="required" placeholder="请输入排序">
    </div>
    </div>

    <div class="layui-form-item">
    <label class="layui-form-label">菜单类型</label>
    <div class="layui-input-block">
    <select id="useableId" name="type" lay-verify="required">
    <option value="0">目录菜单</option>
    <option value="1">权限菜单</option>
    </select>
    </div>
    </div>

    <div class="layui-form-item">
    <label class="layui-form-label">权限标识</label>
    <div class="layui-input-block">
    <input type="text" name="permission" value="" class="layui-input linksName" lay-verify="required" placeholder="请输入权限标识">
    </div>
    </div>

        <div class="layui-form-item">
            <button class="layui-btn layui-block" lay-filter="addLink" lay-submit>提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
    <script type="text/javascript" src="${path}/res/layui/layui.js"></script>
    <script type="text/javascript">
        layui.config({
            base: '../res/js/'
        }).extend({
            treeSelect: 'treeSelect'
        });
        var path = "${path}";
        var parentdepartmentid = "1";
        layui.use(['form', 'layer', 'jquery', 'treeSelect'], function () {
            var $ = layui.$,
                form = layui.form,
                treeSelect = layui.treeSelect,
                layer = layui.layer;
            treeSelect.render({
                // 选择器
                elem: '#tree',
                // 数据
                data: path + '/sysMenu/treeDataJson?id=0',
                // 异步加载方式：get/post，默认get
                type: 'get',

                id: 'tree',
                // 占位符
                placeholder: '请选择父菜单',
                // 是否开启搜索功能：true/false，默认false
                search: true,
                // 点击回调
                click: function (d) {
                    $("#pid").val(d.current.id);
                    console.log(d.current.id);
                },
                // 加载完成后的回调函数
                success: function (d) {
                    console.log(d);
                    // 选中节点，根据id筛选
                    treeSelect.checkNode('tree', parentdepartmentid);
                    // 获取zTree对象，可以调用zTree方法
                    var treeObj = treeSelect.zTree('tree');
                    console.log(treeObj);
                    // 刷新树结构
                    treeSelect.refresh();
                }
            });
            // select赋值
            // $("#sel").find("option[value='" + roleId +"']").attr("selected", true);
            form.render('select');
        });
    </script>
    <script type="text/javascript" src="${path}/res/js/custom-js/menu.js"></script>
    <script type="text/javascript">
        var path = "${path}";
    </script>
</body>
</html>