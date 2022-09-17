package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class App {

  private static HttpURLConnection openConnection(String apiUrl, String apiMethod)
      throws IOException {

    URL obj = new URL(apiUrl);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod(apiMethod);
    con.setRequestProperty("Accept", "application/json");
    if ("POST".equals(apiMethod)) {
      con.setRequestProperty("Content-Type", "application/json");
      con.setDoOutput(true);
    }
    return con;
  }

  private static String getUserApiCall(String apiUrl, String apiMethod) throws IOException {

    final HttpURLConnection con = openConnection(apiUrl, apiMethod);
    int responseCode = con.getResponseCode();
    System.out.println("GET Response Code :: " + responseCode);
    if (responseCode == HttpURLConnection.HTTP_OK) { // success
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      // print result
      return response.toString();
    } else {
      throw new InterruptedIOException("GET request not worked.. some thing went wrong");
    }
  }

  private static String createUserApiCall(String apiUrl, String apiMethod, String requestBody)
      throws IOException {

    final HttpURLConnection con = openConnection(apiUrl, apiMethod);
    OutputStream os = con.getOutputStream();
    byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
    os.write(input, 0, input.length);
    BufferedReader br =
        new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
    StringBuilder response = new StringBuilder();
    String responseLine;
    while ((responseLine = br.readLine()) != null) {
      response.append(responseLine.trim());
    }
    return response.toString();
  }

  public List<User> fetchUsers(String apiUrl, String apiMethod) throws IOException {

    final String json = getUserApiCall(apiUrl, apiMethod);
    Type listType = new TypeToken<ArrayList<User>>() {}.getType();
    return new Gson().fromJson(json, listType);
  }

  public String createUser(String apiUrl, String apiMethod, String requestBody) throws IOException {

    return createUserApiCall(apiUrl, apiMethod, requestBody);
  }
}
