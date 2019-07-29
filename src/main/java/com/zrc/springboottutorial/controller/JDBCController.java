package com.zrc.springboottutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/29 9:36
 * Description: No Description
 */
@RestController
public class JDBCController {

    // 内置的DataSource 直接根据配置文件 application.properties 里面获取的。
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/jdbc")
    public Map<String, Object> hello() {
        Connection conn = null;
        PreparedStatement stmt = null; // Or PreparedStatement if needed
        ResultSet rs = null;
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            conn = dataSource.getConnection();

            stmt = conn.prepareStatement("SELECT NAME FROM SYS_USER WHERE ID = ?");
            stmt.setString(1, "190709BX03DMZ25P");
            rs = stmt.executeQuery();

            while (rs.next()) {
                result.put("Name", rs.getString(1));
                break;
            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
            conn.close();
            conn = null;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    ;
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    ;
                }
                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    ;
                }
                conn = null;
            }
        }

        return result;
    }

    @GetMapping("/jdbc2")
    public Map<String, Object> hello2() {
        Map<String, Object> result = new HashMap<String, Object>();
        String sql = "select count(*) from SYS_USER";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        result.put("Count", count);

        return result;
    }
}
