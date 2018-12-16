package hu.ppke.itk.java2018.jozgy.hf04;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;

public class Command {

	static public class Package {
		private String command;
		private InetAddress ip;
		private int port;
		private int repeatTime;
		private boolean needsAddress;

		public Package(String command, InetAddress ip, int port, int repeatTime) {
			this.command = command;
			this.ip = ip;
			this.port = port;
			this.repeatTime = repeatTime;
			this.needsAddress = true;
		}

		public Package(String command, InetAddress ip, int port) {
			this.command = command;
			this.ip = ip;
			this.port = port;
			this.repeatTime = 1;
			this.needsAddress = true;
		}
		
		public Package(String command) {
			this.command = command;
			this.needsAddress = true;
			this.repeatTime = 1;
		}

		public String getCommand() {
			return command;
		}

		public InetAddress getIp() {
			return ip;
		}

		public int getPort() {
			return port;
		}

		public int getRepeatTime() {
			return repeatTime;
		}
		
		public boolean needsAddress() {
			return needsAddress;
		}
	}

	private ArrayList<Package> socket = new ArrayList<>();
	private Object lockOfSocket = new Object();

	public void socketWrite(Package newPackage) {
		synchronized (lockOfSocket) {
			socket.add(newPackage);
		}
	}

	public ArrayList<Package> socketRead() {
		synchronized (lockOfSocket) {
			ArrayList<Package> tempForRead = new ArrayList<>(socket);
			Iterator<Package> socketIt = socket.iterator();

			while (socketIt.hasNext()) {

				Package temp = socketIt.next();
				temp.repeatTime -= 1;

				if (temp.repeatTime < 1) {
					socketIt.remove();
				}
			}
			return tempForRead;
		}
	}

	private ArrayList<Package> engine = new ArrayList<>();
	private Object lockOfEngine = new Object();

	public void engineWrite(Package newPackage) {
		synchronized (lockOfEngine) {
			engine.add(newPackage);
		}
	}

	public ArrayList<Package> engineRead() {
		synchronized (lockOfEngine) {
			ArrayList<Package> tempForRead = new ArrayList<>(engine);
			Iterator<Package> socketIt = engine.iterator();
			while (socketIt.hasNext()) {

				Package temp = socketIt.next();
				temp.repeatTime -= 1;

				if (temp.repeatTime < 1) {
					socketIt.remove();
				}
			}
			return tempForRead;
		}
	}
	
	private ArrayList<Package> frontEnd = new ArrayList<>();
	private Object lockOfFrontEnd = new Object();
	
	public void frontEndWrite(Package newPackage) {
		synchronized (lockOfFrontEnd) {
			frontEnd.add(newPackage);
		}
	}
	
	public ArrayList<Package> frontEndRead() {
		synchronized (lockOfFrontEnd) {
			ArrayList<Package> tempForRead = new ArrayList<>(frontEnd);
			Iterator<Package> socketIt = frontEnd.iterator();
			while (socketIt.hasNext()) {

				Package temp = socketIt.next();
				temp.repeatTime -= 1;

				if (temp.repeatTime < 1) {
					socketIt.remove();
				}
			}
			return tempForRead;
		}
	}

}