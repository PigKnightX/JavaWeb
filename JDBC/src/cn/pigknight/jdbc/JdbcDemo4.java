package cn.pigknight.jdbc;

import cn.pigknight.domain.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo4 {

    public static void main(String[] args) {
        List<Pokemon> list = new JdbcDemo4().findAll();
        System.out.println(list);
    }
    /**
     * 查询所有对象
     * @return 表数据对象集合
     */
    public List<Pokemon> findAll(){
        Connection conn = null;
        Statement stmt =null;
        ResultSet rs = null;
        List<Pokemon> list = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123456");
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
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
            }
        }
        return list;
    }
}
