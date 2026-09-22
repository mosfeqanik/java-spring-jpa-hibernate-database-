import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class FrontendLauncher {
    public static void main(String[] args) throws IOException {
        int port = 3000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Frontend Server running on port " + port);
        
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange t) throws IOException {
                String uri = t.getRequestURI().getPath();
                if (uri.equals("/")) uri = "/index.html";
                
                File file = new File("." + uri);
                
                if (file.exists() && !file.isDirectory()) {
                    String method = t.getRequestMethod();
                    
                    // Fix: For HEAD requests, use -1 to indicate no response body will be sent
                    long responseLength = "HEAD".equals(method) ? -1 : file.length();
                    t.sendResponseHeaders(200, responseLength);
                    
                    if ("GET".equals(method)) {
                        OutputStream os = t.getResponseBody();
                        Files.copy(file.toPath(), os);
                        os.close();
                    } else {
                        // This covers HEAD and other methods
                        t.getResponseBody().close();
                    }
                } else {
                    String response = "404: File Not Found";
                    t.sendResponseHeaders(404, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            }
        });
        server.start();
    }
}


