package io.bayrktlihn.sec01;

import io.bayrktlihn.courseutil.Util;
import io.bayrktlihn.sec01.assignment.FileService;

public class Lec09AssignmentDemo {

  public static void main(String[] args) {
    FileService.read("file01.txt")
        .subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onComplete()
        );

    FileService.delete("file04.txt")
        .subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onComplete()
        );

//    FileService.write("file03.txt", "This is file3")
//        .subscribe(
//            Util.onNext(),
//            Util.onError(),
//            Util.onComplete()
//        );
  }
}
