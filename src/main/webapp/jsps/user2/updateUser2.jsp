<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户详情页面</title>
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.form.js'/>"></script>
    <script>
        $(function () {
            var layer;
            layui.use(['form','layer'],function(){
                var form = layui.form;
                layer = layui.layer;
                form.verify({
                    /* 用户名*/
                    namecheck: function (value, item) { //value：表单的值、item：表单的DOM对象
                        if(value.length > 6){
                            return '昵称太长！';
                        }
                    },
                    agedcheck: function (value, item) { //value：表单的值、item：表单的DOM对象
                        if(!new RegExp("^(?:[1-9][0-9]?|1[01][0-9]|120)$").test(value)){
                            return '年龄输入0-120之间';
                        }
                    },
                    qmcheck: function (value, item) { //value：表单的值、item：表单的DOM对象
                        if(value.length > 100){
                            return '签名太长！';
                        }
                    }

                });
            });
            $("#testSubmmit").click(function () {
                var hideForm = $('#imgFrom');
                var options = {
                    dataType : "json",
                    beforeSubmit : function() {

                    },
                    success : function(result) {
                        var ms = $.trim(result.isOk);
                        if($.trim("A") == ms){
                            $("#heardImg").css("background-image","url("+result.imgUrl+")")
                            layer.msg("修改头像成功！",{offset: ['90px', '620px'],area: ['300px', '50px'],time: 5000});
                        }else if($.trim("B") == ms){
                            layer.msg("文件夹创建失败！",{offset: ['90px', '620px'],area: ['300px', '50px'],time: 5000});
                        }else if($.trim("C") == ms){
                            layer.msg("图片不是以jpg结尾的！",{offset: ['90px', '620px'],area: ['300px', '50px'],time: 5000});
                        }else if($.trim("D") == ms){
                            layer.msg("文件上传失败！",{offset: ['90px', '620px'],area: ['300px', '50px'],time: 5000});
                        }else {
                            layer.msg("上传了点问题！",{offset: ['90px', '620px'],area: ['300px', '50px'],time: 5000});
                        }

                    },
                    error : function(result) {
                        alert('失败');
                    }
                };
                hideForm.ajaxSubmit(options);
            });
        });

    </script>
    <style>
        #heardImg{
            width: 100px;
            height: 100px;
            background-position: center center;
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>

<div>
    <form id="imgFrom" class="layui-form" action="<c:url value='/UserControl/uploadHead.do'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="fy2Uid" value="${sessionScope.sessionFyUser.fy2User.fy2Uid}">
        <%-- 这是原来的 用于删除图片的--%>
        <input  type="hidden" name="fy2Img" value="${sessionScope.sessionFyUser.fy2User.fy2Img}">
        <%-- 展示图片 --%>
        <div id="heardImg" style="background-image: url(${sessionScope.sessionFyUser.fy2User.fy2Img});"></div>
        <input  type="file" name="multipartFile"/>
        <button id="testSubmmit" type="button" class="layui-btn">更改按钮</button>
    </form>
</div>

<%-- 分割线 --%>
<hr class="layui-bg-orange">

<div>
    <form class="layui-form" action="<c:url value='/UserControl/updateUser2.do'/>" method="post">
        <input type="hidden" name="fy2Uid" value="${sessionScope.sessionFyUser.fy2User.fy2Uid}">
        <input type="hidden" name="fyUidy" value="${sessionScope.sessionFyUser.fy2User.fyUidy}">
        <div class="layui-form-item">
            <label class="layui-form-label">昵称：</label>
            <div class="layui-input-inline">
                <input type="text" name="fy2Name" value="${sessionScope.sessionFyUser.fy2User.fy2Name}" required  lay-verify="required|namecheck" placeholder="请输入昵称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别：</label>
            <div class="layui-input-block">
                <input type="radio" name="fy2Sex" value="男" title="男" <c:if test="${'男' eq sessionScope.sessionFyUser.fy2User.fy2Sex}">checked</c:if>/>
                <input type="radio" name="fy2Sex" value="女" title="女" <c:if test="${'女' eq sessionScope.sessionFyUser.fy2User.fy2Sex}">checked</c:if>/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">年龄：</label>
            <div class="layui-input-block">
                <input type="text" name="fy2Age" value="${sessionScope.sessionFyUser.fy2User.fy2Age}" required  lay-verify="required|agedcheck" placeholder="请输入年龄" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">签名</label>
            <div class="layui-input-block">
                <textarea name="fy2Qm" lay-verify="required|qmcheck" placeholder="请输入内容" class="layui-textarea">${sessionScope.sessionFyUser.fy2User.fy2Qm}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
