<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>肺炎信息展示页面</title>
</head>
<body>
    <h2>姓名：${oneData.fyName} </h2>
    <h2>证件号：${oneData.fyIdCard}</h2>
    <h2>年龄：${oneData.fyAge}</h2>
    <h2>性别：
        <c:if test="${'男' eq oneData.fySex}">男</c:if>
        <c:if test="${'女' eq oneData.fySex}">女</c:if>
    </h2>
    <h2>状态：
        <c:if test="${'YS' eq oneData.fyStatus}">疑似</c:if>
        <c:if test="${'QZ' eq oneData.fyStatus}">确诊</c:if>
        <c:if test="${'ZY' eq oneData.fyStatus}">治愈</c:if>
        <c:if test="${'SW' eq oneData.fyStatus}">死亡</c:if>
    </h2>
    <h2>时间：<fmt:formatDate value="${oneData.fyTdate}" pattern="yyyy年MM月dd日"/> </h2>
    <h2>地点：${oneData.fyProvince} - ${oneData.fyCity} - ${oneData.fyCounty}</h2>
</body>
</html>
