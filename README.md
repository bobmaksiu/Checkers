# Checkers Game

## Description

Checkers Game is a desktop application designed for playing checkers between two players. The game uses Java Swing for the graphical user interface and Java Sockets for communication between the host and the joining player. The application implements full game logic, allowing players to make moves and interact with the game board.

## Features

- **Multiplayer:**  
  Two players can connect and play against each other over a network.

- **User Interface:**  
  The game features a graphical interface built with Java Swing.

- **Game Logic:**  
  Complete checkers game logic is implemented, allowing players to make valid moves.

## Technologies Used

- **Java Swing** for the graphical user interface.
- **Java Sockets** for network communication between players.
- **Java** as the primary programming language.

## Getting Started

### Prerequisites

- **Java 17** or later.
- **IDE** (e.g., IntelliJ IDEA) for development.

### Installation

1. **Clone the repository:**

  ```bash
   git clone https://github.com/bobmaksiu/checkers-game.git
```

2. **Navigate into the project directory:**

```bash
   cd checkers-game
```

3. **Build the project:**

   For Maven:

```bash
   mvn install
```

   For Gradle:

```bash
   gradle build
```

4. **Run the application:**

   For Maven:

```bash
   mvn exec:java -Dexec.mainClass="com.example.checkers.CheckersGame"
```

   For Gradle:

```bash
   gradle run
```

   The application will open a window where you can start a game as a host or join an existing game.

## How to Play

1. **Start the Host Game:**
   - Launch the application and select the option to start a new game as the host.
   - Provide the IP address and port number for the host to be used by the joining player.

2. **Join a Game:**
   - Launch the application and select the option to join an existing game.
   - Enter the IP address and port number provided by the host to connect.

3. **Play the Game:**
   - Use the graphical interface to make moves on the checkers board.
   - The game will handle the logic for valid moves and interactions.

## Planned Features

- **Turn-Based Mechanics:**  
  Implement a turn-based system to ensure players alternate moves.

- **Enhanced Game Logic:**  
  Refine game logic to handle more complex scenarios and improve the user experience.

- **Network Improvements:**  
  Optimize network communication for better performance and reliability.

## Contributing

Contributions are welcome! Please follow these steps if you'd like to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions or suggestions, feel free to reach out:

- **Mail** - [maxpetryna@gmail.com](mailto:maxpetryna@gmail.com)
- **GitHub** - [bobmaksiu](https://github.com/bobmaksiu)
