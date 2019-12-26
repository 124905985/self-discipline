layui.use(['laydate', 'form', 'table'], function () {
    var laydate = layui.laydate;
    var form = layui.form;
    var table = layui.table;


    //开始时间选择框
    laydate.render({
        elem: '#startTime'
    });

    //结束时间选择框
    laydate.render({
        elem: '#endTime'
    });

    //表格数据初始化
    table.render({
        elem: '#articleTable',
        method: 'get',
        url: getContextPath() + '/articles/pageList',
        cellMinWidth: 80,
        autoSort: false,
        cols: [[
            {field: 'articleId', title: 'articleId', hide: true, sort: true},
            {field: 'userId', title: 'userId', hide: true, sort: true},
            {field: 'articleTitle', title: '标题'},
            {field: 'articleViews', title: '浏览量'},
            {field: 'articleCommentCount', title: '评论数'},
            {field: 'articleDate', title: '发布日期'},
            {field: 'articleLikeCount', title: '点赞数'},
            {field: 'articleStatus', title: '状态'},
            {field: 'right', title: '操作', toolbar: '#toolbar'}
        ]],
        page: true,  //开启分页
        request: {
            pageName: 'current' //页码的参数名称，默认：page
            ,limitName: 'size' //每页数据量的参数名，默认：limit
        }
    });

    //监听事件
    table.on('toolbar(articleTool)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'enable':
                layer.msg('启用');
                break;
            case 'delete':
                layer.msg('删除');
                break;
            case 'update':
                layer.msg('编辑');
                break;
        }
    });


});

/*用户-停用*/
function member_stop(obj, id) {
    layer.confirm('确认要停用吗？', function (index) {

        if ($(obj).attr('title') == '启用') {

            //发异步把用户状态进行更改
            $(obj).attr('title', '停用')
            $(obj).find('i').html('&#xe62f;');

            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
            layer.msg('已停用!', {icon: 5, time: 1000});

        } else {
            $(obj).attr('title', '启用')
            $(obj).find('i').html('&#xe601;');

            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
            layer.msg('已启用!', {icon: 5, time: 1000});
        }

    });
}

/*用户-删除*/
function member_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        //发异步删除数据
        $(obj).parents("tr").remove();
        layer.msg('已删除!', {icon: 1, time: 1000});
    });
}


function delAll(argument) {
    var ids = [];

    // 获取选中的id
    $('tbody input').each(function (index, el) {
        if ($(this).prop('checked')) {
            ids.push($(this).val())
        }
    });

    layer.confirm('确认要删除吗？' + ids.toString(), function (index) {
        //捉到所有被选中的，发异步进行删除
        layer.msg('删除成功', {icon: 1});
        $(".layui-form-checked").not('.header').parents('tr').remove();
    });
}
