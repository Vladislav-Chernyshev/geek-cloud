package geekbrains.cloud.stream;

@FunctionalInterface
public interface BinaryOperation {

    int apply(int x, int y);
}
