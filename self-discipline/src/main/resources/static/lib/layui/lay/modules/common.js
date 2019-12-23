layui.define(['jquery'], function (exports) {
    var $ = layui.jquery;
    var obj = {
        ajaxPost: function (url, params, successCallback, errorCallback) {
            $.ajax({
                url: url,
                type: 'post',
                contentType: "application/json; charset=utf-8",
                dataType: 'json',//返回值类型
                data: JSON.stringify(params),
                success: successCallback,
                error: errorCallback
            });
        },
        ajaxGet: function (url, successCallback, errorCallback) {
            $.ajax({
                url: url,
                type: 'get',
                dataType: 'json',//返回值类型
                data: {},
                success: successCallback,
                error: errorCallback
            })
        }
    };
    //输出接口
    exports('common', obj);
});