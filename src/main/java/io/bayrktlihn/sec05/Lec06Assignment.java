package io.bayrktlihn.sec05;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec05.assignment.InventoryService;
import io.bayrktlihn.sec05.assignment.OrderService;
import io.bayrktlihn.sec05.assignment.RevenueService;

public class Lec06Assignment {

  public static void main(String[] args) {

    final OrderService orderService = new OrderService();
    final RevenueService revenueService = new RevenueService();
    final InventoryService inventoryService = new InventoryService();

//    revenue and inv - observe the order stream
    orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
    orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

    inventoryService.inventoryStream()

        .subscribe(Util.subscriber("inventory"));

    revenueService.revenueStream()
        .subscribe(Util.subscriber("revenue"));

    Util.sleepSeconds(60);

  }
}
