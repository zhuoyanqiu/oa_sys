layui.use(['form','layer'],function(){
    var form = layui.form,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;


    form.on('submit(submit)',function (data) {

        var ajaxReturnData;

        console.log("helloworld");
        $.ajax({
            url: path+'/login/login',
            type: 'post',
            async:false,
            data:data.field,
            success:function (data) {
                ajaxReturnData=data;
            }
        });

        if(ajaxReturnData.code=='0')
        {
            layer.msg(ajaxReturnData.msg, {icon: 1,offset: '100px'});
            setTimeout("window.location.href='"+ path +"/login/index'",1000)//延迟一秒跳转登录成功之后界面
            return false;

        }else {
            layer.msg(ajaxReturnData.msg, {icon: 5,offset: '100px'});
            return false;
        }

    })



})