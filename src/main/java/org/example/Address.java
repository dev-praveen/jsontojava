package org.example;

public class Address {

  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private Geo geo;

  @Override
  public String toString() {
    return "Address{"
        + "street='"
        + street
        + '\''
        + ", suite='"
        + suite
        + '\''
        + ", city='"
        + city
        + '\''
        + ", zipcode='"
        + zipcode
        + '\''
        + ", geo="
        + geo
        + '}';
  }

  public String getStreet() {
    return street;
  }

  public String getSuite() {
    return suite;
  }

  public String getCity() {
    return city;
  }

  public String getZipcode() {
    return zipcode;
  }

  public Geo getGeo() {
    return geo;
  }
}
