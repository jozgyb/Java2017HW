package hu.ppke.itk.java2018.jozgy.hf04;

import java.net.InetAddress;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameServer implements Runnable{
	boolean running = true;
	private Command commandMessage;
	
	private HashMap<String, String> joinedClients = new HashMap<>();
	private ArrayList<InetAddress> clientIPs = new ArrayList<>();
	private ArrayList<Integer> clientPorts = new ArrayList<>();
	
	public GameServer(Command commandMessage) {
		this.commandMessage = commandMessage;
		
		new Thread(this).start();
	}
	
	private void getClientInformation(Command.Package input) {
		String clientID = input.getIp().toString() + ":" + input.getPort();
		
		if(!joinedClients.containsKey(clientID)) {
			clientIPs.add(input.getIp());
			clientPorts.add(input.getPort());
			joinedClients.put(clientID, "online");
		}
	}
	
	private void broadcastChatMessage(String message) {
		for(int m = 0; m < clientIPs.size(); m++) {
			commandMessage.socketWrite(new Command.Package(message, clientIPs.get(m), clientPorts.get(m)));
		}
	}
	
	private void handleCommand(Command.Package input) {
		Instant instant = Instant.now();
		System.out.println(instant + "GameServer Handling Command: " + input.getCommand());
		
		ArrayList<String> tempCommands = new ArrayList<>(Arrays.asList(input.getCommand().split("\\s")));
		
		switch (tempCommands.get(0)) {
		case "chatMessage":
			broadcastChatMessage(input.getCommand());
			break;

		default:
			break;
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				ArrayList<Command.Package> input = commandMessage.engineRead();
				
				for (int i = 0; i < input.size(); i++) {
					getClientInformation(input.get(i));
					//TODO: handle input
					handleCommand(input.get(i));
				}
				
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void stop() {
		Instant instant = Instant.now();
		System.out.println(instant + "GameServerEngine SHUTDOWN");
		running = false;
	}

}
