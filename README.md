# AirportTestTask

## Instalation
Open terminal and clone the directory
```https://github.com/denisdnepr66/AirportTestTask.git```

Open project with ide

The application uses MySQL Database.
1. Create (connect) to the database. By default database name - "flights", user - "root", passowrd - "password"
2. Run Spring Application 
3. Use [data.sql](https://github.com/denisdnepr66/AirportTestTask/blob/master/src/main/resources/static/data.sql "data.sql") to fill the database with data

## Introduction
There are 4 Entities classes: FlightEntity, CargoEntity, BaggageItemEntity, CargoItemEntity. 
BaggageItemEntity and CargoItemEntity extend Freight. Freight is a abstract MappedSuperclass.
All dates are stored in the database without timezome.

## API
GET ```/flight/{flightNumber}/{zonedDateTime}```  
flightNumber - number of the flight  
zonedDateTime - departure time in format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00".  
This endpoint returns a FreightDTO with information about flight's cargo weight, baggage weight and total weight (baggage + cargo)  
Request ```http://localhost:8080/flight/7043/2021-10-27T10:27:28.000-01:00```  
Response  
```json
{
  "freightDTO":
  {
    "cargoWeight":835,
    "baggageWeight":835,
    "totalWeight":1670
  }
}
```

GET ```/airport/{airportIATACode}/{date}```  
airportIATACode - airport IATA code  
date - date in format yyyy-MM-dd+00:00 - for example, "2021-10-27+00:00"  
This endpoint returns an AirportDTO with:  
- number of arriving flights  
- number of departing flights  
- number of arriving baggages  
- number of departing baggages  
  
Request ```http://localhost:8080/airport/YYZ/2021-10-27+00:00```  
Response  
```json
{
  "airportInfoDTO":
  {
    "numberOfDepartingFlights":0,
    "numberOfArrivingFlights":2,
    "numberOfDepartingBaggagePieces":0,
    "numberOfArrivingBaggagePieces":115
  }
}
```  


