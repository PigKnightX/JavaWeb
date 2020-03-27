package cn.pigknight.jdbc;

import cn.pigknight.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 练习：
 *      1.通过键盘录入用户名和密码
 *      2.判断用户是否登录成功
 *          select * from user where username="" and password = "";
 *          如果sql有这个查询结果，则成功，反之，则失败
 * 步骤1.创建数据库表 user (id,username,password)
 */
public class JdbcDemo6 {

    public static void main(String[] args) {
        //1.键盘录入用户名和密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名： ");
        String username = sc.nextLine();
        System.out.println("请输入密码： ");
        String password = sc.nextLine();
        //2.调用方法
        boolean flag = new JdbcDemo6().login2(username, password);
        //3.判断结果，输出不同语句
        if(flag){
            System.out.println("登录成功");
        }else {
            System.out.println("用户名或密码错误！");
        }
    }

    /**
     * 登录方法
     */
    public boolean login(String username,String password){
        if(username == null || password == null){
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
            //3.获取执行sql的对象
            stmt = conn.createStatement();
            //4.执行查询
            rs = stmt.executeQuery(sql);
            //5.判断
//            if (rs.next()){
//                return true;
//            }else {
//                return false;
//            }
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);
        }

        return false;
    }


    /**
     * 登录方法,使用PreparedStatement
     */
    public boolean login2(String username,String password){
        if(username == null || password == null){
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = ? and password = ?";
            //3.获取执行sql的对象
            //stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //4.执行查询,不需要传参
            rs = pstmt.executeQuery();
            //5.判断
//            if (rs.next()){
//                return true;
//            }else {
//                return false;
//            }
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }

        return false;
    }
}
