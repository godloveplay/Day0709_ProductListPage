package com.zmh.servlet;

import com.zmh.domian.Product;
import com.zmh.service.ProductService;
import com.zmh.utils.UUIDUtils;
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
 * Created by zmh on 2017/7/10.
 */
@WebServlet(name = "ProductAddServlet")
public class ProductAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 接收参数
         * 封装数据
         * 调用业务层
         * 页面跳转
         */

        //判断是否重复提交


        try {
            //解决POST提交中文参数乱码
            request.setCharacterEncoding("UTF-8");
            //获取令牌
            String token4Session = (String) request.getSession().getAttribute("token");
            String token4Form = request.getParameter("token");
            //移除session中的令牌
            request.getSession().removeAttribute("token");
            System.out.println("session:" + token4Session);
            System.out.println("session:" + token4Form);
            if (!token4Form.equals(token4Session)) {
                request.setAttribute("msg", "请不要重复提交");
                request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
                return;
            }
            //封装数据到product  Bean
            Map<String, String[]> map = request.getParameterMap();
            Product product = new Product();
            BeanUtils.populate(product, map);
            product.setPid(UUIDUtils.getUUID());
            product.setPdate(new Date());
            //调用业务层
            ProductService productService = new ProductService();
            productService.save(product);
            //页面跳转至 productList
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
