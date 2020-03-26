package cn.pigknight.jdbc;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * 数据库中增加一条数据
 */
public class JdbcDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123456");
            //3.定义sql语句
            String sql = "insert into Pokemon_info values(5,'火恐龙')";
            //4.获取执行语句statement对象
            stmt = conn.createStatement();
            //5.执行语句
            int count = stmt.executeUpdate(sql);
            //6.处理结果
            System.out.println(count);
            if (count > 0){
                System.out.println("添加成功！ ");
            }else {
                System.out.println("添加失败！ ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //7.释放资源
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
