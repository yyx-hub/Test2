<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2019/3/18
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加课程信息</title>
    <script src="js/jquery.min.3.1.0.js"></script>
    <script language="JavaScript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="layuiadmin/layui/css/layui.css">
</head>
<body>

<form id="myform" name="myform" class="layui-form" action="#" style="margin-top: 20px" method="post">

    <div class="layui-form-item">
            <label class="layui-form-label">课程名称</label>
            <div class="layui-input-inline">
                <input id="courseName" name="courseName" lay-verify="courseName" type="text" value="" class="layui-input" >
            </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">标签ID</label>
            <div class="layui-input-inline">
                <select id="tagId" name="tagId" lay-verify="tagId" lay-filter="aihao" >
                    <option></option>
                </select>
            </div>

        </div>
    </div>

    <div class="layui-form-item">
            <label class="layui-form-label">上线日期</label>
            <div class="layui-input-inline">
                <input id="onlineDate" name="onlineDate" lay-verify="onlineDate" onfocus="WdatePicker({minDate:'%y-%M-{%d}'})" type="text" placeholder="yyyy-MM-dd" autocomplete="off"  class="layui-input" >
            </div>
    </div>

    <div class="layui-form-item">
            <label class="layui-form-label">课程描述</label>
            <div class="layui-input-inline">
                <input id="courseDesc" name="courseDesc" lay-verify="courseDesc" type="text" value="" class="layui-input" >
            </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">课时</label>
        <div class="layui-input-inline">
            <input id="courseHour" name="courseHour" lay-verify="courseHour" type="text" value="" class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">教师ID</label>
            <div class="layui-input-inline">
                <select id="teacherId" name="teacherId" lay-verify="teacherId" lay-filter="aihao" >
                    <option></option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">课程价格</label>
            <div class="layui-input-inline">
                <select id="coursePrice" name="coursePrice" lay-verify="coursePrice" lay-filter="coursePrice">
                    <option value="" selected="selected">请选择课程价格</option>
                    <option value="0" selected="selected">免费</option>
                    <option value="1" selected="selected">普通会员</option>
                    <option value="2" selected="selected">高级会员</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">难易程度</label>
            <div class="layui-input-inline">
                <select name="courseEasy" lay-verify="courseEasy" lay-filter="courseEasy">
                    <option value="" selected="selected">请选择难易程度</option>
                    <option value="0" selected="selected">简单</option>
                    <option value="1" selected="selected">一般</option>
                    <option value="2" selected="selected">较难</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <button class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
        </div>
    </div>
</form>
</body>

<script src="/admin/layuiadmin/layui/layui.js"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //查询标签表
        $(function () {
            $.ajax({
                url:"../selectList.do",
                dataType:"json",
                success:function (data) {
                    var json=data.data;
                    var role=document.getElementById("tagId");
                    for(var i in json){
                        var option=document.createElement("option");
                        option.setAttribute("value",json[i].tagId);
                        option.innerText=json[i].tagName;
                        role.appendChild(option);
                        form.render('select');
                    }
                }
            });

        })

        //查询教师表
        $(function () {
            $.ajax({
                url:"../selectTList.do",
                dataType:"json",
                success:function (data) {
                    var json=data.data;
                    var role=document.getElementById("teacherId");
                    for(var i in json){
                        var option=document.createElement("option");
                        option.setAttribute("value",json[i].teacherId);
                        option.innerText=json[i].teacherName;
                        role.appendChild(option);
                        form.render('select');
                    }
                }
            });

        })


        laydate.render({
            elem: '#onlineDate'
            ,type: 'datetime'
        });
        //自定义验证规则

        form.verify({
            teacherId: function (value) {
                if (value.length == 0) {
                    return '教师不能为空！';
                }
            }
        });
        form.verify({
            coursePrice: function (value) {
                if (value.length == 0) {
                    return '课程价格不能为空！';
                }
            }
        });
        form.verify({
            courseHour: function (value) {
                if (value.length == 0) {
                    return '课时不能为空！';
                }
            }
        });
        form.verify({
            courseEasy: function (value) {
                if (value.length == 0) {
                    return '难易程度不能为空！';
                }
            }
        });
        //监听提交
        form.on('submit(demo1)', function(data){
            // layer.alert(JSON.stringify(data.field), {
            //     title: '修改后的提交信息'
            // });
            $.ajax({
                url: "../insertCourse.do", //请求的url地址
                dataType: "json", //返回格式为json
                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                data: $("#myform").serialize(), //参数值
                type: "post", //请求方式
                success: function(data) {
                    //请求成功时处理
                    if(data.msg==0){
                        alert("添加成功！");
                    }else{
                        alert("添加失败！");
                    }
                    //关闭当前窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                },

            });

            return false;
        });
    });
</script>
<script>
    function CurentTime() {
        var now = new Date();

        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var day = now.getDate();

        var hh = now.getHours();
        var mm = now.getMinutes();

        var clock = year + "-";

        if (month < 10)
            clock += "0";

        clock += month + "-";

        if (day < 10)
            clock += "0";

        clock += day + " ";

        if (hh < 10)
            clock += "0";

        clock += hh + ":";
        if (mm < 10) clock += '0';
        clock += mm;
        return (clock);
    }
</script>

</html>
