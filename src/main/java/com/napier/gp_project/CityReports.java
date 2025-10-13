package com.napier.gp_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityReports {

    public static Connection con = null;

    /**
     * All the cities in the world organised by largest population to smallest.
     */
    public void getCitiesInWorld() {
        try {
            String sql = "SELECT city.Name AS CityName, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<City> cities = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CityName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setDistrict(rset.getString("District"));
                c.setPopulation(rset.getInt("Population"));
                cities.add(c);
            }

            System.out.println("\nAll Cities in the World (Organised by Population - largest to smallest)");
            System.out.printf("%-35s %-25s %-20s %15s%n", "City Name", "Country", "District", "Population");

            for (City c : cities) {
                System.out.printf("%-35s %-25s %-20s %,15d%n",
                        c.getName(), c.getCountryCode(), c.getDistrict(), c.getPopulation());
            }

        } catch (SQLException e) {
            System.out.println("Error generating world city report: " + e.getMessage());
        }
    }

    /**
     * Top N populated cities in the world.
     */
    public void getTopNPopulatedCitiesInWorld(int n) {
        try {
            String sql = "SELECT city.Name AS CityName, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population DESC LIMIT ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, n);
            ResultSet rset = pstmt.executeQuery();

            ArrayList<City> cities = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CityName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setDistrict(rset.getString("District"));
                c.setPopulation(rset.getInt("Population"));
                cities.add(c);
            }

            System.out.println("\nTop " + n + " Populated Cities in the World");
            System.out.printf("%-35s %-25s %-20s %15s%n", "City Name", "Country", "District", "Population");

            for (City c : cities) {
                System.out.printf("%-35s %-25s %-20s %,15d%n",
                        c.getName(), c.getCountryCode(), c.getDistrict(), c.getPopulation());
            }

        } catch (SQLException e) {
            System.out.println("Error generating top N world city report: " + e.getMessage());
        }
    }

}
