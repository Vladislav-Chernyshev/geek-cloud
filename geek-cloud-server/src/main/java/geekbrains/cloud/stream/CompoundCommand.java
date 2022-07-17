package geekbrains.cloud.stream;

import java.util.List;

public class CompoundCommand extends Command{

    private List<Command> commands;

    public CompoundCommand(List<Command> commands){
        this.commands = commands;
    }

    @Override
    public List getToMerge() {
        return commands.stream()
                .flatMap(cmd -> cmd.getToMerge().stream())
                .toList();
    }


    @Override
    public void execute() {
        commands.forEach(Command::execute);
    }
}
