package org.sourceit.db.DAO.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum MySQLConnection {
    /**
     *
     */
    INSTANCE;

    private Connection connection;

    MySQLConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e){
            throw  new RuntimeException("no class in classpath");
        }
        try {


            connection = DriverManager.getConnection("jdbc:mysql://localhost/PERSONS_DATABASE?user=root&password=Toha19961004");
        }catch (SQLException e){
            throw  new RuntimeException("somthing wrong",e);
        }

    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
