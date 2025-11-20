package com.napier.gp_project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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


