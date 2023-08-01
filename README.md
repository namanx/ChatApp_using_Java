# Java Chat Application

## Introduction
This is a Java chat application that demonstrates various concepts of Object-Oriented Programming (OOPs), Multithreading, Networking, Java Swing Library, and String Handling in Java. The application allows users to connect to a chat server using sockets and exchange messages in real-time.

## Features
- Multiple users can connect to the chat server simultaneously.
- Messages are transmitted using sockets, enabling real-time communication.
- The application utilizes multithreading to handle multiple client connections efficiently.
- String handling is used to process and manage incoming and outgoing messages.
- The chat application provides a simple and intuitive command-line interface for users.
- A Simple Java GUI using Swing package

## Requirements
- Java Development Kit (JDK) 8 or higher.
- A terminal or command prompt to run the application.

## Usage
1. Clone the repository or download the source code.
2. Compile the Java files using the following command:
   ```bash
   javac ChatServer.java ChatClient.java
   ```
3. Start the chat server by running the following command:
   ```bash
   java ChatServer
   ```
4. Start the chat client(s) by running the following command:
   ```bash
   java ChatClient
   ```
5. The client(s) will be prompted to enter the server IP and port to connect.
6. Once connected, users can send and receive messages in the chat application.

## How It Works
- The chat server creates a `ServerSocket` to listen for incoming connections.
- Each new client connection is handled in a separate thread using Java multithreading (`Thread` or `ExecutorService`).
- The client and server exchange messages using `BufferedReader` and `PrintWriter` to read and write data over the socket connection.
- The application implements a basic protocol to handle user commands and chat messages.

## Credits
This chat application is created by Naman Guntiwar.

## Disclaimer
This application is for educational purposes only. It may contain bugs and is not intended for production use.

## Output




![ChatApp_v1](https://github.com/namanx/ChatApp_using_Java/assets/94428936/9ee4027c-8ced-4ab7-884b-7e28253ba9bf)










### With GUI

![Screenshot (214)](https://github.com/namanx/ChatApp_using_Java/assets/94428936/a132a44c-2b00-4a5e-9ac0-c25edebb2671)
