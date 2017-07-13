<%@ page import="com.zmh.utils.UUIDUtils" %>
<%@ page import="net.sf.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: zmh
  Date: 2017/7/10
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/jsp/jquery-3.2.1.min.js"></script>
    <title>Title</title>
    <script>


        function isChanged() {
            <%--var arr = ['${Product.pid}',--%>
            <%--'${Product.pname}',--%>
            <%--'${Product.market_price}',--%>
            <%--'${Product.shop_price}',--%>
            <%--'${Product.is_hot}',--%>
            <%--'${Product.pdesc}',--%>
            <%--'${Product.pflag}',--%>
            <%--'${Product.cid == 1}'];--%>

            if ($("#pname")[0].value == "${Product.pname}") {
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
<h1>修改商品</h1>
<form action="${pageContext.request.contextPath}/Servlet/ProductUpdateServlet" method="post">
    <input type="hidden" name="pid" value="${Product.pid}"/>
    <table border="1" width="600px">
        <tr>
            <td>商品名</td>
            <td><input type="text" id="pname" name="pname" value="${Product.pname}"/><span id="productSpan"></span>
            </td>
        </tr>
        <tr>
            <td>市场价</td>
            <td><input type="text" name="market_price" value="${Product.market_price}"/></td>
        </tr>
        <tr>
            <td>商城售价</td>
            <td><input type="text" name="shop_price" value="${Product.shop_price}"/></td>
        </tr>
        <tr>
            <td>是否热门</td>
            <td><input type="radio" name="is_hot" value="1"
                       <core:if test="${Product.is_hot == 1}">checked</core:if>  />是
                <input type="radio" name="is_hot" value="0"
                       <core:if test="${Product.is_hot == 0}">checked</core:if> />不是
            </td>
        </tr>
        <tr>
            <td>商品详情</td>
            <td><textarea name="pdesc" cols="20" rows="4">${Product.pdesc}</textarea></td>
        </tr>
        <tr>
            <td>是否下架</td>
            <td>
                <select name="pflag">
                    <option value="1" <core:if test="${Product.pflag == 1}">selected</core:if>>已下架</option>
                    <option value="0" <core:if test="${Product.pflag == 0}">selected</core:if>>未下架</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>商品分类</td>
            <td><select name="cid">
                <option value="1" <core:if test="${Product.cid == 1}">selected</core:if>>手机数码</option>
                <option value="2" <core:if test="${Product.cid == 2}">selected</core:if>>电脑办公</option>
                <option value="3" <core:if test="${Product.cid == 3}">selected</core:if>>汽车用品</option>
                <option value="4" <core:if test="${Product.cid == 4}">selected</core:if>>鞋靴箱包</option>
            </select></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交修改" onclick="return isChanged()"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
