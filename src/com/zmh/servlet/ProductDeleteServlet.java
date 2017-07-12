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
@WebServlet(name = "ProductDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取参数
            String pid = request.getParameter("pid");

            Product product = new Product();
            product.setPid(pid);
            //调用服务层 删除方法
            ProductService productService = new ProductService();

            productService.deleteProduct(product);

            request.getRequestDispatcher("/Servlet/ProductFindAllServlet").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
