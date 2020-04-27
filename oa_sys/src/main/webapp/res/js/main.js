var tab;
layui.config({
    base: '../res/js/'
}).use(['element', 'layer', 'tab'], function () {

       var $ = layui.jquery,
        layer = layui.layer;
    tab = parent.layui.tab({
        elem: '.layout-nav-card', //设置选项卡容器
        contextMenu: true
    });

 /*   $.ajax({
        url: path + '/leave/getLeaveTaskCount',
        type: 'post',
        async: false,
    });*/

        $("#toTaskListId").on("click", function () {


            console.log("toTaskListId");
            var data = {
                icon: 'fa-vcard-o',
                href: '/leave/toTaskList',
                title: '待办任务'
            };

            tab.tabAdd(data);

        })

    $("#leaveformId").on("click", function () {

        console.log("leaveformId");
        var data = {
            icon: 'fa-vcard-o',
            href: '/leave/toLeaveForm',
            title: '填写请假单'
        };

        tab.tabAdd(data);

    })


});