package at.htld.modules.handler;

import at.htld.common.DBConnection;
import at.htld.modules.entitiy.Station;
import at.htld.modules.entitiy.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

            } else {
               return null;
            }
        } finally {

        }

        return u;
    }

    public ArrayList<Station> getStationByChatId(long chat_id) throws SQLException {
        PreparedStatement pstmt;
        ArrayList<Station> stations = new ArrayList<Station>();
        ResultSet rset;
        DBHandler transFound = null;

        try {
            pstmt = con
                    .prepareStatement("select * from Station WHERE chat_id =" + chat_id );
            rset = pstmt.executeQuery();

            while (rset.next()) {
                Station station = new Station();

                station.setChat_id(rset.getLong("chat_id"));
                station.setS_id(rset.getInt("s_id"));
                station.setDstation(rset.getString("dstation"));
                station.setSlink(rset.getString("slink"));
                stations.add(station);
            }
        } finally {

        }

        return stations;
    }

    public String getStationLinkById(int sid) throws SQLException {
        PreparedStatement pstmt;
        String url = "";
        ResultSet rset;
        DBHandler transFound = null;

        try {
            pstmt = con
                    .prepareStatement("select slink from Station WHERE s_id =" + sid );
            rset = pstmt.executeQuery();


            if(rset.next()) {
                url = rset.getString("slink");
            }
        } finally {

        }

        return url;
    }

    public void saveUser(User u){
        PreparedStatement pstmt;

        try {
            pstmt = con.prepareStatement("insert into userdata "
                    + " (chat_id,vname,nname,klass) values "
                    + " (?, ?, ?, ?)");

            pstmt.setLong(1, u.getChat_id());
            pstmt.setString(2, u.getVname());
            pstmt.setString(3, u.getNname());
            pstmt.setString(4, u.getKlass());
            pstmt.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void saveStation(Station s){
        PreparedStatement pstmt;

        try{
            pstmt = con.prepareStatement("insert into station (chat_id, dstation, slink) values(?,?,?)");

            pstmt.setLong(1, s.getChat_id());
            pstmt.setString(2, s.getDstation());
            pstmt.setString(3, s.getSlink());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
