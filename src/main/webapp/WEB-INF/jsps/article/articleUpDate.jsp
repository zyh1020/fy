<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>修改文章</title>
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/css/article/articleFrom.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/wangEditor.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
    <script>
        $(function () {
            layui.use(['form', 'laydate'],function(){
                var form = layui.form;
                var laydate = layui.laydate;
                //执行一个laydate实例
                laydate.render({
                    elem: '#fyTdate' //指定元素
                    ,trigger: 'click' //自动弹出控件的事件，采用click弹出
                });

                form.verify({
                    /* 用户名*/
                    titlecheck: function (value, item) { //value：表单的值、item：表单的DOM对象
                        if(value.length > 30){
                            return '标题太长了！';
                        }
                    },
                    abstractcheck: function (value, item) {
                        if(value.length > 50){
                            return '摘要太长了！';
                        }
                    },
                    contentcheck: function (value, item) {
                        if(value.length > 3000){
                            return '内容太长了！';
                        }
                    }
                });
            });
            /* 富文本 */
            var E = window.wangEditor;
            var editor = new E('#editorDiv');
            var $text1 = $('#textareaFyContent');
            editor.customConfig.onchange = function (html) {
                // 监控变化，同步更新到 textarea
                $text1.val(html)
            };
            editor.customConfig.uploadFileName = 'multipartFile';
            // 将图片大小限制为 3M
            editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;
            // 限制一次最多上传 1 张图片
            editor.customConfig.uploadImgMaxLength = 1;
            //开启wangEditor的错误提示
            editor.customConfig.debug=true;
            // 关闭粘贴样式的过滤
            editor.customConfig.pasteFilterStyle = false;
            // 忽略粘贴内容中的图片
            editor.customConfig.pasteIgnoreImg = true;
            // 将 timeout 时间改为 3s
            editor.customConfig.uploadImgTimeout = 3000;

            editor.customConfig.uploadImgServer = '${pageContext.request.contextPath }/ArticleControl/fileUpload.do';  // 上传图片到服务器


            editor.customConfig.uploadImgHooks = {
                success: function (xhr, editor, result) {
                    // 图片上传并返回结果，图片插入成功之后触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                    layer.msg('图片插入成功上传！',{offset: ['90px', '620px'],area: ['300px', '50px'],time: 2000});
                },
                error: function (xhr, editor) {
                    // 图片上传出错时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                    alert("图片上传出错时触发");
                },
                timeout: function (xhr, editor) {
                    // 图片上传超时时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                    layer.msg('图片上传超时！稍后再试',{offset: ['90px', '620px'],area: ['300px', '50px'],time: 2000});
                }
            };
            editor.create();
            // 初始化 textarea 的值
            $text1.val(editor.txt.html());
        });
    </script>

</head>
<body>
<form class="layui-form" action="<c:url value='/ArticleControl/updateArticle.do'/>" method="post">

    <%-- 隐藏一个用户id --%>
    <input type="hidden" name="fyUid" value=" ${sessionScope.sessionFyUser.fyUid}">
    <%-- 隐藏一个文章id --%>
    <input type="hidden" name="fyTid" value="${fyArticle.fyTid}">

    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
            <input type="text" name="fyTitle" value="${fyArticle.fyTitle}" required  lay-verify="required|titlecheck" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">文章摘要</label>
        <div class="layui-input-block">
            <input type="text" name="fyAbstract" value="${fyArticle.fyAbstract}" required  lay-verify="required|abstractcheck" placeholder="请输入文章摘要" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分类</label>
        <div class="layui-input-block">
            <input type="radio" name="fySort" value="ZZ" title="治疗和诊断" <c:if test="${'ZZ' eq fyArticle.fySort}">checked</c:if>>
            <input type="radio" name="fySort" value="KF" title="控制和防护" <c:if test="${'KF' eq fyArticle.fySort}">checked</c:if>>
            <input type="radio" name="fySort" value="PY" title="辟谣" <c:if test="${'PY' eq fyArticle.fySort}">checked</c:if>>
            <input type="radio" name="fySort" value="QT" title="其他" <c:if test="${'QT' eq fyArticle.fySort}">checked</c:if>>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">时间</label>
        <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
            <input type="text" value="${fyArticle.fyTdate}" name="fyTdate" class="layui-input" id="fyTdate" placeholder="yyyy-MM-dd">
        </div>
    </div>


        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容</label>

            <textarea id="textareaFyContent" class="layui-textarea" lay-verify="required|contentcheck" style="display:none" name="fyContent"></textarea>
        </div>
        <div id="editorDiv">
            ${fyArticle.fyContent}
        </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">恢复原文</button>
        </div>
    </div>

</form>

</body>
</html>
