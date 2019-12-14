package utils;

import java.sql.*;

/**
 * 数据库操作公共类
 *
 * @author OK绷38号
 * @version 1.0
 */
public final class ConnectionUtil {

    private static String url = "jdbc:mysql://localhost:3306/image_test?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";

    private static String user = "root";

    private static String password = "666";

    private ConnectionUtil() {}

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类，加载驱动失败。");
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库连接
     * @return
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("创建数据库连接失败。");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放数据库资源
     * @param rs ResultSet
     * @param stmt Statement
     * @param conn Connection
     * @param pstmt PreparedStatement
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn, PreparedStatement pstmt) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                }
        }
    }

}
