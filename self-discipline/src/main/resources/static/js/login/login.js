layui.use(['layer', 'form', 'common'], function () {
    var layer = layui.layer,
        form = layui.form,
        $ = layui.jquery,
        common = layui.common;

    /**
     * 登录提交
     */
    form.on('submit(login)', function (data) {
        common.ajaxPost(
            base_path + '/doLogin', data.field,
            function (result) {
                if (result.code === 200) {
                    layer.msg("登录成功！");
                    window.location.href = "/";
                } else {
                    layer.msg(result.message);
                }
            }, function () {
                layer.msg("登录失败！");
            });
        return false;
    });
});