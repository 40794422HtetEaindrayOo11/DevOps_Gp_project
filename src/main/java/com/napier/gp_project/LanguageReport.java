package com.napier.gp_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The LanguageReport class generates reports for major world languages.
 * It calculates:
 * - Total number of speakers worldwide (population * percentage)
 * - Percentage of the world population that speaks each language
 * Languages included in this report:
 * Chinese, English, Hindi, Spanish, Arabic
 * REPORT OUTPUT:
 * Language | Total Population of Speakers | Percentage of World Population
 */
public class LanguageReport {
    private Connection con;

    /**
     * Constructor to initialize the report with a database connection.
     *
     * @param con Active MySQL database connection
     */
    public LanguageReport(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves the major language statistics from the database.
     * PROCESS:
     *  1. Join countrylanguage with country table
     *  2. Multiply each country's population by the percentage of speakers
     *  3. Sum the totals for each language
     *  4. Calculate the percentage of world population speaking each language
     *
     * @return A list of CountryLanguage objects containing:
     *         - Language
     *         - Total speaker population
     *         - Percentage of global population
     */
    public ArrayList<CountryLanguage> getLanguageReport() {
        try {
            Statement stmt = con.createStatement();

            // SQL query to calculate language speakers globally
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
     * Prints the language report in a clean table format.
     * TABLE FORMAT:
     *  Language | Total Population | Speaker Percentage
     *
     * @param countrylanguages ArrayList of CountryLanguage objects
     */
    public void printLanguageReport(ArrayList<CountryLanguage> countrylanguages) {
        System.out.println("\n");
        System.out.println(String.format("%-15s %-20s %-20s",
                "Language", "Total Population", "Speaker Percentage"));

        for (CountryLanguage c : countrylanguages) {
            System.out.println(String.format("%-15s %-20s %-20s",
                    c.getLanguage(), c.getPopulation(), c.getPercentage()));
        }
    }

}
