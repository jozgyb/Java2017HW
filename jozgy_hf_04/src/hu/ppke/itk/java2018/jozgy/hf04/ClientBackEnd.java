package hu.ppke.itk.java2018.jozgy.hf04;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientBackEnd implements Runnable {
	
	private Command commandMessage;
	private String userName = new String("");
	
	public ClientBackEnd(Command commandMessage) {
		this.commandMessage = commandMessage;
		new Thread(this).start();
	}

	private void handleCommand(Command.Package input) {
		Instant instant = Instant.now();
		System.out.println(instant + "User: " + userName + " is handling: " + input.getCommand());
		
		ArrayList<String> tempCommands = new ArrayList<>(Arrays.asList(input.getCommand().split("\\s")));
		
		
		switch (tempCommands.get(0)) {
		case "chatMessage":
			//TODO: textarea append
			commandMessage.socketWrite(new Command.Package(input.getCommand()));
			break;
		case "setPlayerName":
			userName = tempCommands.get(1);
			
			//TODO: inditson egy kliens a nevvel
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public void run() {
		try {
			ArrayList<Command.Package> input = commandMessage.socketRead();
			
			for (int i = 0; i < input.size(); i++) {
				handleCommand(input.get(i));
			}
			
			Thread.sleep(1000);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
