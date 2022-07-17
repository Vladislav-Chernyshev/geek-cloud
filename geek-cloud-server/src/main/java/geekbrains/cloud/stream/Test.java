package geekbrains.cloud.stream;

public class Test {

    private static void testLambda() {
        BinaryOperation sum = (x, y) -> x + y;
        System.out.println(sum.apply(1, 2));
    }

    private static void testMethodReference() {
        BinaryOperation sum = Integer::sum;
        System.out.println(sum.apply(1, 2));
    }

    private static void execute(JobExecutor executor) {
        executor.execute();
    }

    private static void run() {
        System.out.println("Execute");
    }

    private static int multiply(int x, int y) {
        return x * y;
    }

    private static int calculate(int x, int y, BinaryOperation operation) {
        return operation.apply(x, y);
    }


    public static void main(String[] args) {
        testLambda();
        testMethodReference();
        execute(Test::run);
        Processor processor = Test::calculate;
        System.out.println(processor.process(2, 4, Test::multiply));

    }
}
