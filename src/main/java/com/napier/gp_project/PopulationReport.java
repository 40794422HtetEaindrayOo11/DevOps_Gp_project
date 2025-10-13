package com.napier.gp_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PopulationReport {
    private Connection con;  // store the connection

    // Constructor that accepts the connection
    public PopulationReport(Connection con) {
        this.con = con;
    }

    /**
     * Fetching the required data from database
     */
    public ArrayList<Country> getConCityPopulation() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT cn.Continent, SUM(cn.Population) AS TOTAL_POPULATION, " +
                            "SUM(ct.Population) AS CITY_POPULATION, " +
                            "(SUM(cn.Population)-SUM(ct.Population)) AS NON_CITY_POPULATION " +
                            "FROM country cn " +
                            "LEFT JOIN (" +
                            " SELECT CountryCode, SUM(Population) AS Population " +
                            " FROM city " +
                            " GROUP BY CountryCode " +
                            ") ct ON cn.Code = ct.CountryCode " +
                            "GROUP BY cn.Continent " +
                            "ORDER BY TOTAL_POPULATION DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country country = new Country();
                country.setContinent(rset.getString("Continent"));
                country.setTotalPopulation(rset.getLong("TOTAL_POPULATION"));
                country.setCityPopulation(rset.getLong("CITY_POPULATION"));
                country.setNonCityPopulation(rset.getLong("NON_CITY_POPULATION"));
                countries.add(country);
            }

            rset.close();
            stmt.close();
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Printing out the query result in a table format
     */
    public void printConCityPopulation(ArrayList<Country> countries) {
        System.out.println(String.format("%-15s %-20s %-20s %-20s",
                "Continent", "Total Population", "City Population", "Non City Population"));

        for (Country c : countries) {
            System.out.println(String.format("%-15s %-20s %-20s %-20s",
                    c.getContinent(), c.getTotalPopulation(), c.getCityPopulation(), c.getNonCityPopulation()));
        }
    }

    /**
     * Fetch and print the population of the world
     */
    public void getPopulationOfWorld() {
        try {
            String sql = "SELECT SUM(Population) AS WorldPopulation FROM country";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            long worldPopulation = 0;
            if (rset.next()) {
                worldPopulation = rset.getLong("WorldPopulation");
            }

            System.out.println("\nThe Population of the World");
            System.out.printf("%-25s %15s%n", "Description", "Population");
            System.out.printf("%-25s %,15d%n", "World", worldPopulation);

            rset.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error generating world population report: " + e.getMessage());
        }
    }
    /**
     * Fetch and print the population of each continent
     */
    public void getPopulationOfContinent() {
        try {
            String sql = "SELECT Continent, SUM(Population) AS ContinentPopulation " +
                    "FROM country " +
                    "GROUP BY Continent " +
                    "ORDER BY ContinentPopulation DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            System.out.println("\nThe Population of Each Continent");
            System.out.printf("%-25s %15s%n", "Continent", "Population");

            while (rset.next()) {
                String continent = rset.getString("Continent");
                long population = rset.getLong("ContinentPopulation");

                System.out.printf("%-25s %,15d%n", continent, population);
            }

            rset.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error generating continent population report: " + e.getMessage());
        }
    }

}
