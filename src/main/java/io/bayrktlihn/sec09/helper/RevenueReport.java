package io.bayrktlihn.sec09.helper;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.ToString;

@ToString
public class RevenueReport {

  private LocalDateTime localDateTime = LocalDateTime.now();
  private Map<String, Double> revenue;

  private RevenueReport(final Map<String, Double> revenue) {
    this.revenue = revenue;
  }

  public static RevenueReport create(final Map<String, Double> revenue) {
    return new RevenueReport(revenue);
  }
}
