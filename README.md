# Courier Tracking

Courier Tracking is a comprehensive solution for managing and tracking courier movements and store locations. It provides a robust backend system with RESTful APIs for creating couriers, updating their locations, tracking their travel distances, and logging their entries into stores.

## Tech Stack
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Web**
- **MapStruct**
- **Lombok**
- **H2 Database**
- **JUnit 5**

## Database Schema

### Courier Table
| Column Name    | Data Type      | Description                     |
|----------------|----------------|---------------------------------|
| id             | Long           | Primary key                     |
| name           | String         | Name of the courier             |
| surname        | String         | Surname of the courier          |
| creationDatetime | LocalDateTime | Timestamp of creation           |
| updateDatetime | LocalDateTime | Timestamp of last update        |

### Store Table
| Column Name    | Data Type      | Description                      |
|----------------|----------------|----------------------------------|
| id             | Long           | Primary key                      |
| name           | String         | Name of the store                |
| latitude       | Double         | Latitude coordinate of store     |
| longitude      | Double         | Longitude coordinate of store    |
| creationDatetime | LocalDateTime | Timestamp of creation            |
| updateDatetime | LocalDateTime | Timestamp of last update         |

### CourierLocation Table
| Column Name    | Data Type      | Description                      |
|----------------|----------------|----------------------------------|
| id             | Long           | Primary key                      |
| courier_id     | Long           | Foreign key to Courier table     |
| latitude       | Double         | Latitude coordinate of location  |
| longitude      | Double         | Longitude coordinate of location |
| creationDatetime | LocalDateTime | Timestamp of creation            |
| updateDatetime | LocalDateTime | Timestamp of last update         |

### CourierStoreEntryLog Table
| Column Name    | Data Type      | Description                             |
|----------------|----------------|-----------------------------------------|
| id             | Long           | Primary key                             |
| courier_id     | Long           | Foreign key to Courier table            |
| store_id       | Long           | Foreign key to Store table              |
| entryTime      | Timestamp      | Timestamp when courier entered the store|
| creationDatetime | LocalDateTime | Timestamp of creation                   |
| updateDatetime | LocalDateTime | Timestamp of last update                |

## Accessing H2 Database

Courier Tracking uses an H2 in-memory database for development and testing purposes. You can access the H2 console to view and manage the database.

To access the H2 console:

1. Run the application locally.
2. Open a web browser and go to `http://localhost:8080/h2-console`.
3. Driver: `org.h2.Driver`.
4. JDBC URL: `jdbc:h2:mem:dsen`.
5. Username: `SA`.



## Design Patterns

- **Repository Pattern** 
- **Builder Pattern**
- **DTO Pattern**
- **Singleton Pattern**


## Usage

Once the application is up and running, you can interact with it using the following RESTful endpoints:

- `POST /couriers`: Create a new courier.
- `POST /stores`: Create a new store.
- `PUT /couriers/{id}/location`: Update the location of a courier.
- `GET /couriers/{id}/travel-distance`: Get the total travel distance of a courier.
- `GET /couriers/{id}/store-entry-log`: Get the entry log of a courier to stores.

## Note

For development and testing purposes, an H2 in-memory database is used. Data will not persist between application restarts.
* **Courier-Tracking.postman_collection.json file located under the resources directory.**
