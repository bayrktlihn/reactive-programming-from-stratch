package io.bayrktlihn.sec09.helper;

import com.github.javafaker.Book;
import io.bayrktlihn.courseutil.Util;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class BookOrder {

  private String title;
  private String author;
  private String category;
  private double price;

  private BookOrder() {
    final Book book = Util.faker().book();
    title = book.title();
    author = book.author();
    category = book.genre();
    price = Double.parseDouble(Util.faker().commerce().price());
  }

  public static BookOrder createRandomBookOrder() {
    return new BookOrder();
  }
}
