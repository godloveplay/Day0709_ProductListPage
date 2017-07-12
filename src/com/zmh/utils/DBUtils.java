package com.zmh.utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private static ComboPooledDataSource DATA_SOURCE = new ComboPooledDataSource();

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
