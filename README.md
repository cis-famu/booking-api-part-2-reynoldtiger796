[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/hP86jo5x)

**Required Stories**

- Create a Firebase Database for your application. **3pts** (Done)
- The name in the class fields should match all of the names in the last coding assignment. (Done)
- Add a createdAt field to every collection. (Done)
- Include sample data for each field. (Done)
 
- Create an endpoint for each of the classes (Hotel, Room, User). **6pts** (Done)
- Create the required models, controllers, and services. (Done)
- Each controller should have two methods (Done)
    - Get by ID
    - Get all

- Document endpoints **1pt** (Done)
### Part 2 Required Stories (Done)

- Create an endpoint for each of the major classes (Hotel, Room, User) ***(9 points)***
  - [x] Create the required models, controllers, and services(Done)
  - Each controller should have 3 methods:
    - Create a new entry(Done)
    - Update an existing entry(Done)
    - Delete an existing entry(Done)
- Document endpoints. ***(1 point)***
  - Add to the README file the URI for each endpoint and a description (see example below)(Done)
  - This should include **ALL** endpoints, including those from last week. *Note that there is an extra column*(Done)

  All URIs begin with:
http://localhost:8080/api/v1/product/?sort=asc

|Network| Description                               |
|-------|-------------------------------------------|
|/user| Retrieves all users                       |
|/{userId}| Retrieves a specific product using its ID |
|/hotel| Retrieves all hotels                      |
|/{hotelId}| Retrieves a specific hotel using its ID   |
|/room| Retrieves all rooms                       |
|/{roomID}| Retrieves a specific room using its ID    |
|`POST`| `/user`                                   | Creates a new product|
|`PUT`| `/user/{id}`                              | Updates a product based on it's ID|
|`DELETE`| `/user/{id}`                              | Deletes a product based on it's ID|
|`POST`| `/hotel`                                  | Creates a new product|
|`PUT`| `/hotel/{id}`                             | Updates a product based on it's ID|
|`DELETE`| `/hotel/{id}`                             | Deletes a product based on it's ID|
|`POST`| `/room`                                   | Creates a new product|
|`PUT`| `/room/{id}`                              | Updates a product based on it's ID|
|`DELETE`| `/room/{id}`                              | Deletes a product based on it's ID|

Create an endpoint that allows the user to sort descending or ascending by the createdAt. **6pts** (Done)
