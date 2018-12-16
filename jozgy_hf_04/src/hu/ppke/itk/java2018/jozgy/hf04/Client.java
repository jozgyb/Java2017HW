package hu.ppke.itk.java2018.jozgy.hf04;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client implements Runnable {
	
	boolean running = true;
	
	private InetAddress ip;
	private int port;
	private byte timeout;
	private PackageSender sender;
	private DatagramSocket socket;
	private Command commandMessage;
	
	

	public Client(String ip, int port, Command commandMessage) throws UnknownHostException {
		try {
			this.ip = InetAddress.getByName(ip);
			this.port = port;
			this.commandMessage = commandMessage;
			socket = new DatagramSocket();
			sender = new PackageSender(socket);
			new Thread(this).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void run() {
		while (running) {
			try {
				ArrayList<Command.Package> output = commandMessage.socketRead();
				
				for (int i = 0; i < output.size(); i++) {
					//TODO: kliens leallitasa
					sender.send(output.get(i).getCommand(), ip, port);
				}
				
				sender.receive(100);
				Command.Package tempPackage = new Command.Package(sender.getCommand(), ip, port);
				commandMessage.engineWrite(tempPackage);
				timeout = 0;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
