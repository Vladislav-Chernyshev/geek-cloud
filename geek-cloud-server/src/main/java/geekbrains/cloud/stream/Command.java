package geekbrains.cloud.stream;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private List toMerge = new ArrayList();
    private List toUnMerge = new ArrayList();

    public Command() {
    }

    public Command(List toMerge) {
        this.toMerge = toMerge;
    }

    public List getToMerge() {
        return toMerge;
    }

    public List getToUnMerge() {
        return toUnMerge;
    }

    public void execute(){
        getToMerge().forEach(System.out::println);

    }
}
