<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>查询更新列表</title>
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script src="<c:url value='/js/address.js'/>"></script>
    <script>

        layui.use(['form', 'laydate'],function(){
            var form = layui.form;
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#fyTdate' //指定元素
                ,trigger: 'click' //自动弹出控件的事件，采用click弹出
                ,format:'yyyy-MM-dd'
            });

            laydate.render({
                elem: '#endTdate' //指定元素
                ,trigger: 'click' //自动弹出控件的事件，采用click弹出
                ,format:'yyyy-MM-dd'
            });
        });
    </script>
</head>
<body>
<div class="from">
    <form class="layui-form" action="<c:url value='/DataControl/upDateDataLists.do'/>" method="post">

        <div class="layui-form-item">
            <label class="layui-form-label">患者姓名</label>
            <div class="layui-input-block">
                <input type="text" name="fyName" value="${fyConditionData.fyName}" placeholder="请输入患者姓名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">患者的身份证件号：</label>
            <div class="layui-input-block">
                <input type="text" name="fyIdCard" value="${fyConditionData.fyIdCard}" placeholder="请输入证件号" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">患者的起始年龄：</label>
            <div class="layui-input-block">
                <input type="text" name="fyAge" value="${fyConditionData.fyAge}" placeholder="请输入年龄" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">患者的终止年龄：</label>
            <div class="layui-input-block">
                <input type="text" name="endAge" value="${fyConditionData.endAge}" placeholder="请输入年龄" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">患者的性别</label>
            <div class="layui-input-block">
                <input type="radio" name="fySex" value="男" title="男性" <c:if test="${'男' eq fyConditionData.fySex}">checked</c:if>/>
                <input type="radio" name="fySex" value="女" title="女性" <c:if test="${'女' eq fyConditionData.fySex}">checked</c:if>/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">患者的当前状态</label>
            <div class="layui-input-block">
                <input type="radio" name="fyStatus" value="YS" title="疑似" <c:if test="${'YS' eq fyConditionData.fyStatus}">checked</c:if>/>
                <input type="radio" name="fyStatus" value="QZ" title="确诊" <c:if test="${'QZ' eq fyConditionData.fyStatus }">checked</c:if>/>
                <input type="radio" name="fyStatus" value="ZY" title="治愈" <c:if test="${'ZY' eq fyConditionData.fyStatus }">checked</c:if>/>
                <input type="radio" name="fyStatus" value="SW" title="死亡" <c:if test="${'SW' eq fyConditionData.fyStatus }">checked</c:if>/>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">起始时间：</label>
            <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                <input type="text" value="<fmt:formatDate value="${fyConditionData.fyTdate}" pattern="yyyy-MM-dd"/>" name="fyTdate" class="layui-input" id="fyTdate" placeholder="yyyy-MM-dd">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">终止时间：</label>
            <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                <input type="text" value="<fmt:formatDate value="${fyConditionData.endTdate}" pattern="yyyy-MM-dd"/>" name="endTdate" class="layui-input" id="endTdate" placeholder="yyyy-MM-dd">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">点击搜索</button>
                <button type="reset" class="layui-btn layui-btn-primary">恢复原文</button>
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
            <col width="200">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>姓名</th>
            <th>证件号</th>
            <th>年龄</th>
            <th>性别</th>
            <th>当前状态</th>
            <th>状态时间</th>
            <th>详细地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${fyDatas}" var="fyData">
            <tr>
                <td>
                    <a href="<c:url value='/DataControl/findOneData.do?fyDid=${fyData.fyDid}'/>" class="my-title" target="_blank">
                        ${fyData.fyName}
                    </a>
                </td>
                <td>${fyData.fyIdCard}</td>
                <td>${fyData.fyAge}</td>
                <td>
                    <c:if test="${'男' eq fyData.fySex}">男</c:if>
                    <c:if test="${'女' eq fyData.fySex}">女</c:if>
                </td>
                <td>
                    <c:if test="${'YS' eq fyData.fyStatus}">疑似</c:if>
                    <c:if test="${'QZ' eq fyData.fyStatus}">确诊</c:if>
                    <c:if test="${'ZY' eq fyData.fyStatus}">治愈</c:if>
                    <c:if test="${'SW' eq fyData.fyStatus}">死亡</c:if>
                </td>
                <td><fmt:formatDate value="${fyData.fyTdate}" pattern="yyyy年MM月dd日"/></td>
                <td>${fyData.fyProvince}-${fyData.fyCity}-${fyData.fyCounty}</td>
                <td><a href="<c:url value='/DataControl/findUpdateOneData.do?fyDid=${fyData.fyDid}'/>">修改</a> <a href="<c:url value='/DataControl/deleteOneData.do?fyDid=${fyData.fyDid}'/>">删除</a></td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
</div>


<script type="text/javascript">
    var fyProvince = '${fyConditionData.fyProvince}';
    var fyCity = '${fyConditionData.fyCity}';
    var fyCounty ='${fyConditionData.fyCounty}';
    addressInit('FyProvince', 'FyCity', 'FyCounty',fyProvince,fyCity,fyCounty);
</script>
</body>
</html>
