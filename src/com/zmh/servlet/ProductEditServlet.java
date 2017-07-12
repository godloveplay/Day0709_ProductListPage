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
@WebServlet(name = "ProductEditServlet")
public class ProductEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        try {
            Product product = new Product();
            product.setPid(pid);
            System.out.println("pid" +
                    pid);
            ProductService productService = new ProductService();

            Product exitProduct = productService.searchProduct(product);
            System.out.println(exitProduct);
            //将 返回的product 插入request域 中转发
            request.setAttribute("Product", exitProduct);
            request.getRequestDispatcher("/jsp/editProduct.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
