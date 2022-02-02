package io.bayrktlihn.sec02;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec02.helper.NameGenerator;

public class Lec07FluxVsList {

  public static void main(String[] args) {

//    final List<String> names = NameGenerator.getNames(5);
//    System.out.println(names);

    NameGenerator.getNames(5)
        .subscribe(Util.onNext());

  }
}
