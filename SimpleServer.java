import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                String filePath = "index.html";
                File file = new File(filePath);

                byte[] response = Files.readAllBytes(file.toPath());

                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            }
        });

        server.start();
        System.out.println("Server started at http://localhost:8000");
    }
}