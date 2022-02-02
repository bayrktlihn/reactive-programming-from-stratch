package io.bayrktlihn.sec11;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec11.assignment.SlackMember;
import io.bayrktlihn.sec11.assignment.SlackRoom;

public class Lec07SlackDemo {

  public static void main(String[] args) {

    final SlackRoom slackRoom = SlackRoom.create("reactor");

    final SlackMember sam = SlackMember.create("sam");
    final SlackMember jake = SlackMember.create("jake");
    final SlackMember mike = SlackMember.create("mike");

    slackRoom.joinRoom(sam);
    slackRoom.joinRoom(jake);

    sam.says("Hi all..");
    Util.sleepSeconds(4);

    jake.says("Hey!");
    sam.says("I simply wanted to say hi..");
    Util.sleepSeconds(4);

    slackRoom.joinRoom(mike);
    mike.says("Hey guys.. glad to be here...");
  }
}
