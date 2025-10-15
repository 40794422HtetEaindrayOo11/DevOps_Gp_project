package com.napier.gp_project;

import java.sql.*;
import java.util.ArrayList;

public class App {

    private static Connection con = null;

    /**
     * Establishes a connection with the database
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "example"
                );
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
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
                System.out.println("Disconnected from database.");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void main(String[] args) {
        App a = new App();
        a.connect();

        // --- 1. Population by Continent/City ---
        PopulationReport pr = new PopulationReport(con);
        ArrayList<Country> countries = pr.getConCityPopulation();

        // --- 2. World Population ---
        pr.getPopulationOfWorld();  // ✅ call the method directly using the same instance

        // --- 3. Print Continent-City Population Report ---
        pr.printConCityPopulation(countries);

        // --- 3. Continent Population ---
        pr.getPopulationOfContinent();  // ✅ NEW METHOD

        // --- 4. Population of each Region ---
        pr.getPopulationOfRegion();

        // --- 5. Population of each Country ---
        pr.getPopulationOfCountry();
        a.disconnect();
    }
}
