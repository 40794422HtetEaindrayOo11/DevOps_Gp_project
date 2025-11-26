package com.napier.gp_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    // Shared database connection for the entire application
    private static Connection con = null;
    Logger log = Logger.getLogger(App.class.getName());
    /**
     * Establishes a connection with the database
     * @param location Database hostname and port (e.g., "localhost:33060")
     * @param delay    Delay time before each retry (Not used correctly but kept for compatibility)
     * method:          -loads MySQL driver
     *                  - attempts connection up to 10 times
     *                  - wait 30 sec
     *                  - print information for debugging
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.fine("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            log.fine("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location
                                + "/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "example"
                );
                log.fine("Successfully connected");
                break;
            } catch (SQLException sqle) {
                if (log.isLoggable(Level.FINE)) {
                    int finalI = i;
                    log.fine(() -> "Failed to connect to database attempt " + finalI);
                    log.fine(() -> sqle.getMessage());
                }
            } catch (InterruptedException ie) {
                if (log.isLoggable(Level.FINE)) {
                    log.fine(() -> "Thread interrupted? Should not happen.");
                }
            }
        }
    }

    /**
     * Safely disconnect from the MySQL database.
     * This prevents memory leaks and ensures resources are properly released.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                log.fine("Disconnected from database.");
            } catch (Exception e) {
                log.fine("Error closing connection to database");
            }
        }
    }

    public Connection getConnection() {
        return con;
    }

    /**
     * Connects to the database.
     * Runs ALL 32 population, city, country, language and capital city reports
     * Prints results to the console
     * Disconnects from database
     * @param args
     */

    public static void main(String[] args)
    {
        // Connect using default arguments or CLI parameters
        App app = new App();
        if(args.length < 1){
            app.connect("localhost:33060", 30000);
        }else{
            app.connect(args[0], Integer.parseInt(args[1]));
        }

        //Capital City Reports
        Capital_city_reports capital_city_reports = new Capital_city_reports();
        Capital_city_reports.con = app.con;
        capital_city_reports.getAllCapitalCitiesInWorld();
        capital_city_reports.getAllCapitalCitiesInContinent("Asia");
        capital_city_reports.getAllCapitalCitiesInRegion("Caribbean");
        capital_city_reports.getTopNPopulatedCapitalCitiesInWorld(5);
        capital_city_reports.getTopNPopulatedCapitalCitiesInContinent("Asia",10);
        capital_city_reports.getTopNPopulatedCapitalCitiesInRegion(5,"Western Europe");

        //Country Reports
        Country_reports country_reports = new Country_reports();
        Country_reports.con = app.con;
        country_reports.getCountriesInWorld();
        country_reports.getCountriesByRegion("Southeast Asia");
        country_reports.getCountriesByContinent("Asia");
        country_reports.getTopNPopulatedCountriesInWorld(10);
        country_reports.getTopNPopulatedCountriesInContinent("Asia", 5);
        country_reports.getTopNPopulatedCountriesInRegion("Southeast Asia", 5);

        //City Reports
        CityReports cityReports = new CityReports();
        CityReports.con = app.con;
        cityReports.getCitiesInWorld();
        cityReports.getCitiesByContinent("Asia");
        cityReports.getCitiesByRegion("Middle East");
        cityReports.getTopNPopulatedCitiesInWorld(10);
        cityReports.getTopNPopulatedCitiesInRegion("Southeast Asia", 5);
        cityReports.getTopNPopulatedCitiesInContinent("Asia", 5);
        cityReports.getTopNPopulatedCitiesInDistrict("Michigan",5);
        cityReports.getCitiesByCountry("Myanmar");
        cityReports.getCitiesByDistrict("Kabol");
        cityReports.getTopNPopulatedCitiesInCountry("Myanmar", 5);

        //Language Reports
        LanguageReport lr = new LanguageReport(con);
        ArrayList<CountryLanguage> countryLanguages = lr.getLanguageReport();
        lr.printLanguageReport(countryLanguages);

        //Population Reports
        PopulationReport pr = new PopulationReport();
        pr.con = app.con;
        // --- World Population ---
        pr.getPopulationOfWorld();
        // --- Continent Population ---
        pr.getPopulationOfContinent();
        pr.getPopulationOfRegion();
        pr.getPopulationOfCountry();
        //        --- Population of the people who are living in cities and those who don't for Continent level ---
        ArrayList<Country> countries = pr.getConCityPopulation();
        pr.printConCityPopulation(countries);
        // --- Population of the people who are living in cities and those who don't for Region level ---
        ArrayList<Country> regionCountries = pr.getRegionCityPopulation();
        pr.printRegionCityPopulation(regionCountries);
        // --- Population of the people who are living in cities and those who don't for Country level ---
        ArrayList<Country> counCountry = pr.getCountryCityPopulation();
        pr.printCountryCityPopulation(counCountry);
        // --- Population of a city
        pr.getPopulationOfCity();
        // --- Population of a district
        pr.getPopulationOfDistrict();

        //Disconnect from DB
        app.disconnect();
    }

}
