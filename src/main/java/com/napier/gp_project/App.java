package com.napier.gp_project;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.sql.*;

public  class App {

    public static Connection con = null;
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void main(String[] args)
    {


        App app = new App();
        app.connect();
        Capital_city_reports capital_city_reports = new Capital_city_reports();

        Capital_city_reports.con = app.con;
        capital_city_reports.getAllCapitalCitiesInWorld();
        capital_city_reports.getAllCapitalCitiesInContinent("Asia");
        capital_city_reports.getAllCapitalCitiesInRegion("Caribbean");
        capital_city_reports.getTopNPopulatedCapitalCitiesInWorld(5);
        capital_city_reports.getTopNPopulatedCapitalCitiesInContinent("Asia",10);
        app.disconnect();


    }

}

