import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public static void main(String[] args) {
        String fileName = "D:\\test.txt";
        Path path = Paths.get(fileName);
        try(Stream<String> lines = Files.newBufferedReader(path).lines()){
            System.out.printf("strm 1"+lines.hashCode());
            Stream<String> lines2 = lines.map(e-> e.toUpperCase());
            System.out.println("strm 2" + lines2.hashCode());
            Stream<String> lines3 = lines2.filter(e-> e.contains("amardeep".toUpperCase()));
            System.out.println("strm3" + lines3.hashCode());
            lines3.forEach(System.out::println);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
