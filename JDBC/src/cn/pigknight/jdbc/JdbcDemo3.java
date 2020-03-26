package cn.pigknight.jdbc;

import java.sql.*;

/**
 * 查询语句
 */
public class JdbcDemo3 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123456");
            //3.定义sql语句
            String sql = "select * from Pokemon_info";
            //4.获取执行语句statement对象
            stmt = conn.createStatement();
            //5.执行语句
            rs = stmt.executeQuery(sql);
            //6.处理结果
            //6.1 让游标向下移动一行
            //6.2获取数据

            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString("name");

                System.out.println(id +"---" + name);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //7.释放资源
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
