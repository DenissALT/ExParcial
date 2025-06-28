import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PersonalizedGreetingClient 
{
    private static final String FLASK_API_URL = "http://localhost:5000/greet";
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, introduce tu nombre: ");
        String userName = scanner.nextLine();
        scanner.close();
        try 
        {
            URL url = new URL(FLASK_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            String jsonInputString = "{\"name\": \"" + userName + "\"}";
            try (OutputStream os = conn.getOutputStream()) 
            {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) 
            {
                BufferedReader errorBr = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String errorOutput;
                while ((errorOutput = errorBr.readLine()) != null) 
                {
                    errorResponse.append(errorOutput);
                }
                System.err.println("Error: " + conn.getResponseCode() + " - " + errorResponse.toString());
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
        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
}