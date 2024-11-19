
# Steam Games Retriever

The **Steam Games Retriever** is a simple Java application that acts as a local web server. It allows you to list the games from a Steam account using the platform's API.

## Features
- Starts a local web server on port **8080**.
- Displays an HTML page with a form to input the Steam API key and the user's account ID.
- Handles a `POST` request to the server with the provided information.
- Makes a call to the Steam API to list the games from the specified account.

## Prerequisites
- **Java 23**.
- **Maven**.

## Setup and Execution

### Step 1: Clone the repository
Clone this repository to your local environment:
```bash
git clone <REPOSITORY_URL>
```

### Step 2: Build the project
Navigate to the root folder of the project and run the Maven command:

```bash
mvn clean package
```

This will generate a `.jar` file inside the `target` folder.

### Step 3: Run the server
Run the web server with the following command:

```bash
java -cp ./target/steam-games-retriever-1.0-SNAPSHOT.jar com.steam_games_retriever.Server
```

The server will start on port **8080**.

### Step 4: Access the application
Open your browser and go to the following address:

```
http://localhost:8080/
```

## How to Use
1. On the home page (`index.html`), enter your **Steam API key** and a **Steam account ID**.
2. Click the **Fetch Games** button.
3. The server will make a request to the Steam API and return a list of games associated with the account.

## Project Structure
- **src/main/java/com/steam_games_retriever**: Main application Java code.
- **src/main/webapp/index.html**: HTML page that serves as the user interface.
- **pom.xml**: Maven configuration file.

## Notes
- Make sure the Steam account profile is set to public so the API can fetch the data.
- You can generate an API key on the official [Steam Web API](https://steamcommunity.com/dev) site.