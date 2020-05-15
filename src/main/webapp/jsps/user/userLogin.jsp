<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录页面</title>
    <link rel="stylesheet" href="<c:url value='/css/user/userLogin.css'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>

    <script>
        $(function () {

            var layer;
            layui.use(['form','layer'],function(){
                var form = layui.form;
                layer = layui.layer;
                form.verify({
                    /* 用户名*/
                    usernamecheck: function (value, item) { //value：表单的值、item：表单的DOM对象
                        if(!new RegExp("^[a-zA-Z0-9_-]{4,9}$").test(value)){
                            return '4到9位只能包含：字母，数字，下划线，减号';
                        }
                        if(/(^\_)|(\__)|(\_+$)/.test(value)){
                            return '用户名首尾不能出现下划线\'_\'';
                        }
                        if(/^\d+\d+\d$/.test(value)){
                            return '用户名不能全为数字';
                        }

                    },
                    passcheck: function (value, item) {
                        if(!new RegExp("^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,12}$").test(value)){
                            return '6到12必须包含数字,英文,字符中的两种以上';
                        }
                    }
                });

            });

            /* 刷新验证码 */
            $("#changeCaptcha").click(function () {
                this.src ="<c:url value='/MyKaptcha/getCaptchaCode.do'/>?"+Math.floor(Math.random()*100);
            });
        });

    </script>
</head>
<body>

<div class="loginFrame">
    <form class="layui-form" action="<c:url value='/UserControl/login.do'/>" method="post">
    <c:choose>
        <c:when test="${not empty message}">
            <div class="message">${message}</div>
            <%-- 移除 --%>
            <% request.getSession().removeAttribute("message");%>
        </c:when>
        <c:otherwise>
            <br/>
        </c:otherwise>
    </c:choose>

        <div class="layui-form-item">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-inline">
                <input type="text" name="fyName" value="${fyUser.fyName}" required  lay-verify="required|usernamecheck" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密 码：</label>
            <div class="layui-input-inline">
                <input type="password" name="fyPassword" value="${fyUser.fyPassword}" required lay-verify="required|passcheck" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码：</label>
            <div class="layui-input-inline">
                <input type="text" name="fyCode" value="${fyUser.fyCode}" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div><img id="changeCaptcha" src="<c:url value='/MyKaptcha/getCaptchaCode.do'/>">
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
    <hr class="layui-bg-red">
    <a href="<c:url value='/jsps/user/userRegist.jsp'/>" style="float: left;margin-left:110px" class="layui-btn">去注册</a>
    <a href="<c:url value='/jsps/user/userRegist.jsp'/>" style="float: left;margin-left:30px" class="layui-btn">找回密码</a>

</div>
</body>
</html>
