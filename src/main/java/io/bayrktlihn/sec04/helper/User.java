package io.bayrktlihn.sec04.helper;

import io.bayrktlihn.courseutil.Util;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

  private int userId;
  private String name;

  private User(final int userId) {
    this.userId = userId;
    this.name = Util.faker().name().firstName();
  }

  public static User createRandomUser(final int userId) {
    return new User(userId);
  }
}
