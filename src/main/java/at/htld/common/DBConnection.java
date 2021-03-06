package at.htld.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * DB-Hilfmethoden
 *
 * <pre>
 * 		2017-10-12 KAUF initial version
 * </pre>
 */
public class DBConnection {

    private final String DB_DATABASE_NAME = "HTLDBot";
    private final String DB_USER_NAME = "htld_bot_user";
    private final String DB_USER_PASSWD = "12345";

    private Date datum;

    /**
     * Liefert eine DB Connection auf die mysql-DB
     *
     * @return DB Connection
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;

        StringBuilder sbBuilder = new StringBuilder("jdbc:mysql://localhost:3306/");
        sbBuilder.append(DB_DATABASE_NAME).append("?user=")
                .append(DB_USER_NAME).append("&password=")
                .append(DB_USER_PASSWD)
                .append("&useSSL=").append("false")
                .append("&useUnicode=true&characterEncoding=UTF-8")
                .append("&serverTimezone=").append("GMT");

        conn = DriverManager.getConnection(sbBuilder.toString());

        return conn;

    }
}