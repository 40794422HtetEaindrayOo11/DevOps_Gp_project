# Use Case 1: Produce a Report about the Countries’ Population

## Characteristic Information

**Goal in Context:**  
As a report manager, I’d like to produce six reports on the population of the countries for the entire world, for a given continent and for a given region, and the variants where I can enter a number and the report will show the top N countries. This allows me to easily make proper reports for the population.

**Scope:** Population Reporting System  
**Level:** Primary task  
**Preconditions:** Database contains all data for cities, capitals, countries, and languages.  
**Success End Condition:** Reports can be easily generated for the desired continent, region, and the world.  
**Failed End Condition:** A report isn’t produced.  
**Primary Actor:** Report Manager  
**Trigger:** A request for countries’ population report is sent to the report manager.

## Main Success Scenario
1. Organization requests countries’ population information for the world, a given continent, and region.  
2. Report Manager selects the desired continent and region to obtain population information.  
3. Report Manager extracts the population information for all countries for the selected continent, region, and the entire world.  
4. Report Manager chooses a number N and the desired continent and region to obtain population information.  
5. Report Manager extracts the top N populated countries information for the desired continent, region, and the world.  
6. Report Manager provides the report to the organization.

**Extensions:** None  
**Sub-Variations:** None  
**Schedule:** Version released by the end of Sprint 3, as each sprint runs parallel with different developers.

---

# Use Case 2: Produce a Report about the Cities’ Population

## Characteristic Information

**Goal in Context:**  
As a report manager, I’d like to produce five reports on the population of cities for the world, a given continent, region, country, and district, as well as the top N ranked cities by entering a number.

**Scope:** Population Reporting System  
**Level:** Primary task  
**Preconditions:** Database contains all data for cities, capitals, countries, and languages.  
**Success End Condition:** Reports can be easily generated for all desired levels.  
**Failed End Condition:** A report isn’t produced.  
**Primary Actor:** Report Manager  
**Trigger:** A request for cities’ population report is sent.

## Main Success Scenario
1. Organization requests cities’ population information for the world, continent, region, country, and district.  
2. Report Manager selects the desired continent, region, country, and district to obtain population information.  
3. Report Manager extracts the population information for all cities for the selected levels.  
4. Report Manager specifies a number N for top-ranked cities.  
5. Report Manager extracts the top N cities’ population information.  
6. Report Manager provides the report to the organization.

**Extensions:** None  
**Sub-Variations:** None  
**Schedule:** Version released by the end of Sprint 3.

---

# Use Case 3: Report of Capital Cities by Population

## Characteristic Information

**Goal in Context:**  
As a report manager, I want to produce reports of capital cities sorted by population to understand population distribution across continents and the world. Additionally, I want to produce reports showing the top N populated capitals.

**Scope:** Population Reporting System  
**Level:** Primary task  
**Preconditions:** Database is prepared and connected; information about countries, cities, and regions is updated.  
**Success End Condition:** Report shows the most populated capital cities and optionally filtered by user-specified number N.  
**Failed End Condition:** Errors in report generation or database connection failure.  
**Primary Actor:** Report Manager  
**Trigger:** Organization requests capital city population report.

## Main Success Scenario
1. Organization requests capital city population data for the world, continent, or region.  
2. Report Manager selects the desired geographical scope.  
3. Report Manager retrieves population information of all capital cities for the selected scope.  
4. Report Manager specifies a number N for top-populated capitals.  
5. System retrieves and orders capital cities by population from largest to smallest.  
6. Report Manager reviews and prints or exports the report.  
7. System closes database connection after generating the report.

**Extensions:** None  
**Sub-Variations:** None  
**Schedule:** Version released by the end of Sprint 3.

---

# Use Case 4: Check Population of City and Non-City Areas

## Characteristic Information

**Goal in Context:**  
As a report manager, I want to produce reports showing the population living in cities and non-cities for the world, continent, and region to compare urban and rural populations.

**Scope:** Population Reporting System  
**Level:** Primary task  
**Preconditions:** Database is set up with necessary data about countries, cities, regions, and continents.  
**Success End Condition:** Report is successfully generated showing city and non-city populations.  
**Failed End Condition:** Report isn’t produced or data is missing.  
**Primary Actor:** Report Manager  
**Trigger:** Report Manager wants city and non-city population report.

## Main Success Scenario
1. Organization requests population report for cities and non-cities for the world, continent, and region.  
2. Report Manager selects desired continent or region.  
3. Report Manager extracts total population data, dividing it into city (urban) and non-city (rural) categories.  
4. System calculates population for selected regions and displays results.  
5. Report Manager reviews the report.  
6. Report Manager provides the report to the organization.

**Extensions:** None  
**Sub-Variations:** None  
**Schedule:** Version released by the end of Sprint 3.

---

# Use Case 5: Check Total Population Reports

## Characteristic Information

**Goal in Context:**  
As a report manager, I want to produce reports showing total population for the world, continent, region, country, district, and city to understand population distribution at each level.

**Scope:** Population Reporting System  
**Level:** Primary task  
**Preconditions:** Database is set up with necessary data for countries, cities, regions, continents, and districts.  
**Success End Condition:** Reports are generated successfully for all selected levels.  
**Failed End Condition:** Report isn’t produced or data is missing.  
**Primary Actor:** Report Manager  
**Trigger:** Report Manager wants total population reports.

## Main Success Scenario
1. Organization requests total population reports.  
2. Report Manager selects desired area level.  
3. System retrieves population data for selected level.  
4. Report Manager reviews the results.  
5. Report Manager provides or prints the report.

**Extensions:** None  
**Sub-Variations:** None  
**Schedule:** Version released by the end of Sprint 3.

---

# Use Case 6: Check Language Population Reports

## Characteristic Information

**Goal in Context:**  
As a report manager, I want to produce reports showing population by language to understand language distribution worldwide.

**Scope:** Population Reporting System  
**Level:** Primary task  
**Preconditions:** Database is set up with data about countries, populations, and languages.  
**Success End Condition:** Report is generated showing population and percentage of speakers for each language.  
**Failed End Condition:** Report isn’t produced or data is missing.  
**Primary Actor:** Report Manager  
**Trigger:** Report Manager wants language population report.

## Main Success Scenario
1. Organization requests report showing population by language.  
2. Report Manager selects languages to include.  
3. System retrieves total number and percentage of people for each selected language.  
4. Report Manager reviews results.  
5. Report Manager provides or prints the report.

**Extensions:** None  
**Sub-Variations:** None  
**Schedule:** Version released by the end of Sprint 3.
