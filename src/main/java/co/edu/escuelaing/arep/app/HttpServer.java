package co.edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class HttpServer {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        HttpConnectionExample connectionToApi = new HttpConnectionExample();

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean firstLine = true;
            String uri = "";

            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    uri = inputLine.split(" ")[1];
                    firstLine = false;
                    System.out.println("THE URI IS: " + uri);
                }

                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) { break; }
            }
            URI uriObject = new URI(uri);
            outputLine = "";
            if (uri.startsWith("/cliente")) {
                String htmlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/index.html")));
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n" + htmlContent;
            } else if (uri.startsWith("/consulta")) {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n" + connectionToApi.consult("compreflex" + uri.replace("/consulta", "")).toString();
            } else {
                outputLine = "HTTP/1.1 404 NOT FOUND\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <title>Not found</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <h1>Error 404 Not Found</h1>\n"
                        + "</html>";
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
}