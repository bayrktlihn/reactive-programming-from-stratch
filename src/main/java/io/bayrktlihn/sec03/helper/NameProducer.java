package io.bayrktlihn.sec03.helper;

import io.bayrktlihn.courseutil.Util;
import java.util.function.Consumer;
import reactor.core.publisher.FluxSink;

public class NameProducer implements Consumer<FluxSink<String>> {

  private FluxSink<String> fluxSink;

  @Override
  public void accept(final FluxSink<String> fluxSink) {
    this.fluxSink = fluxSink;
  }

  public void produce() {
    final String name = Util.faker().name().fullName();
    final String thread = Thread.currentThread().getName();
    this.fluxSink.next(thread + " : " + name);
  }
}
