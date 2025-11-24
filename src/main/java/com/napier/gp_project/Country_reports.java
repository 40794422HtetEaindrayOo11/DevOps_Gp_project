package com.napier.gp_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class contains all reporting functionality related to Country data.
 * It connects to the database, executes SQL queries, retrieves results,
 * converts them into Country objects, and prints formatted reports.
 */
public class Country_reports {

    public static Connection con = null;
    /**
     * Retrieves and displays all countries in the world,
     * ordered by population from largest to smallest.
     *
     */
    public void getCountriesInWorld() {
        try {
            String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "ORDER BY Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<Country> countries = new ArrayList<>();

            // Collect data into Country objects
            while (rset.next()) {
                Country c = new Country();
                c.setCode(rset.getString("Code"));
                c.setName(rset.getString("Name"));
                c.setContinent(rset.getString("Continent"));
                c.setRegion(rset.getString("Region"));
                c.setPopulation(rset.getInt("Population"));
                c.setCapital(rset.getInt("Capital"));
                countries.add(c);
            }

            // Display report
            System.out.println("     All Countries in the World (Organised by Population - Largest to Smallest)");
            System.out.printf("%-5s %-45s %-20s %-25s %15s %10s%n",
                    "Code", "Name", "Continent", "Region", "Population", "Capital");

            for (Country c : countries) {
                System.out.printf("%-5s %-45s %-20s %-25s %,15d %10d%n",
                        c.getCode(), c.getName(), c.getContinent(), c.getRegion(),
                        c.getPopulation(), c.getCapital());
            }
        } catch (SQLException e) {
            System.out.println("Error generating country report: " + e.getMessage());
        }
    }

    /**
     * Retrieves and displays all countries from a specific region,
     * ordered by population from largest to smallest.
     *
     * @param regionName The region for which the report should be generated.
     */

    public void getCountriesByRegion(String regionName) {
        try {
            String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Region = ? " +
                    "ORDER BY Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, regionName);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country c = new Country();
                c.setCode(rset.getString("Code"));
                c.setName(rset.getString("Name"));
                c.setContinent(rset.getString("Continent"));
                c.setRegion(rset.getString("Region"));
                c.setPopulation(rset.getInt("Population"));
                c.setCapital(rset.getInt("Capital"));
                countries.add(c);
            }


            System.out.println("     All Countries in Region - " + regionName + " (Organised by Population - Largest to Smallest)");
            System.out.printf("%-5s %-45s %-20s %-25s %15s %10s%n",
                    "Code", "Name", "Continent", "Region", "Population", "Capital");

            for (Country c : countries) {
                System.out.printf("%-5s %-45s %-20s %-25s %,15d %10d%n",
                        c.getCode(), c.getName(), c.getContinent(), c.getRegion(),
                        c.getPopulation(), c.getCapital());
            }

        } catch (SQLException e) {
            System.out.println("Error generating country report by region: " + e.getMessage());
        }
    }

    /**
     * Retrieves all countries within a specified continent,
     * ordered by population from largest to smallest.
     *
     * @param continentName The continent for the report.
     */

    public void getCountriesByContinent(String continentName) {
        try {
            String sql = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Continent = ? " +
                    "ORDER BY Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, continentName);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country c = new Country();
                c.setCode(rset.getString("Code"));
                c.setName(rset.getString("Name"));
                c.setContinent(rset.getString("Continent"));
                c.setRegion(rset.getString("Region"));
                c.setPopulation(rset.getInt("Population"));
                c.setCapital(rset.getInt("Capital"));
                countries.add(c);
            }
            System.out.println("     All Countries in Continent - " + continentName +
                    " (Organised by Population - Largest to Smallest)");
            System.out.printf("%-5s %-45s %-20s %-25s %15s %10s%n",
                    "Code", "Name", "Continent", "Region", "Population", "Capital");

            for (Country c : countries) {
                System.out.printf("%-5s %-45s %-20s %-25s %,15d %10d%n",
                        c.getCode(), c.getName(), c.getContinent(), c.getRegion(),
                        c.getPopulation(), c.getCapital());
            }

        } catch (SQLException e) {
            System.out.println("Error generating country report by continent: " + e.getMessage());
        }
    }


    /**
     * Retrieves the top N most populated countries in the world.
     *
     * @param n Number of countries to return
     */
    public void getTopNPopulatedCountriesInWorld(int n) {
        try {
            String sql = "SELECT Name, Code, Continent, Region, Population " +
                    "FROM country " +
                    "ORDER BY Population DESC LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, n);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country c = new Country();
                c.setName(rset.getString("Name"));
                c.setCode(rset.getString("Code"));
                c.setContinent(rset.getString("Continent"));
                c.setRegion(rset.getString("Region"));
                c.setPopulation(rset.getInt("Population"));
                countries.add(c);
            }

            System.out.println("\nTop " + n + " Populated Countries in the World");
            System.out.printf("%-35s %-10s %-20s %-20s %15s%n",
                    "Country Name", "Code", "Continent", "Region", "Population");

            for (Country c : countries) {
                System.out.printf("%-35s %-10s %-20s %-20s %,15d%n",
                        c.getName(), c.getCode(), c.getContinent(), c.getRegion(), c.getPopulation());
            }

        } catch (SQLException e) {
            System.out.println("Error generating top N countries in world report: " + e.getMessage());
        }
    }

    /**
     * Retrieves the top N populated countries within a specific continent.
     *
     * @param continent The continent to filter
     * @param n Number of countries
     */
    public void getTopNPopulatedCountriesInContinent(String continent, int n) {
        try {
            String sql = "SELECT Name, Code, Continent, Region, Population " +
                    "FROM country " +
                    "WHERE Continent = ? " +
                    "ORDER BY Population DESC LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, continent);
            pstmt.setInt(2, n);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country c = new Country();
                c.setName(rset.getString("Name"));
                c.setCode(rset.getString("Code"));
                c.setContinent(rset.getString("Continent"));
                c.setRegion(rset.getString("Region"));
                c.setPopulation(rset.getInt("Population"));
                countries.add(c);
            }

            System.out.println("\nTop " + n + " Populated Countries in Continent: " + continent);
            System.out.printf("%-35s %-10s %-20s %-20s %15s%n",
                    "Country Name", "Code", "Continent", "Region", "Population");

            for (Country c : countries) {
                System.out.printf("%-35s %-10s %-20s %-20s %,15d%n",
                        c.getName(), c.getCode(), c.getContinent(), c.getRegion(), c.getPopulation());
            }

        } catch (SQLException e) {
            System.out.println("Error generating top N countries in continent report: " + e.getMessage());
        }
    }

    /**
     * Retrieves the top N populated countries within a specific region.
     *
     * @param region Region name
     * @param n Number of countries
     */
    public void getTopNPopulatedCountriesInRegion(String region, int n) {
        try {
            String sql = "SELECT Name, Code, Continent, Region, Population " +
                    "FROM country " +
                    "WHERE Region = ? " +
                    "ORDER BY Population DESC LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            pstmt.setInt(2, n);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country c = new Country();
                c.setName(rset.getString("Name"));
                c.setCode(rset.getString("Code"));
                c.setContinent(rset.getString("Continent"));
                c.setRegion(rset.getString("Region"));
                c.setPopulation(rset.getInt("Population"));
                countries.add(c);
            }

            System.out.println("\nTop " + n + " Populated Countries in Region: " + region);
            System.out.printf("%-35s %-10s %-20s %-20s %15s%n",
                    "Country Name", "Code", "Continent", "Region", "Population");

            for (Country c : countries) {
                System.out.printf("%-35s %-10s %-20s %-20s %,15d%n",
                        c.getName(), c.getCode(), c.getContinent(), c.getRegion(), c.getPopulation());
            }

        } catch (SQLException e) {
            System.out.println("Error generating top N countries in region report: " + e.getMessage());
        }
    }

}
