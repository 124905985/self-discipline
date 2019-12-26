// 服务端访问路径
var base_path = "http://localhost:80";


//自定义模块
layui.config({
    base: '/static/lib/layui/lay/modules/'
}).extend({ //设定组件别名
    common: 'common'
});


//获取contentPath
function getContextPath() {
    debugger;
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}