package cn.pigknight.jdbc;

import cn.pigknight.domain.Pokemon;
import cn.pigknight.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo5 {
    public static void main(String[] args) {
        List<Pokemon> list = new JdbcDemo5().findAll();
        System.out.println(list);
        System.out.println(list.size());
    }

    /**
     * 查询语句，使用JDBC工具类（JDBCUtils）
     * @return
     */
    public List<Pokemon> findAll(){
        Connection conn = null;
        Statement stmt =null;
        ResultSet rs = null;
        List<Pokemon> list = null;
        try {
           /** //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123456");*/
            conn = JDBCUtils.getConnection();
            //3.定义sql
            String sql = "select * from Pokemon_info";
            //4.获取执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.遍历结果集，封装对象，装载集合
            list = new ArrayList<>();
            while (rs.next()){
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                //创建Pokemon对象
                Pokemon pokemon = new Pokemon();
                pokemon.setId(id);
                pokemon.setName(name);
                //装载集合
                list.add(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            /** if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
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
            }*/
            JDBCUtils.close(rs,stmt,conn);
        }
        return list;
    }
}
