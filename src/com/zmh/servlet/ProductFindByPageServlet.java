package com.zmh.servlet;

import com.zmh.domian.PageBean;
import com.zmh.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by zmh on 2017/7/13.
 */
@WebServlet(name = "ProductFindByPageServlet")
public class ProductFindByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //接受参数
            System.out.println("ProductFindByPageServlet。。。。。。。");
            int currPage = Integer.parseInt(request.getParameter("currPage"));

            ProductService productService = new ProductService();

            PageBean pageBean = productService.findByPage(currPage);
            System.out.println(pageBean);
            request.setAttribute("pageBean", pageBean);

            request.getRequestDispatcher("/jsp/product_page.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
