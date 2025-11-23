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
    LanguageReport language_report;
    Country_reports country_reports;
    CityReports cityReports;
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
    void testGetConCityPopulation() {
        PopulationReport report = new PopulationReport();
        report.con = app.getConnection();

        ArrayList<Country> result = report.getConCityPopulation();
        assertNotNull(result);
        assertFalse(result.isEmpty());

        Country asia = result.stream()
                .filter(c -> "Asia".equals(c.getContinent()))
                .findFirst()
                .orElse(null);

        assertNotNull(asia);
        assertTrue(asia.getTotalPopulation() > 0);
    }


    @Test
    void testPrintConCityPopulation() {
        ArrayList<Country> mock = new ArrayList<>();
        Country c = new Country();
        c.setContinent("Asia");
        c.setTotalPopulation(2000);
        c.setCityPopulation(1500);
        c.setCityPercentage(75.0f);
        c.setNonCityPopulation(500);
        c.setNonCityPercentage(25.0f);
        mock.add(c);

        PopulationReport report = new PopulationReport();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        report.printConCityPopulation(mock);

        System.setOut(original);

        String output = out.toString();

        assertTrue(output.contains("Asia"));
        assertTrue(output.contains("2000"));
        assertTrue(output.contains("1500"));
        assertTrue(output.contains("25.0"));
    }


    @Test
    void testGetRegionCityPopulation() {
        PopulationReport report = new PopulationReport();
        report.con = app.getConnection();

        ArrayList<Country> result = report.getRegionCityPopulation();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        Country eastAsia = result.stream()
                .filter(c -> "Eastern Asia".equals(c.getRegion()))
                .findFirst()
                .orElse(null);

        assertNotNull(eastAsia);
        assertTrue(eastAsia.getTotalPopulation() > 0);
        assertTrue(eastAsia.getCityPercentage() >= 0);
    }

    @Test
    void testPrintRegionCityPopulation() {
        ArrayList<Country> mock = new ArrayList<>();
        Country c = new Country();
        c.setRegion("Eastern Asia");
        c.setTotalPopulation(1000);
        c.setCityPopulation(600);
        c.setCityPercentage(60.0f);
        c.setNonCityPopulation(400);
        c.setNonCityPercentage(40.0f);
        mock.add(c);

        PopulationReport report = new PopulationReport();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        report.printRegionCityPopulation(mock);

        System.setOut(original);

        String output = out.toString();

        assertTrue(output.contains("Eastern Asia"));
        assertTrue(output.contains("1000"));
        assertTrue(output.contains("600"));
        assertTrue(output.contains("40.0"));
    }

    @Test
    void testGetCountryCityPopulation() {
        PopulationReport report = new PopulationReport();
        report.con = app.getConnection();

        ArrayList<Country> result = report.getCountryCityPopulation();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        Country japan = result.stream()
                .filter(c -> "Japan".equals(c.getName()))
                .findFirst()
                .orElse(null);

        assertNotNull(japan);

        assertTrue(japan.getTotalPopulation() > 0);
        assertTrue(japan.getCityPopulation() >= 0);
        assertEquals(
                japan.getCityPopulation() + japan.getNonCityPopulation(),
                japan.getTotalPopulation()
        );
    }

    @Test
    void testPrintCountryCityPopulation() {
        ArrayList<Country> mock = new ArrayList<>();
        Country c = new Country();
        c.setName("Japan");
        c.setTotalPopulation(1000);
        c.setCityPopulation(600);
        c.setCityPercentage(60.0f);
        c.setNonCityPopulation(400);
        c.setNonCityPercentage(40.0f);
        mock.add(c);

        PopulationReport report = new PopulationReport();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        report.printCountryCityPopulation(mock);

        System.setOut(original);

        String output = out.toString();

        assertTrue(output.contains("Japan"));
        assertTrue(output.contains("1000"));
        assertTrue(output.contains("600"));
        assertTrue(output.contains("40.0"));
    }


    @Test
    void getLanguageReport() {
        language_report = new LanguageReport(app.getConnection());
        var list = language_report.getLanguageReport();
        language_report.printLanguageReport(list);
    }








}


