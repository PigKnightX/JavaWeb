package cn.pigknight.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class c3p0Demo2 {
    public static void main(String[] args) throws SQLException {
        //1.获取DataSource
        DataSource ds = new ComboPooledDataSource();
        //2.
        Connection conn = ds.getConnection();

    }
}
