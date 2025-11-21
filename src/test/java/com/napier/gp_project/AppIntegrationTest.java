package com.napier.gp_project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;
    Capital_city_reports capital_city_reports;
    PopulationReport population_report;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void getAllCapitalCitiesInWorldTest()
    {
        capital_city_reports.con = app.getConnection();
        capital_city_reports = new Capital_city_reports();
        capital_city_reports.getAllCapitalCitiesInWorld();
    }

    @Test
    void getAllCapitalCitiesInContinentTest()
    {
        capital_city_reports.con = app.getConnection();
        capital_city_reports = new Capital_city_reports();
        capital_city_reports.getAllCapitalCitiesInContinent("Asia");
    }

    @Test
    void getAllCapitalCitiesInRegionTest()
    {
        capital_city_reports.con = app.getConnection();
        capital_city_reports = new Capital_city_reports();
        capital_city_reports.getAllCapitalCitiesInRegion("Caribbean");
    }


    // Population Integrated Testing

    @Test
    void getPopulationOfWorld(){
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        population_report.getPopulationOfWorld();


    }

    @Test
    void getPopulationOfContinent(){
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        population_report.getPopulationOfContinent();
    }

    @Test
    void getPopulationOfRegion(){
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        population_report.getPopulationOfRegion();
    }

    @Test
    void getPopulationOfCountry(){
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        population_report.getPopulationOfCountry();
    }

    @Test
    void getPopulationOfCity(){
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        population_report.getPopulationOfCity();
    }

    @Test
    void getPopulationOfDistrict(){
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        population_report.getPopulationOfDistrict();
    }

    @Test
    void getConCityPopulation() {
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        ArrayList<Country> countries = population_report.getConCityPopulation();
        population_report.printConCityPopulation(countries);
    }

    @Test
    void testPrintConCityPopulation() {
        population_report.con = app.getConnection();
        ArrayList<Country> mock = new ArrayList<>();
        Country c = new Country();
        c.setContinent("Asia");
        c.setTotalPopulation(1000);
        c.setCityPopulation(600);
        c.setCityPercentage(60.0f);
        c.setNonCityPopulation(400);
        c.setNonCityPercentage(40.0f);
        mock.add(c);

        PopulationReport report = new PopulationReport();

        // Capture console output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        report.printConCityPopulation(mock);

        String output = out.toString();

        assertTrue(output.contains("Asia"));
        assertTrue(output.contains("1000"));
        assertTrue(output.contains("600"));
        assertTrue(output.contains("40.0"));
    }

    @Test
    void getRegionCityPopulation() {
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        ArrayList<Country> countries = population_report.getRegionCityPopulation();
        population_report.printRegionCityPopulation(countries);
    }

    @Test
    void getCountryCityPopulation() {
        population_report.con = app.getConnection();
        population_report = new PopulationReport();
        ArrayList<Country> countries = population_report.getCountryCityPopulation();
        population_report.printCountryCityPopulation(countries);
    }
}


