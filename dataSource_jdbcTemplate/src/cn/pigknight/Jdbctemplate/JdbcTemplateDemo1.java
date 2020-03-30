package cn.pigknight.Jdbctemplate;

import cn.pigknight.datasource.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //3.调用方法
        String sql = "update account set name = 'wangwu' where id = ?";

        int count = template.update(sql, 3);
        System.out.println(count);
    }
}
