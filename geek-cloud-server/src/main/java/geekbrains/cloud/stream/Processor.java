package geekbrains.cloud.stream;

public interface Processor {

    int process(int x, int y, BinaryOperation operation);

}
