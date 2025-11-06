# Order Management System

## How to Run

1. In the project root, compile all source files:
   ```bash
   javac src/**/*.java
   ```

2. Then run the demo code:
   ```bash
   java -cp src Main
   ```

The program will attempt to maintain a connection as long as possible, but the server will stop at some random interval. The register will always write to a file `orders.txt`, but will gracefully continue when unable to send to the server.

---

## External Packages and Frameworks Used

This project uses only built-in Java libraries (no external dependencies).

| Package | Purpose |
|----------|----------|
| `java.net` | For socket-based client-server communication |
| `java.io` | For input/output stream handling |
| `java.util` | For ArrayList and utility data structures |

## Sources

ChatGPT for help formatting and phrasing the readme
