package io.bayrktlihn.sec11.assignment;

import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

public class SlackMember {

  @Getter
  private String name;
  @Setter
  private Consumer<String> messageConsumer;

  private SlackMember(final String name) {
    this.name = name;
  }

  public static SlackMember create(final String name) {
    return new SlackMember(name);
  }

  public void receives(final String message) {
    System.out.println(message);
  }

  public void says(final String message) {
    messageConsumer.accept(message);
  }

}
