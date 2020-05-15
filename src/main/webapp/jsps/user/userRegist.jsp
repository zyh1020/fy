<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet" href="<c:url value='/css/user/userRegist.css'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script src="<c:url value='/js/riddler-sdk-0.2.2.js'/>"></script>
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
            // 初始化
            new YpRiddler({
                expired: 10,
                mode: 'dialog',
                winWidth: 50,
                lang: 'zh-cn', // 界面语言, 目前支持: 中文简体 zh-cn, 英语 en
                // langPack: LANG_OTHER, // 你可以通过该参数自定义语言包, 其优先级高于lang
                container: document.getElementById('cbox'),
                appId: 'a8bef13adacb466680c390c30b27397e',
                version: 'v1',
                noButton: true,
                onError: function (param) {
                    if (!param.code) {
                        layer.msg('错误请求！',{area: ['200px', '50px'],time: 5000});
                    }
                    else if (parseInt(param.code / 100) == 5) {
                        // 服务不可用时，开发者可采取替代方案
                        layer.msg('服务不可用！',{area: ['200px', '50px'],time: 5000});

                    }
                    else if (param.code == 429) {
                        layer.msg('请求频繁，请稍后再试！',{area: ['200px', '50px'],time: 5000});

                    }
                    else if (param.code == 403) {
                        layer.msg('请求受限，请稍后再试！',{area: ['300px', '50px'],time: 5000});

                    }
                    else if (param.code == 400) {
                        layer.msg('非法请求！',{area: ['200px', '50px'],time: 5000});

                    }
                    // 异常回调
                    layer.msg('验证服务异常！',{area: ['200px', '50px'],time: 5000});

                },
                onSuccess: function (validInfo, close, useDefaultSuccess) {
                    // 成功回调
                    //alert('验证通过! token=' + validInfo.token + ', authenticate=' + validInfo.authenticate);
                    var fyPthoneVal = $("#fyPhone").val();
                    var tokenVal = validInfo.token;
                    var authenticateVal = validInfo.authenticate;

                    var ret = /^[a-zA-Z][a-zA-Z0-9_]{5,20}$/;

                    if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(fyPthoneVal))){
                        layer.msg('手机号不正确！',{area: ['200px', '50px'],time: 5000});
                    }else{
                        /* 发送异步请求 */
                        $.ajax({
                            url: "${pageContext.request.contextPath }/UserControl/code.do",
                            type:"post",
                            contentType:"application/json;charset=UTF-8",
                            dataType:"json",//回调
                            data:JSON.stringify({mobile: fyPthoneVal,token: tokenVal,authenticate:authenticateVal}),

                            success:function(data){
                                if(data.dxMsg){
                                    layer.msg('验证码已发送请注意查收！',{offset: ['90px', '620px'],area: ['300px', '50px'],time: 5000});
                                }
                            },
                            error:function(msg) {
                                alert("失败"+msg)
                            }
                        });
                    }


                    close();
                },
                onFail: function (code, msg, retry) {
                    // 失败回调
                    retry()
                },
                beforeStart: function (next) {
                    console.log('验证马上开始');
                    next()
                },
                onExit: function () {
                    // 退出验证 （仅限dialog模式有效）
                    console.log('退出验证');
                }
            });


        });

    </script>
</head>
<body>
    <div class="RegistFrame">
        <form class="layui-form" action="<c:url value='/UserControl/regist.do'/>" method="post">
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
                <label class="layui-form-label">手机号：</label>
                <div class="layui-input-inline">
                    <input type="text" id="fyPhone" name="fyPhone" value="${fyUser.fyPhone}" required  lay-verify="required|phone|number" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
                <button id="cbox" type="button" class="layui-btn">发送验证码</button>
                <%--<div id="cbox">验证</div>--%>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">验证码：</label>
                <div class="layui-input-inline">
                    <input type="text" name="fyCode" value="${fyUser.fyCode}" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
        <hr class="layui-bg-red">
        <a href="<c:url value='/jsps/user/userLogin.jsp'/>" style="float: left;margin-left:140px" class="layui-btn">已经注册去登陆</a>
    </div>
</body>
</html>
