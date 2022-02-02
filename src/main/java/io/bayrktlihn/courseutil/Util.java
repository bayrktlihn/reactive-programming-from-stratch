package io.bayrktlihn.courseutil;

import com.github.javafaker.Faker;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.reactivestreams.Subscriber;

public class Util {

  private static final Faker FAKER = Faker.instance();

  public static Consumer<Object> onNext() {
    return o -> System.out.println("Received : " + o);
  }

  public static Consumer<Throwable> onError() {
    return e -> System.out.println("ERROR: " + e.getMessage());
  }

  public static Runnable onComplete() {
    return () -> System.out.println("Completed");
  }

  public static Faker faker() {
    return FAKER;
  }

  public static void sleepSeconds(final int timeout) {
    try {
      TimeUnit.SECONDS.sleep(timeout);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void sleepMillis(final int timeout) {
    try {
      TimeUnit.MILLISECONDS.sleep(timeout);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static Subscriber<Object> subscriber() {
    return new DefaultSubscriber();
  }

  public static Subscriber<Object> subscriber(final String name) {
    return new DefaultSubscriber(name);
  }

}
