package com.napier.gp_project;


import java.sql.*;
import java.util.ArrayList;


public  class App {

    private static Connection con = null;
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

        Country_reports country_reports = new Country_reports();
        Country_reports.con = app.con;
        country_reports.getCountriesInWorld();
        country_reports.getCountriesByRegion("Southeast Asia");
        country_reports.getCountriesByContinent("Asia");
        country_reports.getTopNPopulatedCountriesInWorld(10);
        country_reports.getTopNPopulatedCountriesInContinent("Asia", 5);
        country_reports.getTopNPopulatedCountriesInRegion("Southeast Asia", 5);

        CityReports cityReports = new CityReports();
        CityReports.con = app.con;
        cityReports.getCitiesInWorld();
        cityReports.getCitiesByContinent("Asia");
        cityReports.getCitiesByRegion("Middle East");
        cityReports.getTopNPopulatedCitiesInWorld(10);
        cityReports.getTopNPopulatedCitiesInRegion("Southeast Asia", 5);
        cityReports.getTopNPopulatedCitiesInContinent("Asia", 5);


        PopulationReport pr = new PopulationReport(con);
        // --- World Population ---
        pr.getPopulationOfWorld();  // call the method directly using the same instance
        // --- Continent Population ---
        pr.getPopulationOfContinent();  //  NEW METHOD
        // --- Population of the poeple who are living in cities and thos who don't for Continent level ---
        pr.getPopulationOfCountry();
        // --- Population of the poeple who are living in cities and those who don't for Continent level ---
        ArrayList<Country> countries = pr.getConCityPopulation();
        pr.printConCityPopulation(countries);
        // --- Population of the poeple who are living in cities and those who don't for Region level ---
        ArrayList<Country> regionCountries = pr.getRegionCityPopulation();
        pr.printRegionCityPopulation(regionCountries);
        // --- Population of the poeple who are living in cities and those who don't for Country level ---
        ArrayList<Country> counCountry = pr.getCountryCityPopulation();
        pr.printCountryCityPopulation(counCountry);

        country_reports.getCountriesInWorld();
        country_reports.getCountriesByRegion("Southeast Asia");
        country_reports.getTopNPopulatedCountriesInWorld(10);
        country_reports.getTopNPopulatedCountriesInContinent("Asia", 5);
        app.disconnect();

    }

}

