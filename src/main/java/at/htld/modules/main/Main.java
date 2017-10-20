package at.htld.modules.main;

import at.htld.common.DBConnection;

import java.sql.SQLException;

public class Main {

    public static void main (String[] args){
        DBConnection dbc = new DBConnection();
        try {
            dbc.testcon();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
