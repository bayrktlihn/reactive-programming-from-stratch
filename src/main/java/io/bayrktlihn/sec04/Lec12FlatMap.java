package io.bayrktlihn.sec04;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec04.helper.OrderService;
import io.bayrktlihn.sec04.helper.UserService;

public class Lec12FlatMap {

  public static void main(String[] args) {

    UserService.getUsers()
        .flatMap(user -> OrderService.getOrders(user.getUserId())) // mono / flux
//        .filter(p -> p > 10)
        .subscribe(Util.subscriber());

    Util.sleepSeconds(60);

  }
}
