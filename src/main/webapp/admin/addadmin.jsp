<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/8
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../layui/css/layui.css"></link>
    <link rel="stylesheet" href="/admin/layuiadmin/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="js/jquery.min.3.1.0.js"></script>
</head>

<body>
<form  class="layui-form" id="myForm" name="myForm" action="#" method="post" enctype="application/x-www-form-urlencoded">

    <div class="layui-form-item">
        <label class="layui-form-label">管理员姓名</label>
        <div class="layui-input-inline">
            <input id="admin_name" name="admin_name" lay-verify="admin_name" type="text" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">管理员密码</label>
        <div class="layui-input-inline">
            <input id="admin_pwd" name="admin_pwd" type="password" value="123456" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">管理员电话</label>
        <div class="layui-input-inline">
            <input id="adminphone" name="adminphone" type="text" value=""  class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">管理员权限</label>
        <div class="layui-input-inline">
            <select id="role_level" name="role_level" lay-filter="aihao" >
                <option></option>
                </select>
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
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        $(function () {
            $.ajax({
                url:"../roleList.do",
                dataType:"json",
                success:function (data) {
                    var json=data.data;
                    var role=document.getElementById("role_level");
                    for(var i in json){
                        var option=document.createElement("option");
                        option.setAttribute("value",json[i].role_id);
                        option.innerText=json[i].role_name;
                        role.appendChild(option);
                        form.render('select');
                    }
                }
            });

        })
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            //验证姓名
            admin_name: function(value){
                if(value.length >2){
                    return '用户名至少得2个字符';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }

            ,adminphone:function (value) {
                var p=/^1[3456789]\d{9}$/;
                if(!p.test(value)){
                    return '号码不正确';
                }
            }
        });

        //监听提交
        form.on('submit(buttons)', function(data){
            $.ajax({
                url: "../addadmin.do",
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
</script>
</html>
