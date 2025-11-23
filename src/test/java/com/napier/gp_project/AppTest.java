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

    @Test
    void createCityTest()
    {
        City city = new City(18800000,32,"Yangon","MMR","SoutheastAsia");
        String cityReport = city.toString();
    }

    @Test
    void cityGetterSetterTest()
    {
        City city = new City();
        city.setPopulation(18000000);
        city.setId(32);
        city.setName("Yangon");
        city.setCountryCode("MMR");
        city.setDistrict("SoutheastAsia");

        assertEquals(18000000,city.getPopulation());
        assertEquals(32,city.getId());
        assertEquals("Yangon",city.getName());
        assertEquals("MMR",city.getCountryCode());
        assertEquals("SoutheastAsia",city.getDistrict());

    }

    @Test
    void createCountryTest()
    {
        Country country = new Country("MMR","Myanmar","Asia","AsiaPacific",10000000f,1960,2130000000,62f,100f,80f,"Burma","democracy","NLD",1,"NPD",1231231231,123123123,10000000,80,20);
        String countryReport = country.toString();
    }

    @Test
    void countryGetterSetterTest()
    {
        Country country = new Country();
        country.setCode("MMR");
        country.setName("Myanmar");
        country.setContinent("Asia");
        country.setRegion("AsiaPacific");
        country.setIndepYear(1960);
        country.setSurfaceArea(18000f);
        country.setLifeExpectancy(63f);
        country.setGnp(100f);
        country.setGnpOld(80f);
        country.setLocalName("Burma");
        country.setGovernmentForm("democracy");
        country.setHeadOfState("NLD");
        country.setCapital(1);
        country.setCode2("NPD");
        country.setTotalPopulation(29000000);
        country.setCityPopulation(20000000);
        country.setNonCityPopulation(9000000);
        country.setCityPercentage(90f);
        country.setNonCityPercentage(10f);


        assertEquals("MMR",country.getCode());
        assertEquals("Myanmar",country.getName());
        assertEquals("Asia",country.getContinent());
        assertEquals("AsiaPacific",country.getRegion());
        assertEquals(18000f,country.getSurfaceArea());
        assertEquals(1960,country.getIndepYear());
        assertEquals(63f,country.getLifeExpectancy());
        assertEquals(100f,country.getGnp());
        assertEquals(80f,country.getGnpOld());
        assertEquals("Burma",country.getLocalName());
        assertEquals("democracy",country.getGovernmentForm());
        assertEquals("NLD",country.getHeadOfState());
        assertEquals(1,country.getCapital());
        assertEquals("NPD",country.getCode2());
        assertEquals(29000000,country.getTotalPopulation());
        assertEquals(20000000,country.getCityPopulation());
        assertEquals(9000000,country.getNonCityPopulation());
        assertEquals(90f,country.getCityPercentage());
        assertEquals(10f,country.getNonCityPercentage());
    }

    @Test
    void createCountryLanguageTest()
    {
        CountryLanguage countryLanguage = new CountryLanguage("MMR","Myanmar",true,80f,18000000);
        String countryLanguageReport = countryLanguage.toString();
    }

    @Test
    void countryLanguageGetterSetterTest()
    {
        CountryLanguage countryLanguage = new CountryLanguage();
        countryLanguage.setLanguage("Myanmar");
        countryLanguage.setCountryCode("MMR");
        countryLanguage.setPercentage(80f);
        countryLanguage.setPopulation(18000000);
        countryLanguage.setOfficial(true);

        assertEquals("Myanmar",countryLanguage.getLanguage());
        assertEquals("MMR",countryLanguage.getCountryCode());
        assertEquals(80f,countryLanguage.getPercentage());
        assertEquals(18000000,countryLanguage.getPopulation());
        assertEquals(true,countryLanguage.isOfficial());
    }

}
