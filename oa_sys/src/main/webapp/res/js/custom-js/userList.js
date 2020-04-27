layui.config({
    base: "../res/js/"
}).use(['form', 'layer', 'laydate', 'table', 'upload', 'treeSelect'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;

    var statusTpl = function (data) { // 参数d是当前行数据
        var isCkecked = data.status == "0" ? "checked" : "";
        return '<input type="checkbox" value="'+data.id+'" lay-filter="status" lay-skin="switch" lay-text="启用|禁用" '+ isCkecked +'> ';
    };

    //用户列表
    var tableIns = table.render({
        elem: '#list',
        url: path + '/sysUser/getSysUserListByPage',
        height: "full-104",
        page: true,
        limit: 10,
        limits: [10, 15, 20, 25],
        id: "tables",
        cols: [[
            {
                type: "checkbox",
                fixed: "left",
                width: 50,
                align: 'center'
            },
            {
                field: 'loginName',
                title: '用户名',
                align: 'center'
            },
            {
                field: 'pwd',
                title: '密码',
                align: 'center'
            },
            {
                field: 'img',
                title: '图片',
                align: 'center'
            },
            {
                field: 'email',
                title: '邮箱',
                align: 'center'
            },
            {
                field: 'sex',
                title: '性别',
                align: 'center'
            },
            {
                field: 'mobile',
                title: '联系电话',
                align: 'center'
            },
            {
                field: 'status',
                title: '状态',
                align: 'center',
                templet: statusTpl
            }, {
                field: 'name',
                title: '真实姓名',
                align: 'center'
            },
            {
                field: 'roleName',
                title: '角色名',
                align: 'center'
            },
            {
                field: 'departmentName',
                title: '部门名',
                align: 'center'
            },
            {
                title: '操作',
                width: 200,
                fixed: "right",
                align: "center",
                templet: '#flinkbar'
            }
        ]]
    });

    /*    //搜索
        $(".search_btn").on("click", function () {
            table.reload("tables", {
                /!* page: {
                curr: 1 //重新从第 1 页开始
                },*!/
                where: {
                    loginName: $(".login_name").val(),
                    // sex: $(".sex").val()
                }
            });
        });*/


    $(function () {
        //输入框的值改变时触发
        $("#loginName_select").on("input", function (data) {
            //获取input输入的值
            table.reload("tables", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    loginName: data.delegateTarget.value
                }
            });

        });
    });

    form.on('select(sex_select)', function (data) {
        table.reload("tables", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                sex: data.value
            }
        });
    });

    form.on('switch(status)', function(data){
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //开关是否开启，true或者false
        console.log(data.value); //开关value值，也可以通过data.elem.value得到
        console.log(data.othis); //得到美化后的DOM对象
        var id = data.value;
        var status = this.checked ? '0' : '1';
        var index;
        $.ajax({

            type: 'POST',
            url: '/sysUser/updateSaveSysUser',
            data: {"id" :id,"status":status  },
            beforeSend:function(){
                index = layer.msg('正在切换中，请稍候',{icon: 16,time:false,shade:0.8});
            },
            error: function(data){
                console.log(data);
                layer.msg('数据异常，操作失败！');
            },
            success: function(data){
                if(data==="0"){
                    setTimeout(function(){
                        layer.close(index);
                        layer.msg('操作成功！');},2000);
                }else{
                    console.log(data);
                    layer.msg('数据异常，操作失败！');
                }},
            dataType:'JSON'

        });
    });

    //绑定添加友情链接事件
    $(".addLink_btn").click(function () {
        addLink();
    });

    //添加用户
    function addLink() {
        layer.open({
            title: "添加用户",
            type: 2,
            area: ['540px', '720px'],
            content: path + "/sysUser/toAddSysUser"
        });
    }

    form.on("submit(addLink)", function (data) {
        //弹出loading
        const index = top.layer.msg('数据提交中，请稍候', {
            icon: 16, time: false, shade: 0.8
        });
        let ajaxReturnData;
        //登陆验证
        $.ajax({
            url: path + '/sysUser/saveSysUser',
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                ajaxReturnData = data;
            }
        });
        //结果回应
        if (ajaxReturnData === '0') {
            top.layer.close(index);
            top.layer.msg('保存成功', {icon: 1});
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();
            // parent.location.reload();
        } else {
            top.layer.msg('保存失败', {icon: 5});
        }
        return false;
    });


    //批量删除
    $(".delAll_btn").on("click", function () {
        //获取复选框的组件
        var checkStatus = table.checkStatus('tables'), data = checkStatus.data,
            linkId = [];
        if (data.length > 0) {
            for (var i in data) {
                linkId.push(data[i].id);
            }
            layer.confirm('确定删除选中的用户？', {
                icon: 3,
                title: '提示信息'
            }, function (index) {
                var ajaxReturnData;
                //调用jquery实现异步请求提交，以post方式提交
                $.ajax({
                    url: path + '/sysUser/deleteBatch',
                    type: 'post',
                    async: false,
                    data: {ids: linkId.toString()},
                    success: function (data) {
                        ajaxReturnData = data;
                        //删除结果
                        if (ajaxReturnData == '0') {
                            //重新加载一个table
                            table.reload('tables');
                            layer.msg('删除成功', {icon: 1});
                        } else {
                            jsp组件
                            layer.msg('删除失败', {icon: 5});
                        }
                    }
                });
            })
        } else {
            layer.msg("请选择需要删除的部门");
        }
    });


    //列表操作
    //建立table的操作事件
    table.on('tool(tables)', function (obj) {
        //获取事件组件定义的layEvent，data是触发事件组件所在的行数据
        var layEvent = obj.event, data = obj.data;
        if (layEvent === 'edit') { //编辑
            editLink(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此用户？', {
                icon: 3,
                title: '提示信息'
            }, function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: path + '/sysUser/delete',
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                //删除结果
                if (ajaxReturnData == '0') {
                    table.reload('tables');
                    layer.msg('删除成功', {icon: 1});
                } else {
                    layer.msg('删除失败', {icon: 5});
                }
                layer.close(index);
            });
        }
    });


    /*
* 转发到修改页
* */
    function editLink(data) {
        var index = layer.open({
            title: "修改用户信息",
            type: 2,
            area: ['540px', '720px'],
            content: path + "/sysUser/toEditSysUser?id=" + data.id
        })
    }

    form.on("submit(editLink)", function (data) {

        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        var ajaxReturnData;

        $.ajax({
            url: path + '/sysUser/updateSaveSysUser',
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                ajaxReturnData = data;
            }
        });
        //结果回应
        if (ajaxReturnData == '0') {
            top.layer.close(index);
            top.layer.msg('修改保存成功', {icon: 1});
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();
            // parent.location.reload();
        } else {
            top.layer.msg('修改失败', {icon: 5});
        }
        return false;
    });

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#upload'
        , url: path + '/upload/image'
        , before: function (obj) {
//预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#imgg').attr('src', result); //图片链接
            });
        }
        , done: function (res) {
//如果上传失败
            if (res.code == 1) {
                return layer.msg('上传失败');
            } else if (res.code == 0) {//上传成功
//$('#imgg').attr('src', res.data.src); //图片
                $("#img").val('/upload/image/' + res.image);
                return layer.msg('上传成功');
            } else if (res.code == 2) {
                return layer.msg('上传的文件不是图片');
            }
        }
        , error: function () {
//演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click',
                function () {
                    uploadInst.upload();
                });
        }
    });


});