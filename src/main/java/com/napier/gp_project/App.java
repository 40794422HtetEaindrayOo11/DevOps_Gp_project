package com.napier.gp_project;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.sql.*;

public  class App {

    public static void main(String[] args)
    {
        CityReport city_report = new CityReport();
        city_report.connect();
        city_report.getCitiesByContinent("Asia");
        city_report.disconnect();

        CountryReport country_report = new CountryReport();
        country_report.connect();
        country_report.getCountriesByRegion("Southeast Asia");
        country_report.disconnect();

    }

}

