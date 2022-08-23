package org.example;

public class Geo {

  private String lat;
  private String lng;

  @Override
  public String toString() {
    return "Geo{" + "lat='" + lat + '\'' + ", lng='" + lng + '\'' + '}';
  }

  public String getLat() {
    return lat;
  }

  public String getLng() {
    return lng;
  }
}
