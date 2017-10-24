package at.htld.modules.handler;

import at.htld.common.DBConnection;
import at.htld.modules.entitiy.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler extends DBConnection {
    Connection con;
    User u;

    public DBHandler(){
        if(con == null){
            try {
                con = getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public User getUserByChatId(long chat_id) throws SQLException {
        PreparedStatement pstmt;
        User u = new User();
        ResultSet rset;
        DBHandler transFound = null;

        try {
            pstmt = con
                    .prepareStatement("select * from userdata WHERE chat_id =" + chat_id);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                u.setChat_id(rset.getLong("chat_id"));
                u.setVname(rset.getString("vname"));
                u.setNname(rset.getString("nname"));
                u.setKlass(rset.getString("klass"));
                u.setDstation(rset.getString("dstation"));

            } else {
                throw new SQLException("not data found for key " + chat_id);
            }
        } finally {

        }

        return u;
    }

    public void saveUser(User u){
        PreparedStatement pstmt;

        try {
            pstmt = con.prepareStatement("insert into userdata "
                    + " (chat_id,vname,nname,klass,dstation) values "
                    + " (?, ?, ?, ?, ?)");

            pstmt.setLong(1, u.getChat_id());
            pstmt.setString(2, u.getVname());
            pstmt.setString(3, u.getNname());
            pstmt.setString(4, u.getKlass());
            pstmt.setString(5, u.getDstation());
            pstmt.execute();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
