<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>数据报表</title>
    <link rel="stylesheet" href="<c:url value='/css/data/analysis/analysisFyData.css'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/layui/css/layui.css'/>" media="all">s
    <script src="<c:url value='/layui/layui.js'/>"></script>
    <script src="<c:url value='/js/address.js'/>"></script>
    <script src="<c:url value='/js/echarts.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
    <script>
        $(function(){
            /* 点击按钮异步请求*/
            $("#bt_1").click(function(){
                var fyTdate = $("#fyTdate").val();
                var FyProvince = $("#FyProvince").val();
                $.ajax({
                    url:"<c:url value='/DataControl/AnalysisFyData.do'/>",
                    type:'get',
                    data:{'fyTdate':fyTdate,'fyCity':FyProvince},
                    dataType: 'json',//请求的数据类型
                    success:function (data) {
                        if (data != null ) {
                            pieChart(data.AnanlysisSort);
                            lineChart(data.AnanlysisLine);
                        } else {
                            alert("加载失败");
                        }

                    },
                    error:function(data) {
                        alert("出错啦...");
                        alert(data);
                    }
                });
                /* 饼状图表 */
                function pieChart(data) {
                    option = {
                        title: {
                            text: '某站点用户访问来源',
                            subtext: '纯属虚构',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b} : {c} ({d}%)'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data: ['死亡', '治愈', '疑似', '确诊']
                        },
                        series: [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius: '55%',
                                center: ['50%', '60%'],
                                data: data,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    var myPieChart = echarts.init(document.getElementById('pieChart'));
                    myPieChart.setOption(option);
                }
                /* 折线图表 */
                function lineChart(data){
                    option = {
                        title: {
                            text: '折线图堆叠'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['死亡', '治愈', '疑似', '确诊']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: data
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    var myLineChart = echarts.init(document.getElementById('lineChart'));

                    myLineChart.setOption(option);
                }
            });
        });
   </script>
</head>
<body>
<div class="bodyMain">

    <div class="bodyMainHeard">
        <div class="layui-form-item">
            <label class="layui-form-label">时间</label>
            <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
                <input type="text" id="fyTdate" value="<fmt:formatDate value="${fyData.fyTdate}" pattern="yyyy-MM-dd"/>" name="fyTdate" class="layui-input" id="fyTdate" placeholder="yyyy-MM-dd">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">省份</label>
            <div class="layui-input-block">
                <select name="fyProvince" id="FyProvince" lay-ignore>
                </select>
            </div>
        </div>

        <input type="button" value="点我啊！" id="bt_1">
    </div>
    <div class="bodyMianConten">
        <div>
            <div class="pieChart" id="pieChart" style="width: 600px;height:400px;"></div>
            <div class="lineChart" id="lineChart" style="width: 600px;height:400px;"></div>
        </div>
        <div id="main" style="width: 600px;height:400px;"></div>
    </div>
</div>
<script type="text/javascript">

    layui.use(['form', 'laydate'],function(){
        var form = layui.form;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#fyTdate' //指定元素
            ,trigger: 'click' //自动弹出控件的事件，采用click弹出
            ,format:'yyyy-MM-dd'
        });
    });

    var fyProvince = '${fyData.fyProvince}';
    var fyCity = '${fyData.fyCity}';
    var fyCounty ='${fyData.fyCounty}';

    alert("fyCity信息：");
    addressInit('FyProvince', 'FyCity', 'FyCounty',fyProvince,fyCity,fyCounty);

</script>
</body>
</html>
