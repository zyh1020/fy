<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>更改数据</title>
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
            form.verify({
                /* 用户名*/
                namecheck: function (value, item) { //value：表单的值、item：表单的DOM对象
                    if(value.length > 6){
                        return '名字太长！';
                    }
                },
                agedcheck: function (value, item) { //value：表单的值、item：表单的DOM对象
                    if(!new RegExp("^(?:[1-9][0-9]?|1[01][0-9]|120)$").test(value)){
                        return '年龄输入0-120之间';
                    }
                }

            });
        });
    </script>

</head>
<body>
<form class="layui-form" action="<c:url value='/DataControl/upDateOneData.do'/>" method="post">
    <%-- 隐藏一个文章id --%>
    <input type="hidden" name="fyDid" value="${fyData.fyDid}">

    <div class="layui-form-item">
        <label class="layui-form-label">患者姓名</label>
        <div class="layui-input-block">
            <input type="text" name="fyName" value="${fyData.fyName}" required  lay-verify="required|namecheck" placeholder="请输入患者姓名" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">患者的身份证件号：</label>
        <div class="layui-input-block">
            <input type="text" name="fyIdCard" value="${fyData.fyIdCard}" required  lay-verify="required|identity" placeholder="请输入证件号" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">患者的年龄：</label>
        <div class="layui-input-block">
            <input type="text" name="fyAge" value="${fyData.fyAge}" required  lay-verify="required|agedcheck" placeholder="请输入年龄" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">患者的性别</label>
        <div class="layui-input-block">
            <input type="radio" name="fySex" value="男" title="男性" <c:if test="${'男' eq fyData.fySex}">checked</c:if>/>
            <input type="radio" name="fySex" value="女" title="女性" <c:if test="${'女' eq fyData.fySex}">checked</c:if>/>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">患者的当前状态</label>
        <div class="layui-input-block">
            <input type="radio" name="fyStatus" value="YS" title="疑似" <c:if test="${'YS' eq fyData.fyStatus}">checked</c:if>/>
            <input type="radio" name="fyStatus" value="QZ" title="确诊" <c:if test="${'QZ' eq fyData.fyStatus }">checked</c:if>/>
            <input type="radio" name="fyStatus" value="ZY" title="治愈" <c:if test="${'ZY' eq fyData.fyStatus }">checked</c:if>/>
            <input type="radio" name="fyStatus" value="SW" title="死亡" <c:if test="${'SW' eq fyData.fyStatus }">checked</c:if>/>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">时间</label>
        <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
            <input type="text" value="<fmt:formatDate value="${fyData.fyTdate}" pattern="yyyy-MM-dd"/>" name="fyTdate" class="layui-input" id="fyTdate" placeholder="yyyy-MM-dd">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">省份</label>
        <div class="layui-input-block">
            <select name="fyProvince" id="FyProvince" lay-ignore>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">市</label>
        <div class="layui-input-block">
            <select name="fyCity"  id="FyCity" lay-ignore>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">区/县</label>
        <div class="layui-input-block">
            <select name="fyCounty" id ="FyCounty" lay-ignore>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">更改数据</button>
            <button type="reset" class="layui-btn layui-btn-primary">恢复原文</button>
        </div>
    </div>
</form>

<script type="text/javascript">
    var fyProvince = '${fyData.fyProvince}';
    var fyCity = '${fyData.fyCity}';
    var fyCounty ='${fyData.fyCounty}';
    addressInit('FyProvince', 'FyCity', 'FyCounty',fyProvince,fyCity,fyCounty);
</script>

</body>
</html>
