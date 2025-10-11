package com.napier.gp_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulationReport {
    private Connection con;  // store the connection

    // Constructor that accepts the connection
    public PopulationReport(Connection con) {
        this.con = con;
    }

    /**
    Fetching the required data from database
     */
    public ArrayList<Country> getConCityPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // SQL query to select only the desired columns
            String strSelect =
                    "SELECT cn.Continent, SUM(cn.Population) AS TOTAL_POPULATION, " +
                            "SUM(ct.Population) AS CITY_POPULATION, " +
                            "(SUM(cn.Population)-SUM(ct.Population)) AS NON_CITY_POPULATION " +
                            "FROM country cn " +
                            "LEFT JOIN (" +
                            " SELECT CountryCode, SUM(Population) AS Population " +
                            " FROM city" +
                            " GROUP BY CountryCode " +
                            ")" +
                            "ct ON cn.Code = ct.CountryCode " +
                            "GROUP BY cn.Continent " +
                            "ORDER BY TOTAL_POPULATION DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Store all countries in a list
            ArrayList<Country> countries = new ArrayList<>();

            // Putting the data rows into the ArrayList
            while (rset.next()) {
                Country country = new Country();
                country.setContinent(rset.getString("Continent"));
                country.setTotalPopulation(rset.getLong("Total_POPULATION"));
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
    Printing out the query result in a table format
     */
    public void printConCityPopulation(ArrayList<Country> countries) {
        System.out.println(String.format("%-15s %-20s %-20s %-20s"
                ,"Continent","Total Population","City Population","Non City Population"));
        for (Country c: countries){
            System.out.println(String.format("%-15s %-20s %-20s %-20s"
                    ,c.getContinent(),c.getTotalPopulation(),c.getCityPopulation(),c.getNonCityPopulation()));
        }
    }

}
