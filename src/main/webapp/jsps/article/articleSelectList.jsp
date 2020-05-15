<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查询列表</title>
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/css/article/articleFrom.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script>
        layui.use(['form', 'laydate'],function(){
            var form = layui.form;
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#fyTdate' //指定元素
                ,trigger: 'click' //自动弹出控件的事件，采用click弹出
            });
        });
    </script>
</head>
<body>
    <div class="from">
        <form class="layui-form" action="<c:url value='/ArticleControl/findAriticleCondition.do'/>" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">文章标题</label>
                <div class="layui-input-block">
                    <input type="text" style="width: 50%" name="fyTitle" value="${fyArticle.fyTitle}"  placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">分类</label>
                <div class="layui-input-block">
                    <input type="radio" name="fySort" value="ZZ" title="治疗和诊断" <c:if test="${'ZZ' eq fyArticle.fySort}">checked</c:if>>
                    <input type="radio" name="fySort" value="KF" title="控制和防护" <c:if test="${'KF' eq fyArticle.fySort}">checked</c:if>>
                    <input type="radio" name="fySort" value="PY" title="辟谣" <c:if test="${'PY' eq fyArticle.fySort}">checked</c:if>>
                    <input type="radio" name="fySort" value="QT" title="其他" <c:if test="${'QT' eq fyArticle.fySort}">checked</c:if>>
                    <input type="radio" name="fySort" value="" title="清除" <c:if test="${'' eq fyArticle.fySort}">checked</c:if>/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">时间</label>
                <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                    <input type="text" value="${fyArticle.fyTdate}" name="fyTdate" class="layui-input" id="fyTdate" placeholder="yyyy-MM-dd">
                    <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">点击搜索</button>
                </div>
            </div>
        </form>
    </div>



    <div class="tableContent">
        <table class="layui-table">
            <colgroup>
                <col width="200">
                <col width="200">
                <col width="200">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>id</th>
                <th>标题</th>
                <th>分类</th>
                <th>时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${fyArticles}" var="article">
                <tr>
                    <td>
                        <a href="<c:url value='/ArticleControl/findOneArticle.do?fyTid=${article.fyTid}'/>" class="my-title" target="_blank">
                           ${article.fyTid}
                        </a>
                    </td>
                    <td>${article.fyTitle}</td>
                    <td>
                        <c:if test="${'ZZ' eq article.fySort}">治疗和诊断</c:if>
                        <c:if test="${'KF' eq article.fySort}">控制和防护</c:if>
                        <c:if test="${'PY' eq article.fySort}">辟谣</c:if>
                        <c:if test="${'QT' eq article.fySort}">其他</c:if>
                    </td>
                    <td>${article.fyTdate}</td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>

</body>
</html>
