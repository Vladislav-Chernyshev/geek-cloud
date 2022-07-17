package geekbrains.cloud.stream;

import java.util.List;

public class CommandTest {

    public static void main(String[] args) {
        CompoundCommand cmd = new CompoundCommand(List.of(
                new CompoundCommand(List.of(
                        new Command(List.of(1 ,2)),
                        new Command(List.of(3))
                )),
                new Command(List.of(4,5,6)),
                new Command(List.of(7,8,9))
        ));
        cmd.execute();
        System.out.println(cmd.getToMerge());
    }
}
