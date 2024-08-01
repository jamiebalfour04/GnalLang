import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {
  public static void main(String[] args) {

    if(args.length > 0){
      if(new File(args[0]).exists()){

        try {
          new GnalRuntime(Files.readString(new File(args[0]).toPath(), StandardCharsets.UTF_8));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

      }
    }

  }

}