layui.config({
    base: "../res/js/"
}).use(['form','layer','table','treeSelect','tree'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer :
            top.layer,
        $ = layui.jquery,
        table = layui.table,
        tree=layui.tree,
        treeSelect=layui.treeSelect;
//部门列表
    var tableIns = table.render({
        elem: '#flinklist',
        url : path + '/sysMenu/getMenuDataByPage',
        page: true,
        limit:15,
        limits: [10, 15, 20, 25],
        id : "flinklist",
        cols : [[
            {
                type : "checkbox",
                fixed : "left",
                width : 50
            },
            {
                field : 'id',
                title : '菜单ID',
                width : 100,
                align : 'center',
                sort : true
            },
            {
                field : 'title',
                title : '菜单名',
                width :200,
                align : 'center'
            },
            {
                field : 'icon',
                title : '图标',
                width :80,
                align : 'center',
                templet: '#linkTpl'
            },
            {
                field : 'href',
                title : '路径',
                width : 300,
                align : 'center'
            },
            {
                field : 'sort',
                title : '排序',
                align : 'center'
            },
            {
                field : 'type',
                title : '菜单类型',
                align : 'center',
                templet: '#type'
            },
            {
                field : 'permission',
                title : '权限标识',
                align : 'center'
            },
            {
                title : '操作',
                width : 360,
                fixed : "right",
                align : "center",
                templet : '#flinkbar'
            }
        ]]
    });
//搜索
    $(".search_btn").on("click",function(){
        console.log($("#search_input").val());
        table.reload("flinklist",{
            where: {
                title:$("#search_input").val()
            }
        })
    });


    //添加菜单
    $(".add_menu_btn").on("click",function(){
        layer.open({
            title: "添加菜单",
            type: 2,
            skin:'',
            offset: ['85px', '530px'],
            area: ['520px', '700px'],
            content: path + "/sysMenu/toMenuForm"
        })
    });




//定义一个方法，此方法用于显示菜单树形结构
    var renderTree= function() {
        tree.render({
            elem: '#menuTreeId'
            ,data: getMenuTreeData()
            ,edit: ['add', 'update', 'del']
            ,id: 'menuTreeId'
            ,click: function(obj){
                console.log(obj.data.id); //得到当前点击的节点数据
                table.reload("flinklist",{
                    where: {
                        id:obj.data.id
                    }
                })
            }
        });
    };
    function getMenuTreeData()
    {
        var data=[];
//发送异步请求（ajax）
        $.ajax(
            {
                url: "/sysMenu/getMenuDataTreeJson",
                type: "post",
                async:false,
                success:function (result) {
                    data=result;
                }
            }
        );
        return data;
    }
//初始化树形结构
    renderTree();
});