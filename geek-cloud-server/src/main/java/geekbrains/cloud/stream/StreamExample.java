package geekbrains.cloud.stream;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamExample {

    int to(String s){
        return s.length();
    }

    void interfaces(){
        Consumer<String> accept = System.out::println;
        accept.accept("hello");
        Predicate <Integer> predicate = (value) -> value > 10;
        predicate.test(15);
        Function<String, Integer> mapper = this::to;
        Supplier <Integer> supplier = () -> 1;
        supplier.get();

    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of("server-files", "article.txt");
        Map<String, Integer> wordsMap = Files.lines(path)
                .filter(StringUtils::isNotBlank)
                .flatMap(line -> Arrays.stream(line.split(" +")))
                .map(String::toLowerCase)
                .map(word -> word.replaceAll("\\W|[0-9]+|'s", ""))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> 1,
                        Integer::sum
                ));

        System.out.println(wordsMap);

        wordsMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
