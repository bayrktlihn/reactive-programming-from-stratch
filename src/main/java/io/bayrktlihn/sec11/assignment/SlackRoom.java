package io.bayrktlihn.sec11.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {

  private String name;
  private Sinks.Many<SlackMessage> sink;
  private Flux<SlackMessage> flux;

  private SlackRoom(final String name) {
    this.name = name;
    sink = Sinks.many().replay().all();
    flux = sink.asFlux();
  }

  public static SlackRoom create(final String name) {
    return new SlackRoom(name);
  }

  public void joinRoom(final SlackMember slackMember) {
    System.out.println(slackMember.getName() + "------------ Joined ------------" + name);
    subscribe(slackMember);
    slackMember.setMessageConsumer(msg -> postMessage(msg, slackMember));
  }

  private void subscribe(final SlackMember slackMember) {
    flux
        .filter(sm -> !sm.getSender().equals(slackMember.getName()))
        .doOnNext(sm -> sm.setReceiver(slackMember.getName()))
        .map(SlackMessage::toString)
        .subscribe(slackMember::receives);
  }

  private void postMessage(final String msg, final SlackMember slackMember) {
    final SlackMessage slackMessage = new SlackMessage();
    slackMessage.setSender(slackMember.getName());
    slackMessage.setMessage(msg);
    sink.tryEmitNext(slackMessage);

  }
}
