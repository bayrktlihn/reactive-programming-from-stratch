package io.bayrktlihn.sec09;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec09.helper.BookOrder;
import io.bayrktlihn.sec09.helper.RevenueReport;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;

public class Lec03Assignment {

  public static void main(String[] args) {
    final Set<String> allowedCategories = Stream.of(
        "Science fiction",
        "Fantasy",
        "Suspense/Thriller"
    ).collect(Collectors.toSet());

    bookStream()
        .filter(book -> allowedCategories.contains(book.getCategory()))
        .buffer(Duration.ofSeconds(5))
        .map(Lec03Assignment::revenueCalculator)
        .subscribe(Util.subscriber());

    Util.sleepSeconds(60);

  }

  private static RevenueReport revenueCalculator(final List<BookOrder> books) {
    final Map<String, Double> map = books.stream()
        .collect(Collectors.groupingBy(
            BookOrder::getCategory,
            Collectors.summingDouble(BookOrder::getPrice)
        ));

    return RevenueReport.create(map);
  }

  private static Flux<BookOrder> bookStream() {
    return Flux.interval(Duration.ofMillis(200))
        .map(i -> BookOrder.createRandomBookOrder());
  }
}
