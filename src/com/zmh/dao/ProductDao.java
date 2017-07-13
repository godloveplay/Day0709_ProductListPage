package com.zmh.dao;

import com.zmh.domian.PageBean;
import com.zmh.domian.Product;
import com.zmh.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zmh on 2017/7/10.
 */
public class ProductDao {
    public List<Product> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM product ORDER BY pdate DESC";
        List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));

        return list;
    }

    public void save(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid()};
        queryRunner.update(sql, params);
    }

    /**
     * 如果查询到值 返回 true
     * 没有查询到值 返回 false
     *
     * @param product
     * @return
     * @throws SQLException
     */
    public boolean checkProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE pname = ?";
        Product product1 = queryRunner.query(sql, new BeanHandler<>(Product.class), product.getPname());
        return product1 == null ? false : true;
    }

    public Product searchProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE pid = ? ORDER BY pdate DESC";
        Product product1 = queryRunner.query(sql, new BeanHandler<>(Product.class), product.getPid());
        return product1;
    }

    public void updateProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "UPDATE product SET pname = ?,market_price= ?,shop_price=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? WHERE pid = ?";
        Object[] params = {product.getPname(), product.getMarket_price(), product.getShop_price(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid(), product.getPid()};
        queryRunner.update(sql, params);
    }

    public void deleteProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "DELETE FROM product WHERE pid = ?";
        queryRunner.update(sql, product.getPid());
    }

    @Test
    public void test() {
        Product p = new Product();
        p.setPname("kkk");
        try {
            System.out.println(new ProductDao().checkProduct(p));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Connection connection, String s) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "DELETE FROM product WHERE pid = ?";
        queryRunner.update(connection, sql, s);
    }

    //模糊查询 用名字
    public List<Product> searchForName(String pname) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT * FROM product WHERE pname LIKE ? LIMIT 5";
        List<Product> list = queryRunner.query(sql, new BeanListHandler<>(Product.class), pname + "%");
        return list;
    }

    public int findCount() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "SELECT count(*) FROM product";
        Long count = queryRunner.query(sql, new ScalarHandler<>());
        return count.intValue();
    }

    public List<Product> findByPage(int begin, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        //LIMIT 接受一个或两个数字参数。参数必须是一个整数常量。如果给定两个参数，第一个参数指定第一个返回记录行的偏移量，第二个参数指定返回记录行的最大数目。初 始记录行的偏移量是 0(而不是 1)
        String sql = "SELECT * FROM product ORDER BY pdate DESC LIMIT ?,?";
        Object[] params = {begin, pageSize};
        List<Product> list = queryRunner.query(sql, new BeanListHandler<>(Product.class), params);
        return list;
    }
}
