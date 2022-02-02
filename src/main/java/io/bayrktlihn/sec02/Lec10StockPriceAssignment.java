package io.bayrktlihn.sec02;

import io.bayrktlihn.sec02.assignment.StockPricePublisher;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Lec10StockPriceAssignment {

  public static void main(String[] args) {

    final CountDownLatch latch = new CountDownLatch(1);

    StockPricePublisher.getPrice().subscribeWith(
        new Subscriber<Integer>() {

          private Subscription subscription;

          @Override
          public void onSubscribe(final Subscription s) {
            subscription = s;
            s.request(Long.MAX_VALUE);
          }

          @Override
          public void onNext(final Integer price) {
            System.out.println(LocalDateTime.now() + " : Price : " + price);
            if (price > 110 || price < 90) {
              this.subscription.cancel();
              ;
              latch.countDown();
            }
          }

          @Override
          public void onError(final Throwable t) {
            latch.countDown();
          }

          @Override
          public void onComplete() {
            latch.countDown();
          }
        }
    );

    try {
      latch.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
