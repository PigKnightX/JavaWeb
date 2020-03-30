package cn.pigknight.Jdbctemplate;

import cn.pigknight.datasource.domain.account;
import cn.pigknight.datasource.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//Junit单元测试
public class JdbcTemplateDemo2 {


    //1.获取Template对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 修改1号数据的balance为10000
     */
    @Test
    public void test1(){
        //1.获取Template对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //2.定义sql
        String sql = "update account set balance = 10000 where id = 1";
        //3.执行sql
        int count = template.update(sql);
        System.out.println(count);
    }

    /**
     * 查询id 为1的记录，将其封装为map集合
     */
    @Test
    public void test2(){
        String sql ="select * from account where id = ?";
        Map<String, Object> map = template.queryForMap(sql,2);//结果集长度只能是1
        System.out.println(map);
    }

    /**
     * 查询所有记录将其封装为List
     */
    @Test
    public void test3(){
        String sql = "select * from account";
        List<Map<String, Object>> list = template.queryForList(sql);//将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
        for(Map<String,Object> stringObjectMap:list){
            System.out.println(stringObjectMap);
        }

    }

    /**
     * 查询所有记录，封装为account 对象，再装载到list集合
     */
    @Test
    public void test4(){
        String sql = "select * from account";
        List<account> list = template.query(sql, new RowMapper<account>() {

            @Override
            public account mapRow(ResultSet rs, int i) throws SQLException {
                account oneaccount = new account();
                oneaccount.setId(rs.getInt("id"));
                oneaccount.setName(rs.getString("name"));
                oneaccount.setBalance(rs.getFloat("balance"));

                return oneaccount;
            }
        });
        for(account a: list){
            System.out.println(a);
        }
    }


    /**
     * 查询所有记录，封装为account 对象，再装载到list集合
     */
    @Test
    public void test5() {
        String sql = "select * from account";
        List<account> list = template.query(sql,new BeanPropertyRowMapper<account>(account.class));

        for (account a : list) {
            System.out.println(a);
        }
    }

    /**
     * 查询总记录数
     */
    @Test
    public void test7(){
        String sql = "select count(id) from account";
        Long total = template.queryForObject(sql, Long.class);//一般用来实现聚合函数的
        System.out.println(total);
    }

}
