package com.zmh.service;

import com.zmh.dao.ProductDao;
import com.zmh.domian.Product;

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
}
