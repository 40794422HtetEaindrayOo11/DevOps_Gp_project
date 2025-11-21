package com.napier.gp_project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;
    static Capital_city_reports capital_city_reports;
    static PopulationReport population_report;

    @BeforeAll
    static void init()
    {
        app = new App();
        capital_city_reports = new Capital_city_reports();
        population_report = new PopulationReport();
    }


    @Test
    void disconnectTest()
    {
        app.disconnect();
    }

    @Test
    void printConCityPopulationTest()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setContinent("Asia");
        country.setTotalPopulation(18000000);
        country.setCityPopulation(12000000);
        country.setNonCityPopulation(6000000);
        countries.add(country);

        population_report.printConCityPopulation(countries);
    }

    @Test
    void printConCityPopulationTestNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        population_report.printConCityPopulation(countries);
    }

    @Test
    void printConCityPopulationTestContainNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        population_report.printConCityPopulation(countries);
    }




    @Test
    void printRegionCityPopulationTest()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setRegion("Caribbean");
        country.setTotalPopulation(80000000);
        country.setCityPopulation(60000000);
        country.setNonCityPopulation(2000000);
        countries.add(country);
        population_report.printRegionCityPopulation(countries);
    }

    @Test
    void printRegionCityPopulationTestNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        population_report.printRegionCityPopulation(countries);
    }

    @Test
    void printRegionCityPopulationTestContainNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        population_report.printRegionCityPopulation(countries);
    }

    @Test
    void printCountryCityPopulationTest()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setName("Myanmar");
        country.setTotalPopulation(70000000);
        country.setCityPopulation(45000000);
        country.setNonCityPopulation(25000000);
        countries.add(country);
        population_report.printCountryCityPopulation(countries);
    }

    @Test
    void printCountryCityPopulationTestNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        population_report.printCountryCityPopulation(countries);
    }

    @Test
    void printCountryCityPopulationTestContainNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        population_report.printCountryCityPopulation(countries);
    }

}
