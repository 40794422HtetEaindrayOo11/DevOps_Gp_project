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
                    "SELECT cn.Continent, " +
                            "SUM(cn.Population) AS TOTAL_POPULATION, " +
                            "SUM(IFNULL(ct.Population, 0)) AS CITY_POPULATION, " +
                            "ROUND(SUM(IFNULL(ct.Population, 0)) * 100 / SUM(cn.Population), 2) AS CITY_PERCENTAGE, " +
                            "(SUM(cn.Population) - SUM(IFNULL(ct.Population, 0))) AS NON_CITY_POPULATION, " +
                            "ROUND((SUM(cn.Population) - SUM(IFNULL(ct.Population, 0))) * 100 / SUM(cn.Population), 2) AS NON_CITY_PERCENTAGE " +
                            "FROM country cn " +
                            "LEFT JOIN ( " +
                            "   SELECT CountryCode, SUM(Population) AS Population " +
                            "   FROM city " +
                            "   GROUP BY CountryCode " +
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
                country.setCityPercentage(rset.getFloat("CITY_PERCENTAGE"));
                country.setNonCityPopulation(rset.getLong("NON_CITY_POPULATION"));
                country.setNonCityPercentage(rset.getFloat("NON_CITY_PERCENTAGE"));
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
        System.out.println("\nContinent population report for those who live in cities and those who don't");
        System.out.println(String.format("%-15s %-20s %-20s %-20s %-20s %-20s",
                "Continent", "Total Population", "City Population","City percentage",
                "Non City Population", "Non City percentage"));

        for (Country c : countries) {
            System.out.println(String.format("%-15s %-20s %-20s %-20s %-20s %-20s",
                    c.getContinent(), c.getTotalPopulation(), c.getCityPopulation(), c.getCityPercentage(),
                    c.getNonCityPopulation(), c.getNonCityPercentage()));
        }
    }

    public ArrayList<Country> getRegionCityPopulation() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT cn.Region, " +
                            "SUM(cn.Population) AS TOTAL_POPULATION, " +
                            "SUM(IFNULL(ct.Population, 0)) AS CITY_POPULATION, " +
                            "ROUND(SUM(IFNULL(ct.Population, 0)) * 100 / SUM(cn.Population), 2) AS CITY_PERCENTAGE, " +
                            "(SUM(cn.Population) - SUM(IFNULL(ct.Population, 0))) AS NON_CITY_POPULATION, " +
                            "ROUND((SUM(cn.Population) - SUM(IFNULL(ct.Population, 0))) * 100 / SUM(cn.Population), 2) AS NON_CITY_PERCENTAGE " +
                            "FROM country cn " +
                            "LEFT JOIN ( " +
                            "   SELECT CountryCode, SUM(Population) AS Population " +
                            "   FROM city " +
                            "   GROUP BY CountryCode " +
                            ") ct ON cn.Code = ct.CountryCode " +
                            "GROUP BY cn.Region " +
                            "ORDER BY TOTAL_POPULATION DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country country = new Country();
                country.setRegion(rset.getString("Region"));
                country.setTotalPopulation(rset.getLong("TOTAL_POPULATION"));
                country.setCityPopulation(rset.getLong("CITY_POPULATION"));
                country.setCityPercentage(rset.getFloat("CITY_PERCENTAGE"));
                country.setNonCityPopulation(rset.getLong("NON_CITY_POPULATION"));
                country.setNonCityPercentage(rset.getFloat("NON_CITY_PERCENTAGE"));
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
    public void printRegionCityPopulation(ArrayList<Country> countries) {
        System.out.println("\nRegion population report for those who live in cities and those who don't");
        System.out.println(String.format("%-27s %-20s %-20s %-20s %-20s %-20s",
                "Region", "Total Population", "City Population", "City Percentage",
                "Non City Population", "Non City Percentage"));

        for (Country c : countries) {
            System.out.println(String.format("%-27s %-20s %-20s %-20s %-20s %-20s",
                    c.getRegion(), c.getTotalPopulation(), c.getCityPopulation(), c.getCityPercentage(),
                    c.getNonCityPopulation(), c.getNonCityPercentage()));
        }
    }

    public ArrayList<Country> getCountryCityPopulation() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT cn.Name, " +
                            "SUM(cn.Population) AS TOTAL_POPULATION, " +
                            "SUM(IFNULL(ct.Population, 0)) AS CITY_POPULATION, " +
                            "ROUND(SUM(IFNULL(ct.Population, 0)) * 100 / SUM(cn.Population), 2) AS CITY_PERCENTAGE, " +
                            "(SUM(cn.Population) - SUM(IFNULL(ct.Population, 0))) AS NON_CITY_POPULATION, " +
                            "ROUND((SUM(cn.Population) - SUM(IFNULL(ct.Population, 0))) * 100 / SUM(cn.Population), 2) AS NON_CITY_PERCENTAGE " +
                            "FROM country cn " +
                            "LEFT JOIN ( " +
                            "   SELECT CountryCode, SUM(Population) AS Population " +
                            "   FROM city " +
                            "   GROUP BY CountryCode " +
                            ") ct ON cn.Code = ct.CountryCode " +
                            "GROUP BY cn.Name " +
                            "ORDER BY TOTAL_POPULATION DESC;";


            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<>();

            while (rset.next()) {
                Country country = new Country();
                country.setName(rset.getString("Name"));
                country.setTotalPopulation(rset.getLong("TOTAL_POPULATION"));
                country.setCityPopulation(rset.getLong("CITY_POPULATION"));
                country.setCityPercentage(rset.getFloat("CITY_PERCENTAGE"));
                country.setNonCityPopulation(rset.getLong("NON_CITY_POPULATION"));
                country.setNonCityPercentage(rset.getFloat("NON_CITY_PERCENTAGE"));
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
    public void printCountryCityPopulation(ArrayList<Country> countries) {
        System.out.println("\nCountry population report for those who live in cities and those who don't");
        System.out.println(String.format("%-27s %-20s %-20s %-20s %-20s %-20s",
                "Country", "Total Population", "City Population","City Percentage",
                "Non City Population","Non City Percentage"));

        for (Country c : countries) {
            System.out.println(String.format("%-27s %-20s %-20s %-20s %-20s %-20s",
                    c.getName(), c.getTotalPopulation(), c.getCityPopulation(), c.getCityPercentage(),
                    c.getNonCityPopulation(), c.getNonCityPercentage()));
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

    /**
     * Fetch and print the population of each region
     */
    public void getPopulationOfRegion() {
        try {
            String sql = "SELECT Region, SUM(Population) AS RegionPopulation " +
                    "FROM country " +
                    "GROUP BY Region " +
                    "ORDER BY RegionPopulation DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            System.out.println("\nThe Population of Each Region");
            System.out.printf("%-35s %15s%n", "Region", "Population");

            while (rset.next()) {
                String region = rset.getString("Region");
                long population = rset.getLong("RegionPopulation");

                System.out.printf("%-35s %,15d%n", region, population);
            }

            rset.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error generating region population report: " + e.getMessage());
        }
    }

    /**
     * Fetch and print the population of each country
     */
    public void getPopulationOfCountry() {
        try {
            String sql = "SELECT Name, Population, Continent, Region " +
                    "FROM country " +
                    "ORDER BY Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            System.out.println("\nThe Population of Each Country");
            System.out.printf("%-35s %-20s %-25s %15s%n", "Country", "Continent", "Region", "Population");

            while (rset.next()) {
                String name = rset.getString("Name");
                String continent = rset.getString("Continent");
                String region = rset.getString("Region");
                long population = rset.getLong("Population");

                System.out.printf("%-35s %-20s %-25s %,15d%n", name, continent, region, population);
            }

            rset.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error generating country population report: " + e.getMessage());
        }
    }

    /**
     * Fetch and print the population of each city
     */
    public void getPopulationOfCity() {
        try {
            String sql = "SELECT city.Name AS Name, country.Name AS Country, District, city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            System.out.println("\nThe Population of Each City");
            System.out.printf("%-35s %-35s %-20s %15s%n", "Name", "Country", "District", "Population");

            while (rset.next()) {
                String name = rset.getString("Name");
                String country = rset.getString("Country");
                String district = rset.getString("District");
                long population = rset.getLong("Population");

                System.out.printf("%-35s %-35s %-20s %,15d%n", name, country, district, population);
            }

            rset.close();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error generating city population report: " + e.getMessage());
        }
    }





}