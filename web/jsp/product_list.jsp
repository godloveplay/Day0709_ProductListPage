<%--
  Created by IntelliJ IDEA.
  User: zmh
  Date: 2017/7/10
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <script src=""></script>
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
    </script>
</head>
<body>

<table border="1px solid">
    <tr>
        <td colspan="9">
            名称：<input type="text" name="pname"/>
            <input type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="添加" onclick="addPage()"/>
        </td>
    </tr>
    <tr>
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
    <core:forEach var="p" items="${listOfProducts}" varStatus="status">
        <tr>
            <td>
                    ${status.count}
            </td>
            <td>
                    <input type="checkbox" id="selectAll"/>
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

</table>

</body>
</html>
