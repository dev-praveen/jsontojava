package org.example;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/** Unit test for simple App. */
public class AppTest {

  @Test
  public void shouldFetchUsersInformation() throws IOException {

    App app = new App();
    final List<User> users = app.fetchUsers();
    assertNotNull(users);

    final User userObj = users.stream().filter(user -> user.getId() == 7).findFirst().get();

    assertEquals("Telly.Hoeger@billy.biz", userObj.getEmail());
  }
}
