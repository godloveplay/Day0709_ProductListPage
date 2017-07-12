package com.zmh.servlet;

import com.zmh.domian.Product;
import com.zmh.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * Created by zmh on 2017/7/11.
 */
@WebServlet(name = "ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            Map<String, String[]> map = request.getParameterMap();
            Product product = new Product();

            BeanUtils.populate(product, map);
            //更新时间
            product.setPdate(new Date());
            //业务层 处理数据
            ProductService productService = new ProductService();
            productService.updateProduct(product);
            //转发到ProductList
            request.getRequestDispatcher("/Servlet/ProductFindAllServlet").forward(request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
