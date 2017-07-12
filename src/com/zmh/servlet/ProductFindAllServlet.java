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
import java.util.List;

/**
 * Created by zmh on 2017/7/10.
 */
@WebServlet(name = "ProductFindAllServlet")
public class ProductFindAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //调用 Service层 查询所有商品 返回值 List<Product>
            ProductService productService = new ProductService();
            List<Product> list = productService.findAll();
            System.out.println("zzzzzzzzzmhafkgjslfhasdjlhfjksdklfshdjklafhsdjkf");
            request.setAttribute("listOfProducts",list);
            request.getRequestDispatcher("/jsp/product_list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
