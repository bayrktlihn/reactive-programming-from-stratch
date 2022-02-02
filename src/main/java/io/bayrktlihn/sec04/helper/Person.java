package io.bayrktlihn.sec04.helper;

import io.bayrktlihn.courseutil.Util;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {

  private String name;
  private int age;

  private Person() {
    this.name = Util.faker().name().firstName();
    this.age = Util.faker().random().nextInt(1, 30);
  }

  public static Person createRandomPerson() {
    return new Person();
  }
}
