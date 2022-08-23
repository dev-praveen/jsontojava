package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class App {

  private static final String GET_URL = "https://jsonplaceholder.typicode.com/users";

  private String makeUserApiCall() throws IOException {
    URL obj = new URL(GET_URL);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
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

  public List<User> fetchUsers() throws IOException {

    final String json = makeUserApiCall();
    Type listType = new TypeToken<ArrayList<User>>() {}.getType();
    return new Gson().fromJson(json, listType);
  }
}
