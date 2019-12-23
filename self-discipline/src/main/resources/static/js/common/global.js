// 服务端访问路径
var base_path="http://localhost:80";

//自定义模块
layui.config({
    base: '/static/lib/layui/lay/modules/'
}).extend({ //设定组件别名
    common:   'common',
});