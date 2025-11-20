package com.napier.gp_project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;
    Capital_city_reports capital_city_reports;
    Country_reports country_reports;

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

    @Test
    void getCountriesInWorldTest()
    {
        country_reports = new Country_reports();
        Country_reports.con = app.getConnection();
        country_reports.getCountriesInWorld();
    }

    @Test
    void getCountriesByRegionTest()
    {
        country_reports = new Country_reports();
        Country_reports.con = app.getConnection();
        country_reports.getCountriesByRegion("Southeast Asia");
    }

    @Test
    void getCountriesByContinentTest()
    {
        country_reports = new Country_reports();
        Country_reports.con = app.getConnection();
        country_reports.getCountriesByContinent("Asia");
    }

    @Test
    void getTopNPopulatedCountriesInWorldTest()
    {
        country_reports = new Country_reports();
        Country_reports.con = app.getConnection();
        country_reports.getTopNPopulatedCountriesInWorld(10);
    }

    @Test
    void getTopNPopulatedCountriesInContinentTest()
    {
        country_reports = new Country_reports();
        Country_reports.con = app.getConnection();
        country_reports.getTopNPopulatedCountriesInContinent("Asia", 5);
    }

    @Test
    void getTopNPopulatedCountriesInRegionTest()
    {
        country_reports = new Country_reports();
        Country_reports.con = app.getConnection();
        country_reports.getTopNPopulatedCountriesInRegion("Southeast Asia", 5);
    }
}








