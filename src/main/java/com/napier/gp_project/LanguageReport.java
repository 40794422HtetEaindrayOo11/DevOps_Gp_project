package com.napier.gp_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class LanguageReport {
    private Connection con;

    public LanguageReport(Connection con) {
        this.con = con;
    }

    public ArrayList<CountryLanguage> getLanguageReport() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT" +
                    "    cl.Language," +
                    "    SUM(c.Population * cl.Percentage / 100) AS Population, " +
                    "    ROUND(" +
                    "        SUM(c.Population * cl.Percentage / 100) * 100 /" +
                    "        (SELECT SUM(Population) FROM country), " +
                    "        2) AS Percentage " +
                    "FROM " +
                    " countrylanguage AS cl" +
                    " JOIN country AS c ON cl.CountryCode = c.Code " +
                    "WHERE " +
                    "    cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                    "GROUP BY " +
                    "    cl.Language " +
                    "ORDER BY " +
                    "    Population DESC;";


            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<CountryLanguage> countryLanguages = new ArrayList<>();

            while (rset.next()) {
                CountryLanguage countryLanguage = new CountryLanguage();
                countryLanguage.setLanguage(rset.getString("Language"));
                countryLanguage.setPopulation(rset.getLong("Population"));
                countryLanguage.setPercentage(rset.getFloat("Percentage"));
                countryLanguages.add(countryLanguage);
            }

            rset.close();
            stmt.close();
            return countryLanguages;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Printing out the query result in a table format
     */
    public void printLanguageReport(ArrayList<CountryLanguage> countrylanguages) {
        System.out.println("\n");
        System.out.println(String.format("%-15s %-20s %-20s",
                "Language", "Total Population", "Speaker Percentage"));

        for (CountryLanguage c: countrylanguages){
            System.out.println(String.format("%-15s %-20s %-20s",
                    c.getLanguage(), c.getPopulation(), c.getPercentage()));
        }
        }

}
