package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/** Unit test for simple App. */
public class AppTest {

  private static final String GET_URL = "https://jsonplaceholder.typicode.com/users";
  private static final String POST_URL = "https://reqres.in/api/users";
  private App app;

  @Before
  public void setUp() {
    app = new App();
  }

  @Test
  public void shouldFetchUsersInformation() throws IOException {

    final List<User> users = app.fetchUsers(GET_URL, "GET");
    assertNotNull(users);
    final User userObj = users.stream().filter(user -> user.getId() == 7).findFirst().get();
    assertEquals("Telly.Hoeger@billy.biz", userObj.getEmail());
  }

  @Test
  public void shouldCreateUser() throws IOException {

    String requestBody =
        "{\n"
            + "        \"name\": \"paul rudd\",\n"
            + "        \"movies\": [\"I Love You Man\", \"Role Models\"]\n"
            + "    }";
    final String json = app.createUser(POST_URL, "POST", requestBody);
    assertNotNull(json);
  }
}
