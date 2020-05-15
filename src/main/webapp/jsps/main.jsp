<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>" media="all">
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script>
        //注意：导航 依赖 element 模块，否则无法进行功能性操作
        layui.use(['element','carousel'], function(){
            var element = layui.element;
            var carousel = layui.carousel;
            //建造实例
            carousel.render({
                elem: '#test1'
                ,width: '100%' //设置容器宽度
                //,anim: 'updown' //切换动画方式
            });
        });
    </script>
</head>
<body>

    <div class="mianBody">
        <div class="Img_heard">
            <img src="<c:url value='/Img/banner_bg.jpg'/>"/>
        </div>
        <div class="mianContent">
            <div class="content_left">
                <ul class="layui-nav layui-nav-tree layui-bg-white" lay-filter="test">
                    <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
                    <c:choose>
                        <c:when test="${empty sessionScope.sessionFyUser}">
                            <li class="layui-nav-item"><a href="<c:url value='/jsps/user/userLogin.jsp'/>">点击登录</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="layui-nav-item">
                                <a href="javascript:;"><img src="${sessionScope.sessionFyUser.fy2User.fy2Img}" class="layui-nav-img">${sessionScope.sessionFyUser.fy2User.fy2Name}</a>
                                <dl class="layui-nav-child">
                                    <dd><a href="<c:url value='/jsps/user2/updateUser2.jsp'/>">修改信息</a></dd>
                                    <dd><a href="javascript:;">安全管理</a></dd>
                                    <dd><a href="<c:url value='/UserControl/quit.do'/>">退了</a></dd>
                                </dl>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">肺炎的动态信息</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=1&fySort=ZZ'/>">肺炎的治疗和诊断</a></dd>
                            <dd><a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=1&fySort=KF'/>">肺炎的控制和防护</a></dd>
                            <dd><a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=1&fySort=PY'/>">肺炎的辟谣信息</a></dd>
                            <dd><a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=1&fySort=QT'/>">肺炎的其他信息</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">肺炎的信息的管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<c:url value='/ArticleControl/toArticleFrom.do'/>">录入肺炎的信息</a></dd>
                            <dd><a href="<c:url value='/ArticleControl/findUpdateArticle.do?fyUid=${sessionScope.sessionFyUser.fyUid}'/> ">更改肺炎的信息</a></dd>
                            <dd><a href="<c:url value='/ArticleControl/findAriticleCondition.do'/>">查询肺炎的信息</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">肺炎的数据分析</a>
                        <dl class="layui-nav-child">
                            <dd><a href="<c:url value='/DataControl/toAddDataFrom.do'/>">录入肺炎数据</a></dd>
                            <dd><a href="<c:url value='/DataControl/upDateDataLists.do'/>">更改肺炎数据</a></dd>
                            <dd><a href="<c:url value='/DataControl/findDatasByFyData.do'/>">查询肺炎数据</a></dd>
                        </dl>
                    </li>

                    <li class="layui-nav-item"><a href="<c:url value='/jsps/data/analysis/analysisFyData.jsp'/>">肺炎数据报表</a></li>

                </ul>
            </div>
            <div class="content_right">
                <div class="right_img">
                    <div class="layui-carousel" id="test1">
                        <div carousel-item>
                            <div class="lu_img"><img src="<c:url value='/Img/01.jpg'/>"></div>
                            <div class="lu_img"><img src="<c:url value='/Img/02.jpg'/>"></div>
                        </div>
                    </div>
                </div>
                <div class="right_content">

                    <div class="right_content_list">

                        <c:forEach items="${pageBean.lists}" var="article">
                            <ul class="layui-timeline">
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                    <div class="layui-timeline-content layui-text">
                                        <h3 class="layui-timeline-title">${article.fyTdate}</h3>
                                        <p class="contentP">

                                            分类：
                                            <c:if test="${'ZZ' eq article.fySort}">治疗和诊断</c:if>
                                            <c:if test="${'KF' eq article.fySort}">控制和防护</c:if>
                                            <c:if test="${'PY' eq article.fySort}">辟谣</c:if>
                                            <c:if test="${'QT' eq article.fySort}">其他</c:if>
                                            <br>标题：<a href="<c:url value='/ArticleControl/findOneArticle.do?fyTid=${article.fyTid}'/>" class="my-title" target="_blank">${article.fyTitle}</a>
                                            <br>文章摘要：<i class="layui-icon">${article.fyAbstract}</i>

                                        </p>
                                    </div>
                                </li>

                            </ul>
                        </c:forEach>
                    </div>

                    <div class="pageBean">
                        <c:if test="${not empty pageBean.lists}">

                            <span>总记录数：${pageBean.totalCount }  每页显示:${pageBean.pageSize}</span>
                            <span>
                               <c:if test="${pageBean.currPage != 1}">
                                   <a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=1&fySort=${fySort}'/>">[首页]</a>
                                   <a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=${pageBean.currPage-1}&fySort=${fySort}'/>">[上一页]</a>
                               </c:if>
                            <span>第${pageBean.currPage }/ ${pageBean.totalPage}页</span>
                               <c:if test="${pageBean.currPage != pageBean.totalPage}">
                                   <a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=${pageBean.currPage+1}&fySort=${fySort}'/>">[下一页]</a>
                                   <a href="<c:url value='/ArticleControl/findPageBeanSelect.do?currentPage=${pageBean.totalPage}&fySort=${fySort}'/>">[尾页]</a>
                               </c:if>
                            </span>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="bodyFoot"></div>
    </div>

</body>
</html>
