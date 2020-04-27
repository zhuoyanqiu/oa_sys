layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;


    //部门列表
    var tableIns = table.render({
        elem: '#list',
        url : path + '/leave/getLeaveTaskList',
        height : "full-104",
        id : "tables",
        cols : [[
            {
                type : "checkbox",
                fixed : "left",
                align : "center",
                width : 50
            },
            {
                field: 'taskId',
                title: '任务ID',
                align : "center",
                sort: true
            },
            {
                field : 'taskName',
                title : '任务名',
                align : "center"
            },
            {
                field : 'title',
                title : '标题',
                align : "center"
            },
            {
                field : 'reason',
                title : '原因',
                align : "center"
            },
            {
                field : 'days',
                title : '天数',
                align : "center"
            },
            {
                title : '操作',
                width : 300,
                align : "center",
                fixed : "right",
                templet : '#flinkbar'
            }
        ]]
    });

    table.on('tool(tables)',function (obj) {

        var layEvent=obj.event,data=obj.data;

        if(layEvent === 'repeatLeaveForm'){
            //弹出一个调整请假单表单
            editLeaveInfo(data.taskId);

        }else if(layEvent === 'endFlow'){


            layer.confirm('确定结束此流程', {icon: 3, title: '提示信息'}, function (index) {
                var ajaxReturnData;
                //调用jquery实现异步请求提交，以post方式提交
                $.ajax({
                    url: path + '/leave/completeTask',
                    type: 'post',
                    async: false,
                    data: {taskId:data.taskId,pass:false},
                    success: function (data) {
                        ajaxReturnData = data;
                        //删除结果
                        if (ajaxReturnData == '0') {
                            //重新加载一个table
                            table.reload('tables');
                            layer.msg('结束此流程成功', {icon: 1});
                        } else {
                            layer.msg('结束此流程失败', {icon: 5});
                        }
                    }
                });
            })


        }else if(layEvent === 'pass'){


            layer.confirm('确定通过此审核', {icon: 3, title: '提示信息'}, function (index) {
                var ajaxReturnData;
                //调用jquery实现异步请求提交，以post方式提交
                $.ajax({
                    url: path + '/leave/completeTask',
                    type: 'post',
                    async: false,
                    data: {taskId:data.taskId,pass:true},
                    success: function (data) {
                        ajaxReturnData = data;
                        //删除结果
                        if (ajaxReturnData == '0') {
                            //重新加载一个table
                            table.reload('tables');
                            layer.msg('审核成功', {icon: 1});
                        } else {
                            layer.msg('审核失败', {icon: 5});
                        }
                    }
                });
            })


        }else if(layEvent === 'successFinishflow'){


            layer.confirm('此流程确认结束', {icon: 3, title: '提示信息'}, function (index) {
                var ajaxReturnData;
                //调用jquery实现异步请求提交，以post方式提交
                $.ajax({
                    url: path + '/leave/completeTask',
                    type: 'post',
                    async: false,
                    data: {taskId:data.taskId,pass:true},
                    success: function (data) {
                        ajaxReturnData = data;
                        //删除结果
                        if (ajaxReturnData == '0') {
                            //重新加载一个table
                            table.reload('tables');
                            layer.msg('审核成功', {icon: 1});
                        } else {
                            layer.msg('审核失败', {icon: 5});
                        }
                    }
                });
            })


        }else if(layEvent === 'nopass'){

            layer.confirm('确定不通过此审核', {icon: 3, title: '提示信息'}, function (index) {
                var ajaxReturnData;
                //调用jquery实现异步请求提交，以post方式提交
                $.ajax({
                    url: path + '/leave/completeTask',
                    type: 'post',
                    async: false,
                    data: {taskId:data.taskId,pass:false},
                    success: function (data) {
                        ajaxReturnData = data;
                        //删除结果
                        if (ajaxReturnData == '0') {
                            //重新加载一个table
                            table.reload('tables');
                            layer.msg('不审核通过成功', {icon: 1});
                        } else {
                            layer.msg('不审核通过失败', {icon: 5});
                        }
                    }
                });
            })
        }


    })


    form.on("submit(addLeave)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var ajaxReturnData;
        //登陆验证
        $.ajax({
            url: path + '/leave/saveLeave',
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
            top.layer.msg('保存成功', {icon: 1});
        } else {
            top.layer.msg('保存失败', {icon: 5});
        }
        return false;
    })

    //弹出编辑请假内容的表单
    function  editLeaveInfo(taskId) {

        var index=layer.open({
            title:"编辑填写请假表单内容",
            type:2,
            area:['540px','650px'],
            content:path+"/leave/getVarableByTaskId?taskId="+taskId
        })

    }

})