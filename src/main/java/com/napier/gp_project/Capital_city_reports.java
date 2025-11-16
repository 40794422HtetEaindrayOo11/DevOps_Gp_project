package com.napier.gp_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Capital_city_reports {
    public ResultSet rset;
    public static Connection con = null;

    // All Capital Cities in the world
    public void getAllCapitalCitiesInWorld() {
        try {
            String sql = "SELECT city.Name AS CapitalName, " +
                    "country.Name AS CountryName, " +
                    "city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC";

            PreparedStatement pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();

            ArrayList<City> capitals = new ArrayList<>();

            while (rset.next()) {
                City c = new City();
                c.setName(rset.getString("CapitalName"));
                c.setCountryCode(rset.getString("CountryName"));
                c.setPopulation(rset.getInt("Population"));
                capitals.add(c);
            }

            System.out.println("\nAll Capital Cities in the World (Organised by Population - Largest to Smallest)");
            System.out.printf("%-35s %-25s %15s%n", "Capital Name", "Country", "Population");

            for (City c : capitals) {
                System.out.printf("%-35s %-25s %,15d%n",
                        c.getName(),
                        c.getCountryCode(),
                        c.getPopulation());
            }
        }
        catch (SQLException e) {
            System.out.println("Error generating capital city report: " + e.getMessage());
        }
    }
// All Capital city in continent
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

    // All Capital cities in region
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
    // Top N populated cities in the world
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

    // Top N populated cities in a continent
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
