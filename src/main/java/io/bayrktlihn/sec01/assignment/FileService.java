package io.bayrktlihn.sec01.assignment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import reactor.core.publisher.Mono;

public class FileService {

  private static final Path PATH = Paths.get("src/main/resources/assignment/sec01");

  public static Mono<String> read(final String fileName) {
    return Mono.fromSupplier(() -> readFile(fileName));
  }

  public static Mono<Void> write(final String fileName, final String content) {
    return Mono.fromRunnable(() -> writeFile(fileName, content));
  }

  public static Mono<Void> delete(final String fileName) {
    return Mono.fromRunnable(() -> deleteFile(fileName));
  }

  private static String readFile(final String fileName) {
    try (final InputStream inputStream = Files.newInputStream(PATH.resolve(fileName))) {
      final StringBuilder fileContent = new StringBuilder();
      final int bufferSize = 1024;
      byte[] buffer = new byte[bufferSize];
      int readBufferSize = inputStream.read(buffer);

      while (readBufferSize != -1) {
        if (readBufferSize != bufferSize) {
          buffer = Arrays.copyOf(buffer, readBufferSize);
        }
        fileContent.append(new String(buffer));

        readBufferSize = inputStream.read(buffer);
      }
      return fileContent.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void writeFile(final String fileName, final String content) {

    try (final OutputStream outputStream = Files.newOutputStream(PATH.resolve(fileName), StandardOpenOption.WRITE)) {
      outputStream.write(content.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void deleteFile(final String fileName) {
    try {
      Files.delete(PATH.resolve(fileName));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
