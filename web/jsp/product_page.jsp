<%--
  Created by IntelliJ IDEA.
  User: zmh
  Date: 2017/7/13
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/jsp/jquery-3.2.1.min.js"></script>
    <script>
        function addPage() {
            window.location.href = "${pageContext.request.contextPath}/jsp/addProduct.jsp";
        }
        function del(pid) {
            var flag = window.confirm("你确定删除吗？");
            if (flag) {
                window.location.href = "${pageContext.request.contextPath}/Servlet/ProductDeleteServlet?pid=" + pid;
            } else {

            }
        }
        $(function () {
            $("#selectAll").click(function () {
                $("input[id='ids']").prop("checked", this.checked);
            });

            //隐藏 div
            $("#upper").hide();


            $("input[name='pname']").keyup(function () {
                var pname = $("input[name='pname']").val();

                if (pname != "") {
                    $.post("/Servlet/SearchForNameServlet", {'pname': pname}, function (data) {
                        console.log("data----------------:" + $(data).pname);
                        if ($(data) != null) {
                            var $table = $("#showTable");
                            //插入查询到的 商品名字
                            $table.html("");
                            $(data).each(function (index, element) {
                                $table.append("<tr><td>" + element.pname + "</td></tr>")
                            });
                            //显示div
                            $("#upper").show();

                        }
                    }, "json")
                } else {
                    //隐藏 div
                    $("#upper").hide();
                }

            });

        });

        function deleteAll() {
            $("#form1").get(0).submit();
        }
    </script>
    <style>
        #form {
            position: absolute;
            width: 100%;
            height: 100%;
        }

        #upper {
            position: absolute;
            margin-top: 30px;
            margin-left: 52px;
            width: 172px;
            height: 200px;
            border: 1px solid red;
            filter: alpha(opacity=50);
        }
    </style>
</head>
<body>
<div id="form">
    <form id="form1" action="${pageContext.request.contextPath}/Servlet/ProductDeleteAllServlet" method="post">
        <table border="1px solid">
            <tr>
                <td colspan="10">
                    名称：<input type="text" name="pname"/>
                    <input type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="添加" onclick="addPage()"/>
                    <input type="button" value="删除" onclick="deleteAll()"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox" id="selectAll"/>
                </td>
                <td>序号</td>
                <td>商品名</td>
                <td>市场价</td>
                <td>商城售价</td>
                <td>图片</td>
                <td>是否热门</td>
                <td>商品详情</td>
                <td>是否下架</td>
                <td>操作</td>
            </tr>
            <%--<core:forEach var="p" items="${pageContext.request.getAttribute('listOfProducts')}">--%>
            <%--<core:forEach var="p" items="${pageContext.request.getAttribute('listOfProducts')}">--%>
            <core:forEach var="p" items="${pageBean.list}" varStatus="status">
                <tr>
                    <td>
                        <input type="checkbox" id="ids" name="ids" value="${p.pid}"/>
                    </td>
                    <td>
                            ${status.count}
                    </td>
                    <td>
                            ${p.pname}
                    </td>
                    <td>
                            ${p.market_price}
                    </td>
                    <td>
                            ${p.shop_price}
                    </td>
                    <td>
                            ${p.pimage}
                    </td>
                    <td>
                        <core:if test="${p.is_hot == 1}">
                            是
                        </core:if>
                        <core:if test="${p.is_hot == 0}">
                            否
                        </core:if>
                    </td>
                    </td>
                    <td>
                            ${p.pdesc}
                    </td>
                    </td>
                    <td>
                        <core:if test="${p.pflag == 1}">
                            已下架
                        </core:if>
                        <core:if test="${p.pflag == 0}">
                            未下架
                        </core:if>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/Servlet/ProductEditServlet?pid=${p.pid}">修改</a>|
                        <a href="#" onclick="del('${p.pid}')">删除</a>
                    </td>
                </tr>
            </core:forEach>
            <tr>
                <td colspan="10" align="center">
                    <a href="${pageContext.request.contextPath}/Servlet/ProductFindByPageServlet?currPage=1">[首页]</a>
                    <a href="${pageContext.request.contextPath}/Servlet/ProductFindByPageServlet?currPage=1"
                       +${pageBean.currPage - 1}">[上一页]</a>
                    <a href="${pageContext.request.contextPath}/Servlet/ProductFindByPageServlet?currPage=1"
                       +${pageBean.currPage + 1}">[下一页]</a>
                    <a href="${pageContext.request.contextPath}/Servlet/ProductFindByPageServlet?currPage=1"
                       +${pageBean.totalPage}">[尾页]</a>
                </td>
            </tr>
        </table>
    </form>
    <div id="upper">
        <table id="showTable">

        </table>
    </div>
</div>
</body>
</html>
