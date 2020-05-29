<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 2019/4/12
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="/admin/layuiadmin/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="js/jquery.min.3.1.0.js"></script>
</head>
<body>
<form  class="layui-form" id="myForm" name="myForm" action="#" method="post" enctype="application/x-www-form-urlencoded">
    <div class="layui-inline">
        <label class="layui-form-label">发帖时间</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="topic_time" name="topic" placeholder="yyyy-MM-dd HH:mm:ss">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">主题名称</label>
        <div class="layui-input-inline">
            <input id="topic_name" name="topic_name" type="text" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">主题内容</label>
        <div class="layui-input-inline">
            <input id="topic_content" name="topic_content"  type="text" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">板块编号</label>
        <div class="layui-input-inline">
            <input id="block_id" name="block_id"  type="text" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户编号</label>
        <div class="layui-input-inline">
            <input id="user_id" name="user_id"  type="text" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">课程编号</label>
        <div class="layui-input-inline">
            <input id="course_id" name="course_id"  type="text" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">回复数</label>
        <div class="layui-input-inline">
            <input id="replys" name="replys"  type="text" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">查看数</label>
        <div class="layui-input-inline">
            <input id="looks" name="looks"  type="text" value="" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="buttons">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
<script src="layuiadmin/layui/layui.js"></script>
<script >
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
        //监听提交
        form.on('submit(buttons)', function(data){
            $.ajax({
                url: "/addTopic.do",
                dataType: "json",
                type: 'post',
                data: $("#myForm").serialize(),
                success: function (data) {
                    if (data.msg == 1) {
                        layer.open({
                            content: '新增成功'
                            , btn: ['确定']
                            , yes: function (index, layero) {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            }
                        });
                    } else {
                        layer.open({
                            content: '新增失败'
                            , btn: ['确定']
                            , yes: function (index, layero) {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            }
                        });
                    }
                }
            });
            return false;
        });
    });
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //日期时间选择器
        laydate.render({
            elem: '#topic_time'
            ,type: 'datetime'
        });
    });
</script>
</html>