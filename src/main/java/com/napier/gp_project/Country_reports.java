package com.napier.gp_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Country_reports {

    public static Connection con = null;

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

            System.out.println("Total countries displayed: " + countries.size());

        } catch (SQLException e) {
            System.out.println("Error generating country report: " + e.getMessage());
        }
    }

}
