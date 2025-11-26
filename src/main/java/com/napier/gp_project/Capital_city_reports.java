package com.napier.gp_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides all report-generation methods related to **capital cities**.
 * It retrieves data from the `world` MySQL database using SQL queries
 * and prints formatted population reports.
 */
public class Capital_city_reports {
    // Holds the result of SQL queries
    public ResultSet rset;

    // Shared database connection (passed from App.java)
    public static Connection con = null;
    Logger log = Logger.getLogger(Capital_city_reports.class.getName());

    /**
     * Retrieves and prints **all capital cities in the world**
     * sorted by population from largest to smallest.
     */
    public void getAllCapitalCitiesInWorld() {
        try {
            String sql = "SELECT city.Name AS CapitalName, " +
                    "country.Name AS CountryName, " +
                    "city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC";

            // Prepare and execute SQL statement
            PreparedStatement pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();

            ArrayList<City> capitals = new ArrayList<>();

            // Loop through results and build City objects
            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CapitalName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setPopulation(rset.getInt("Population"));
                capitals.add(c);
            }

            // Output report header
            System.out.println("\nAll Capital Cities in the World (Organised by Population - Largest to Smallest)");
            System.out.printf("%-35s %-25s %15s%n", "Capital Name", "Country", "Population");

            // Output each city in formatted table
            for (City c : capitals) {
                System.out.printf("%-35s %-25s %,15d%n",
                        c.getName(),
                        c.getCountryCode(),
                        c.getPopulation());
            }
        }
        catch (SQLException e) {
            if (log.isLoggable(Level.ALL)) {
                log.fine("Error generating capital city report: " + e.getMessage());
            }
        }
    }

    /**
     * Retrieves and prints all capital cities within a **specific continent**.
     *
     * @param continent The continent to filter results by.
     */
    public void getAllCapitalCitiesInContinent(String continent) {
        try {
            String sql = "SELECT city.Name AS CapitalName, " +
                    "country.Name AS CountryName, " +
                    "city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY city.Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, continent);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<City> capitals = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CapitalName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setPopulation(rset.getInt("Population"));
                capitals.add(c);
            }

            System.out.println("\nAll Capital Cities in " + continent + " (Organised by Population - Largest to Smallest)");
            System.out.printf("%-35s %-25s %15s%n", "Capital Name", "Country", "Population");

            for (City c : capitals) {
                System.out.printf("%-35s %-25s %,15d%n",
                        c.getName(),
                        c.getCountryCode(),
                        c.getPopulation());
            }
        }
        catch (SQLException e) {
            System.out.println("Error generating capital city report for " + continent + ": " + e.getMessage());
        }
    }

    /**
     * Retrieves and prints all capital cities for a **specific region**.
     *
     * @param region Region name (e.g., "Caribbean", "Western Europe")
     */
    public void getAllCapitalCitiesInRegion(String region) {
        try {
            String sql = "SELECT city.Name AS CapitalName, " +
                    "country.Name AS CountryName, " +
                    "city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Region = ? " +
                    "ORDER BY city.Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<City> capitals = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CapitalName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setPopulation(rset.getInt("Population"));
                capitals.add(c);
            }

            System.out.println("\nAll Capital Cities in " + region + " (Organised by Population - Largest to Smallest)");
            System.out.printf("%-35s %-25s %15s%n", "Capital Name", "Country", "Population");

            for (City c : capitals) {
                System.out.printf("%-35s %-25s %,15d%n",
                        c.getName(),
                        c.getCountryCode(),
                        c.getPopulation());
            }
        }
        catch (SQLException e) {
            System.out.println("Error generating capital city report for " + region + ": " + e.getMessage());
        }
    }
    /**
     * Retrieves the **top N most populated capital cities in the world**.
     *
     * @param n Number of cities to return.
     */
    public void getTopNPopulatedCapitalCitiesInWorld(int n) {
        try {
            String sql = "SELECT city.Name AS CapitalName, " +
                    "country.Name AS CountryName, " +
                    "city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, n);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<City> capitals = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CapitalName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setPopulation(rset.getInt("Population"));
                capitals.add(c);
            }

            System.out.println("\nTop " + n + " Populated Capital Cities in the World (Largest to Smallest)");
            System.out.printf("%-35s %-25s %15s%n", "Capital Name", "Country", "Population");

            for (City c : capitals) {
                System.out.printf("%-35s %-25s %,15d%n",
                        c.getName(),
                        c.getCountryCode(),
                        c.getPopulation());
                }
        }
        catch (SQLException e) {
            System.out.println("Error generating top N capital city report: " + e.getMessage());}
     }

    /**
     * Retrieves top N capital cities in a **particular continent**.
     *
     * @param continent Continent filter
     * @param n Number of results
     */
    public void getTopNPopulatedCapitalCitiesInContinent(String continent, int n) {
        try {
            String sql = "SELECT city.Name AS CapitalName, " +
                    "country.Name AS CountryName, " +
                    "city.District AS District, " +
                    "city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, continent);
            pstmt.setInt(2, n);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<City> capitals = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CapitalName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setDistrict(rset.getString("District"));
                c.setPopulation(rset.getInt("Population"));
                capitals.add(c);
            }

            System.out.println("\nTop " + n + " Populated Capital Cities in " + continent + " (Largest to Smallest)");
            System.out.printf("%-30s %-25s %-20s %15s%n", "Capital Name", "Country", "District", "Population");

            for (City c : capitals) {
                System.out.printf("%-30s %-25s %-20s %,15d%n",
                        c.getName(),
                        c.getCountryCode(),
                        c.getDistrict(),
                        c.getPopulation());
            }
        }
        catch (SQLException e) {
            System.out.println("Error generating top N capital city in " + continent + "report: " + e.getMessage());}
    }

    /**
     * Retrieves top N most populated capital cities for a **specific region**.
     */
    public void getTopNPopulatedCapitalCitiesInRegion(int n, String region) {
        try {
            String sql = "SELECT city.Name AS CapitalName, " +
                    "country.Name AS CountryName, " +
                    "city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Region = ? " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            pstmt.setInt(2, n);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<City> capitals = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CapitalName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setPopulation(rset.getInt("Population"));
                capitals.add(c);
            }

            System.out.println("\nTop " + n + " Populated Capital Cities in " + region + " (Largest to Smallest)");
            System.out.printf("%-35s %-25s %15s%n", "Capital Name", "Country", "Population");

            for (City c : capitals) {
                System.out.printf("%-35s %-25s %,15d%n",
                        c.getName(),
                        c.getCountryCode(),
                        c.getPopulation());
            }
        }
        catch (SQLException e) {
            System.out.println("Error generating top N capital city report for " + region + ": " + e.getMessage());
        }
    }

}
