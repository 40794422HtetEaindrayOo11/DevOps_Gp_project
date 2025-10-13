package com.napier.gp_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    /**
     * Connection to MySQL database.
     */
    public static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver.");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for the database to start
                Thread.sleep(30000);

                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "example"
                );
                System.out.println("Successfully connected to the database!");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database, attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted during connection wait.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Disconnected from the database.");
            } catch (Exception e) {
                System.out.println("Error closing database connection.");
            }
        }
    }


    public static void main(String[] args) {
        App app = new App();
        app.connect();

        CityReports cityReports = new CityReports();

        CityReports.con = app.con;

        cityReports.getCitiesInWorld();
        cityReports.getTopNPopulatedCitiesInWorld(10);

        app.disconnect();
    }
}
