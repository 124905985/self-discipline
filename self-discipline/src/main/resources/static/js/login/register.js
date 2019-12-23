layui.use(['layer', 'form', 'common'], function () {
    var layer = layui.layer,
        form = layui.form,
        $ = layui.jquery,
        common = layui.common;

    /**
     * 注册提交
     */
    form.on('submit(register)', function (data) {
        common.ajaxPost(
            base_path + '/register', data.field,
            function (result) {
                if (result.code === 200) {
                    layer.msg("注册成功！");
                    window.location.href = base_path + '/toLogin';
                } else {
                    layer.msg(result.message);
                }
            }, function () {
                layer.msg("注册失败！");
            });
        return false;
    });


    //自定义验证规则
    form.verify({
        pass: [/(.+){6,12}$/, '密码必须6到12位']
        , repass: function (value) {
            var pvalue = $("input[name='password']").val();
            if (pvalue !== value) {
                return "两次输入的密码不一致";
            }
        }
    });
});