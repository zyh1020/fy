<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>可以修改文章列表</title>

    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/css/article/articleFrom.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
</head>
<body>
    <form class="layui-form" action="<c:url value='/ArticleControl/findUpdateArticle.do'/>" method="post">
        <%-- 隐藏一个用户id --%>
        <input type="hidden" name="fyUid" value=" ${sessionScope.sessionFyUser.fyUid}">

        <div class="layui-form-item">
            <label class="layui-form-label">文章标题</label>
            <div class="layui-input-block">
                <input type="text" style="width: 50%" name="fyTitle" value="${fyTitle}" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">点击搜索</button>
            </div>
        </div>

    </form>
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
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articleUpdateList}" var="article">
                <tr>
                    <td>${article.fyTid}</td>
                    <td>${article.fyTitle}</td>
                    <td>
                        <c:if test="${'ZZ' eq article.fySort}">治疗和诊断</c:if>
                        <c:if test="${'KF' eq article.fySort}">控制和防护</c:if>
                        <c:if test="${'PY' eq article.fySort}">辟谣</c:if>
                        <c:if test="${'QT' eq article.fySort}">其他</c:if>
                    </td>
                    <td>${article.fyTdate}</td>
                    <td>
                        <a href="<c:url value='/ArticleControl/findOneArticleByFyTid.do?fyTid=${article.fyTid}'/>">修改</a>
                        <a href="<c:url value='/ArticleControl/deleteArticle.do?fyTid=${article.fyTid}'/>">删除</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

</body>
</html>
