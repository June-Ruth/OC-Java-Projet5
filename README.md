# SafetyNet Alerts

Safety Net Alerts manages all information of inhabitant of your city and their fire station.

With it, you will be able to read, create, update and delete information about persons, medical records or fire stations
and you can access to more information with specific endpoints.

## Infos


author : Noémie BARRAL - DA Java Student - OpenClassrooms

release date : 15/01/2020

### Install

* The application works on port 8080.

* The application "SafetyNet Alerts" works with a DataBase Object. It's not configured to support a DataBase Service.

* The JSON data importation is made automatically when calling the DataBase Object from the file data.json in src/main/resources.

* Persistence is not manage in this version. Be careful if shut down the application, you will loose all modification.

## Content

The first release v.1.0.0 contains :

* Administrative endpoints for Person, FireStation and MedicalRecord that can be used for CRUD operations.

* Some specific endpoints to return defined information depending on emergency situation.

### Administrative endpoints

#### Person

**GET** - http://localhost:8080/persons

* returns a list of all persons in DataBase.

**POST** - http://localhost:8080/person

* add a person if not existing. 

**PUT** - http://localhost:8080/person

* update a person if already exists.

**DELETE** - http://localhost:8080/person/{firstName}_{lastName}

* delete a person by its first name and last name.

#### FireStation

**GET** - http://localhost:8080/firestations

* returns a list of all fire stations in DataBase.

**POST** - http://localhost:8080/firestation

* add a fire station if not existing. 

**PUT** - http://localhost:8080/firestation

* update a firestation if already exists.

**DELETE** - http://localhost:8080/firestation/station/{station}

* delete all fire station mapped with this number if existing.

**DELETE** - http://localhost:8080/address/{address}

* delete the fire station mapped with this address if existing.

#### MedicalRecord

**GET** - http://localhost:8080/medicalrecords

* returns a list of all medical records in DataBase.

**POST** - http://localhost:8080/medicalrecord

* add a medical record if not existing. 

**PUT** - http://localhost:8080/medicalrecord

* update a medical record if already exists.

**DELETE** - http://localhost:8080/medicalrecord/{firstName}_{lastName}

* delete a medical record by its first name and last name.

### Specific endpoints

**GET** - http://localhost:8080/firestation?stationNumber=stationNumber

* returns a list of persons covered by the fire station number and the countdown of adult and children.
    
**GET** - http://localhost:8080/childAlert?address=address

* returns a list of children at the address with a list of other inhabitants.
    
**GET** - http://localhost:8080/phoneAlert?firestation=firestationNumber

* return a list of phone number of people covered by the fire station.
    
**GET** - http://localhost:8080/fire?address=address

* return a list of inhabitant at the address and the number of the fire station.
    
**GET** - http://localhost:8080/flood/stations?stations=stationNumber

* returns a list of all flood covered by the fire station.
    
**GET** - http://localhost:8080/personInfo?firstName=firstName&lastName=lastName

* returns all information of a person.
    
**GET** - http://localhost:8080/communityEmail?city=city

* return all email of inhabitant of the city.


