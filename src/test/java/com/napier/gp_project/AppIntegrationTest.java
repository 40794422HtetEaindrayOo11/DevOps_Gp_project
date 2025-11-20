package com.napier.gp_project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;
    Capital_city_reports capital_city_reports;
    CityReports cityReports;
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
    void getCitiesInWorldTest() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getCitiesInWorld();
    }
    @Test
    void getCitiesInContinentTest() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getCitiesByContinent("Asia");
    }
    @Test
    void getCitiesInRegionTest() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getCitiesByRegion("Middle East");
    }
    @Test
    void getCitiesInCountryTest() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getCitiesByCountry("Myanmar");
    }
    @Test
    void getCitiesByDistrictTest() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getCitiesByDistrict("Kabol");
    }
    @Test
    void getTopNPopulatedCitiesInWorld() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getTopNPopulatedCitiesInWorld(5);
    }
    @Test
    void getTopNPopulatedCitiesInContinent() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getTopNPopulatedCitiesInContinent("Asia", 5);
    }
    @Test
    void getTopNPopulatedCitiesInRegion() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getTopNPopulatedCitiesInRegion("Southeast Asia", 5);
    }
    @Test
    void getTopNPopulatedCitiesInDistrict() {
        cityReports.con = app.getConnection();
        cityReports = new CityReports();
        cityReports.getTopNPopulatedCitiesInDistrict("Michigan",5);
    }
}





