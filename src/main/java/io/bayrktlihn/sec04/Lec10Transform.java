package io.bayrktlihn.sec04;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec04.helper.Person;
import java.util.function.Function;
import reactor.core.publisher.Flux;

public class Lec10Transform {

  public static void main(String[] args) {
    getPerson()
        .transform(applyFilterMap())
        .subscribe(Util.subscriber());
  }

  public static Flux<Person> getPerson() {
    return Flux.range(1, 10)
        .map(i -> Person.createRandomPerson());
  }

  public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
    return flux -> flux.filter(p -> p.getAge() > 10)
        .doOnNext(p -> p.setName(p.getName().toUpperCase()))
        .doOnDiscard(Person.class, p -> System.out.println("Not allowing : " + p));
  }
}
