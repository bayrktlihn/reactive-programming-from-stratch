package io.bayrktlihn.sec03.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class FileReaderService {

  private Callable<BufferedReader> openReader(final Path path) {
    return () -> Files.newBufferedReader(path);
  }

  private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
    return (br, sink) -> {
      try {
        final String line = br.readLine();
        System.out.println("reading --- " + line);
        if (Objects.isNull(line)) {
          sink.complete();
        } else {
          sink.next(line);
        }
      } catch (IOException e) {
        sink.error(e);
      }
      return br;
    };
  }

  private Consumer<BufferedReader> closeReader() {
    return br -> {
      try {
        br.close();
        System.out.println("--closed");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };
  }

  public Flux<String> read(final Path path) {
    return Flux.generate(
        openReader(path),
        read(),
        closeReader()
    );
  }

}
