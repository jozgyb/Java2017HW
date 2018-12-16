package hu.ppke.itk.java2018.jozgy.hf04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class PackageSender {

	private DatagramPacket input;
	private DatagramPacket output;
	private byte[] inputInByte = new byte[1024];
	private DatagramSocket socket;

	public PackageSender(DatagramSocket socket) {
		this.socket = socket;
	}

	public void receive(int timeout) throws SocketTimeoutException, IOException {

		socket.setSoTimeout(timeout);
		inputInByte = new byte[1024];
		input = new DatagramPacket(inputInByte, inputInByte.length);
		socket.receive(input);
	}

	public void receive() throws SocketTimeoutException, IOException {

		socket.setSoTimeout(0);
		inputInByte = new byte[1024];
		input = new DatagramPacket(inputInByte, inputInByte.length);
		socket.receive(input);
	}
	
	public void send(String command, InetAddress ip, int port) throws IOException {
		byte[] outputInByte = new byte[1024];
		outputInByte = command.getBytes();
		output = new DatagramPacket(outputInByte, outputInByte.length, ip, port);
		socket.send(output);
	}
	
	public String getCommand() {
		return new String(inputInByte, 0, input.getLength());
	}

	public InetAddress getIP() {
		return input.getAddress();
	}
	
	public int getPort() {
		return input.getPort();
	}
}
