import java.io.*;
import java.net.*;

public class SimpleClient 
{
    private static final String FLASK_API_URL = "http://localhost:5000/hello";
    public static void main(String[] args) 
    {
        try 
        {
            URL url = new URL(FLASK_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) 
            {
                System.err.println("Error de conexión: HTTP " + conn.getResponseCode());
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) 
            {
                response.append(output);
            }
            conn.disconnect();
            System.out.println("Mensaje recibido de Flask: " + response.toString());
        }
        catch (Exception e) 
        {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
