package com.zmh.servlet;

import com.zmh.domian.Product;
import com.zmh.service.ProductService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zmh on 2017/7/12.
 */
@WebServlet(name = "SearchForNameServlet")
public class SearchForNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String pname = request.getParameter("pname");

            ProductService productService = new ProductService();

            List<Product> list = productService.searchForName(pname);
//            JsonConfig config = new JsonConfig();
//            config.set
            JSONArray jsonArray = JSONArray.fromObject(list);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println(jsonArray.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
