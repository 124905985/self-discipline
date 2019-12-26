layui.use(['form'], function () {

    var form = layui.form;

    //表单提交
    form.on('submit(save)', function (data) {
        console.log(data.field);
    });

    //取消添加
    form.on('submit(cancel)', function () {
        window.location.href = "/articles";
    });


});
