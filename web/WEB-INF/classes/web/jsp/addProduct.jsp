<%@ page import="com.zmh.utils.UUIDUtils" %><%--
  Created by IntelliJ IDEA.
  User: zmh
  Date: 2017/7/10
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>


        function checkPname() {
            var pname = document.getElementById("pname").value;
            //获取异步对象
            var XMLHttpRequest = createXMLHttp();
            //绑定状体改变事件
            XMLHttpRequest.onreadystatechange = function () {
                if (XMLHttpRequest.readyState == 4) {
                    if (XMLHttpRequest.status == 200) {
                        var data = XMLHttpRequest.responseText;
                        if (data == 1) {
                            var span = document.getElementById("productSpan");
                            span.innerHTML = "<font color='green'>商品名可以添加</font>";
                        } else if (data == 0) {
                            var span = document.getElementById("productSpan");
                            span.innerHTML = "<font color='red'>商品名已存在</font>";
                        }
                    }
                }
            }
            //设置请求方法和参数
            XMLHttpRequest.open("GET", "${pageContext.request.contextPath}/Servlet/ProductCheckServlet?pname=" + pname, true);
            //发送请求
            XMLHttpRequest.send(null);
        }


        //获取异步对象
        function createXMLHttp() {
            var xmlHttp;
            try { // Firefox, Opera 8.0+, Safari
                xmlHttp = new XMLHttpRequest();
            } catch (e) {
                try {// Internet Explorer
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                } catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                    }
                }
            }

            return xmlHttp;
        }
    </script>
</head>
<body>
<h1>添加商品</h1>
<%
    //在提交页面  向session中存入token   在servlet中获取 进行令牌校验
    String token = UUIDUtils.getUUID();
    session.setAttribute("token", token);
%>
<form action="${pageContext.request.contextPath}/Servlet/ProductAddServlet" method="post">
    <input type="hidden" name="token" value="${token}"/>
    <table border="1" width="600px">
        <tr>
            <td>商品名</td>
            <td><input type="text" id="pname" name="pname" onblur="checkPname()"/><span id="productSpan"></span>
            </td>
        </tr>
        <tr>
            <td>市场价</td>
            <td><input type="text" name="market_price"/></td>
        </tr>
        <tr>
            <td>商城售价</td>
            <td><input type="text" name="shop_price"/></td>
        </tr>
        <tr>
            <td>是否热门</td>
            <td><input type="radio" name="is_hot" value="1">是
                <input type="radio" name="is_hot" value="0">不是
            </td>
        </tr>
        <tr>
            <td>商品详情</td>
            <td><textarea name="pdesc" cols="20" rows="4">

            </textarea></td>
        </tr>
        <tr>
            <td>是否下架</td>
            <td>
                <select name="pflag">
                    <option value="1">已下架</option>
                    <option value="0">未下架</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>商品分类</td>
            <td><select name="cid">
                <option value="1">手机数码</option>
                <option value="2">电脑办公</option>
                <option value="3">汽车用品</option>
                <option value="4">鞋靴箱包</option>
            </select></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交修改"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
