package io.bayrktlihn.courseutil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber implements Subscriber<Object> {

  private final String name;

  public DefaultSubscriber(final String name) {
    this.name = name;
  }

  public DefaultSubscriber() {
    name = "";
  }

  @Override
  public void onSubscribe(final Subscription s) {
    s.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final Object o) {
    System.out.println(name + "Received : " + o);
  }

  @Override
  public void onError(final Throwable t) {

    System.out.println(name + "ERROR : " + t.getMessage());
  }

  @Override
  public void onComplete() {

    System.out.println(name + "Completed");
  }
}
