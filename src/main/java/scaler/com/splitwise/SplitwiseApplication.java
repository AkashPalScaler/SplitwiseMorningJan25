package scaler.com.splitwise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import scaler.com.splitwise.commands.SettleUpGroupCommand;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {
	@Autowired
	SettleUpGroupCommand settleUpGroupCommand;
	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Run datagenerator here -> only once, after that comment it out
		// while loop -> take input -> commandExecuter
		settleUpGroupCommand.execute("settleupgroup 1");
	}
}
