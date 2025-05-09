package scaler.com.splitwise.commands;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CommandExecutor {
    List<Command> commands;

    public void registerCommand(Command command){
        commands.add(command);
    }

    public void deregisterCommmand(Command command){
        commands.remove(command);
    }

    public void execute(String input){
        for(Command command : commands){
            if(command.matches(input)){
                command.execute(input);
            }
        }
    }
}
