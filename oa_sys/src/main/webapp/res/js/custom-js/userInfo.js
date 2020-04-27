var $form;
var form;
var $;
layui.config({
    base: "../../js/"
}).use(['form', 'layer', 'upload', 'laydate'], function () {
    form = layui.form;
    var layer = parent.layer === undefined ? layui.layer :
        parent.layer;
    $ = layui.jquery;
    $form = $('form');
    upload = layui.upload,
    laydate = layui.laydate;
    //普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
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
            demoText.find('.demo-reload').on('click',function () {
                    uploadInst.upload();
                });
        }
    });

    //保存个人信息
    form.on("submit(changeUser)", function (data) {
        //弹出loading
        const index = top.layer.msg('数据提交中，请稍候', {
            icon: 16, time: false, shade: 0.8
        });
        let ajaxReturnData;
        //登陆验证
        $.ajax({
            url: path + '/sysUser/updateSysUser',
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                ajaxReturnData = data;
            }
        });
        //结果回应
        if (ajaxReturnData === '0') {
            top.layer.msg('保存成功', {icon: 1});
            return true;
        } else {
            top.layer.msg('保存失败', {icon: 5});
            return false;
        }

    });


});