/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnect {

    public static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/MADB_PRODUCT_2558";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "SuperAdmin007";

    public static final String SAP_CONNECTION_URL = "jdbc:sapdb://202.183.167.7/CGCDB2";
    public static final String SAP_USERNAME = "STRDBA";
    public static final String SAP_PASSWORD = "ACCOUNT1";

    public static final String MYSQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/genius_data";
    public static final String MYSQL_USERNAME = "cgc";
    public static final String MYSQL_PASSWORD = "systemadmin";
    
    public String db_postgres_jdbc = "jdbc:postgresql://";
    public String db_postgres_dbname = "";
    public String db_postgres_server = "";
    public String db_postgres_username = "";
    public String db_postgres_password = "";    

    /*public DBConnect() {
     }*/
    public Connection openNewConnection_Old() throws Exception {
        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        if (conn == null) {
            throw new SQLException("Cannot initail database connection, because it's NULL.");
        }
        //System.out.println("#: postgresql connection opened := "+conn);
        return conn;
    }

    public Connection openNewConnectionSAP() throws Exception {
        Connection conn = null;
        Class.forName("com.sap.dbtech.jdbc.DriverSapDB");
        conn = DriverManager.getConnection(SAP_CONNECTION_URL, SAP_USERNAME, SAP_PASSWORD);
        if (conn == null) {
            throw new SQLException("Cannot initail database connection, because it's NULL.");
        }
        //System.out.println("#: postgresql connection opened := "+conn);
        return conn;
    }

    public Connection openNewConnectionMySQL() throws Exception {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(MYSQL_CONNECTION_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        if (conn == null) {
            throw new SQLException("Cannot initail database connection, because it's NULL.");
        }
        //System.out.println("#: mysql connection opened := "+conn);
        return conn;
    }

    public void closeConnection(Connection conn) throws Exception {
        if (conn != null) {
            //System.out.println("#: postgresql connection {"+conn+"} has been droped.");
            conn.close();
        }
    }

    public Connection openNewConnection() throws Exception {
        Connection conn = null;

        Class.forName("org.postgresql.Driver");

        ReadPostgreSQLConfig();

        String CONNECTION_DB = db_postgres_jdbc + db_postgres_server + db_postgres_dbname;
        
        System.out.println("Uploads CONNECTION_DB = " + CONNECTION_DB);

        conn = DriverManager.getConnection(CONNECTION_DB, db_postgres_username, db_postgres_password);
        if (conn == null) {
            throw new SQLException("Cannot initial database connection, because it's NULL.");
        }

        return conn;
    }

    public void ReadPostgreSQLConfig() throws Exception {
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = new FileInputStream("config.properties");

            //System.out.println("INPUT = " + input) ;
            // load a properties file
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            //Get Properties Values
            db_postgres_dbname = prop.getProperty("db_postgres_dbname");
            db_postgres_server = prop.getProperty("db_postgres_server");
            db_postgres_username = prop.getProperty("db_postgres_username");
            db_postgres_password = prop.getProperty("db_postgres_password");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
