package cn.pigknight.jdbc;

import cn.pigknight.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务操作，转账
 */
public class JdbcDemo7 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //定义sql
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";
            //获取执行sql对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //设置参数
            pstmt1.setFloat(1,500);
            pstmt1.setInt(2,1);
            pstmt2.setFloat(1,500);
            pstmt2.setInt(2,2);
            //执行sql
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            //提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            try {
                if(conn!=null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            JDBCUtils.close(pstmt1,conn);
            JDBCUtils.close(pstmt2,null);
        }
    }
}
