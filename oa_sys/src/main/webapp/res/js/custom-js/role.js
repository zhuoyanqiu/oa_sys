layui.use(['form','layer','jquery','laydate','table','upload','tree'],
    function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            laydate = layui.laydate,
            upload = layui.upload,
            table = layui.table,
            tree=layui.tree;
//角色列表
        var tableIns = table.render({
            elem: '#roleTables',
            url : path + '/sysRole/getSysRoleData',
//启动分页
            page : false,
            cellMinWidth : 95,
            height : "full-104",
            id : "roleTables",
            cols : [[
                {
                    type : "checkbox",
                    align : "center",
                    fixed : "left",
                },
                {
                    field: 'id',
                    title: '角色id',
                    align : "center",
                    sort: true
                },
                {
                    field : 'name',
                    title : '角色名',
                    align : "center"
                },
                {
                    field : 'useable',
                    title : '状态',
                    align : "center",
                    templet:'#status'
                },
                {
                    title : '操作',
                    width : 360,
                    fixed : "right",
                    align : "center",
                    toolbar: '#rolebar'
                }
            ]]
        });
//定义一个方法，此方法用于显示菜单树形结构
        var renderTree= function(inRoleId,roleName) {
            $("#roleName").html(roleName);
            roleId=inRoleId;
            tree.render({
                elem: '#menuTreeId'
                ,data: getMenuTreeData(roleId)
                ,edit: ['add', 'update', 'del']
                ,showCheckbox: true,
                id: 'menuTreeId'
            });
        };
        function getMenuTreeData(roleId)
        {
            var data=[];
//发送异步请求（ajax）
            $.ajax(
                {
                    url: "/sysMenu/getMenuDataTreeByRoleIdJson?roleId="+roleId,
                    type: "post",
                    async:false,
                    success:function (result) {
                        data=result;
                    }
                }
            );
            return data;
        }

        //建立角色列表的操作事件
        table.on('tool(roleTables)', function(obj){
            var layEvent = obj.event,data = obj.data;
            if(layEvent === 'edit'){ //编辑
            //editLink(data);
            } else if(layEvent === 'authorize'){
                renderTree(obj.data.id,obj.data.name);
            }
        });


        $(".savepermisson_btn").on("click",function () {
            var checkedMenuTreeData = tree.getChecked('menuTreeId');
            savePermisson(checkedMenuTreeData);
        });

        function savePermisson(checkedMenuTreeData) {
            var index = layer.msg('保存中', {
                icon: 16
                ,shade: 0.1
            });
            var ajaxReturnData;
            $.ajax({
                    url: path + '/sysRole/savePermission?roleId='+roleId,
                    type: "post",
                    async: false,
                    contentType:"application/json",
                    data: JSON.stringify(checkedMenuTreeData),
                    success:function (result) {
                        ajaxReturnData=result;
                    }
            });

            if (ajaxReturnData == '0') {
                layer.close(index);
                top.layer.msg('保存成功', {icon: 1});
                renderTree(roleId);
            } else {
                top.layer.msg('保存失败', {icon: 5});
            }

        }

        //添加角色
        $('#one_group .layui-btn').on('click',function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        var active = {
            add:function () {
                var index = layer.open({
                    title: "角色添加",
                    type: 2,
                    skin:'',
                    offset: ['85px', '530px'],
                    area: ['540px', '350px'],
                    content: path + "/sysRole/toRoleForm",
                })
            }
        }






    });