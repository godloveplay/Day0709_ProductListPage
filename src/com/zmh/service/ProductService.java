package com.zmh.service;

import com.zmh.dao.ProductDao;
import com.zmh.domian.PageBean;
import com.zmh.domian.Product;
import com.zmh.utils.DBUtils;
import org.apache.commons.dbutils.DbUtils;
import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zmh on 2017/7/10.
 */
public class ProductService {
    public List<Product> findAll() throws SQLException {
        return new ProductDao().findAll();
    }

    public void save(Product product) throws SQLException {
        new ProductDao().save(product);
    }

    public boolean checkProduct(Product product) throws SQLException {
        return new ProductDao().checkProduct(product);
    }

    public Product searchProduct(Product product) throws SQLException {
        return new ProductDao().searchProduct(product);
    }

    public void updateProduct(Product product) throws SQLException {
        new ProductDao().updateProduct(product);
    }

    public void deleteProduct(Product product) throws SQLException {
        new ProductDao().deleteProduct(product);
    }

    public void deleteAll(String[] ids) {
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
            ProductDao productDao = new ProductDao();
            for (String s : ids) {
                productDao.deleteProduct(connection, s);
            }
            DbUtils.commitAndCloseQuietly(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            DbUtils.rollbackAndCloseQuietly(connection);
        }
    }

    public List<Product> searchForName(String pname) throws SQLException {
        return new ProductDao().searchForName(pname);
    }

    public PageBean findByPage(int currPage) throws SQLException {
        PageBean pageBean = new PageBean();
        ProductDao productDao = new ProductDao();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(5);
        int totalCount = productDao.findCount();
        pageBean.setTotalCount(totalCount);
        double tc = totalCount;
        Double num = Math.ceil(tc / 5);
        pageBean.setTotalPage(num.intValue());
        int begin = (currPage - 1) * 5;
        List<Product> list = productDao.findByPage(begin, 5);
        pageBean.setList(list);

        return pageBean;
    }
}
