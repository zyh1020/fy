<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章展示页面</title>
</head>
<body>
    <h1>文章标题:${oneArticle.fyTitle}</h1>
    <h3>时间:${oneArticle.fyTdate}</h3>
    <h3>分类:
        <c:if test="${'ZZ' eq oneArticle.fySort}">治疗和诊断</c:if>
        <c:if test="${'KF' eq oneArticle.fySort}">控制和防护</c:if>
        <c:if test="${'PY' eq oneArticle.fySort}">辟谣</c:if>
        <c:if test="${'QT' eq oneArticle.fySort}">其他</c:if>
    </h3>
    <h2>摘要:${oneArticle.fyAbstract}</h2>
    <p>内容:${oneArticle.fyContent}</p>
</body>
</html>
