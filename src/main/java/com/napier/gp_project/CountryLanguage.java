package com.napier.gp_project;

public class CountryLanguage {
    private String countryCode;
    private String language;
    private boolean isOfficial;
    private float percentage;
    private long population;

    public CountryLanguage(){

    }

    public CountryLanguage(String countryCode, String language, boolean isOfficial, float percentage, long population) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
        this.population = population;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "countryCode='" + countryCode + '\'' +
                ", language='" + language + '\'' +
                ", isOfficial=" + isOfficial +
                ", percentage=" + percentage +
                '}';
    }
}
