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
        <legend><a name="methodRender">添加部门</a></legend>
    </fieldset>
    <form class="layui-form linksAdd">
        <div class="layui-form-item">
            <label class="layui-form-label">父级机构</label>
            <div class="layui-input-block">
                <input type="hidden" id="parentdepartmentid" name="parentdepartmentid" value="">
                <input type="text" id="tree" lay-filter="tree" class="layui-input" name="title">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">机构名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" value="" class="layui-input" lay-verify="required" placeholder="请输入机构名称"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述信息</label>
            <div class="layui-input-block">
                <input type="text" name="description" value="" class="layui-input" lay-verify="required"
                       placeholder="请输入机构的描述信息"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">显示顺序</label>
            <div class="layui-input-block">
                <input type="text" name="sort" value="" class="layui-input" lay-verify="required" placeholder="请输入排序"/>
            </div>
        </div>
        <%-- <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
        <input type="text" name="status"
        value="${dict.status }" class="layui-input" lay-
        verify="required" placeholder="请输入状态" />
        </div>
        </div> --%>
        <div class="layui-form-item">
            <button class="layui-btn layui-block" lay-filter="addLink" lay-submit>提交</button>
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
        var parentdepartmentid = "12";
        layui.use(['form', 'layer', 'jquery', 'treeSelect'], function () {
            var $ = layui.$,
                form = layui.form,
                treeSelect = layui.treeSelect,
                layer = layui.layer;
            treeSelect.render({
                // 选择器
                elem: '#tree',
                // 数据
                data: path + '/sysDepartment/treeDataJson?id=0',
                // 异步加载方式：get/post，默认get
                type: 'get',
                // 占位符
                placeholder: '请选择部门',
                // 是否开启搜索功能：true/false，默认false
                search: true,
                // 点击回调
                click: function (d) {
                    $("#parentdepartmentid").val(d.current.id);
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
            //select赋值
            //$("#sel").find("option[value='" + roleId +"']").attr("selected", true);
            form.render('select');
        });
    </script>
    <script type="text/javascript" src="${path}/res/js/custom-js/departmentList.js"></script>
    <script type="text/javascript">
        var path = "${path}";
    </script>
</body>
</html>