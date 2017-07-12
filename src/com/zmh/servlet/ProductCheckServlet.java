package com.zmh.servlet;

import com.zmh.domian.Product;
import com.zmh.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by zmh on 2017/7/11.
 */
@WebServlet(name = "ProductCheckServlet")
public class ProductCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestPname = request.getParameter("pname");
        String pname = new String(requestPname.getBytes("ISO-8859-1"), "UTF-8");

        System.out.println("pname" + pname);

        Product product = new Product();
        product.setPname(pname);

        ProductService productService = new ProductService();
        try {
            System.out.println("productService.checkProduct(product)" + productService.checkProduct(product));
            if (productService.checkProduct(product)) {
                //为真 表示查询到值  product已存在不能添加返回 0
                response.getWriter().println(0);
            } else {
                response.getWriter().println(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
