package hu.ppke.itk.java2018.jozgy.hf03;

import java.util.ArrayList;
import java.util.Random;

public class Queue implements Runnable {
	private int randomNumber;
	private NoticeBoard board = new NoticeBoard();
	private ArrayList<Guild> guilds = new ArrayList<>();
	
	private String [] basicEquipments = 
		{
			"Infinity Edge",
			"Rapid firecanon",
			"Ninja Tabi",
			"Morellonomicon",
			"Berserker's greaves",
			"Black Cleaver",
			"Statik shiv",
			"Luden's echo",
			"Tear of the godess"		
		};
	
	private static Random rand2 = new Random();
	
	public String getRandomItem() {
		return basicEquipments[rand2.nextInt(basicEquipments.length)];
	}
	
	private static Random rand = new Random();

	public ArrayList<Guild> getGuilds() {
		return guilds;
	}

	public void setGuilds(ArrayList<Guild> guilds) {
		this.guilds = guilds;
	}

	public synchronized NoticeBoard getBoard() {
		return board;
	}

	public void setBoard(NoticeBoard board) {
		this.board = board;
	}
	
	
	protected synchronized void generateQuest(int incrementer) {
		int randReputation = randomNumber / 2;
		int randTime = 5000;
		int randRep = randomNumber / 4;
		int randReqRep = 5;
		
		ArrayList<String> randItems = new ArrayList<>();
		
		do {
			String currentItem = getRandomItem();
			randItems.add(currentItem);
			board.addItem(currentItem);
		} while(rand.nextInt(3)!= 0);
		
		board.addQuest(new Quest(rand.nextInt(randReputation), rand.nextInt(randTime), rand.nextInt(randRep*((incrementer*randReqRep) / randomNumber) + 1), randItems));
	}
	
	public Queue() {
		randomNumber = 30 * 2 * 2;
		new Thread(this).start();
	}
	
	public Boolean isDayOver() {
		return (randomNumber == -1);
	}
	
	public void generateGuilds(Queue queue) {
		guilds.add(new Guild("Ionia", 0));
		guilds.get(0).addMember(new Hero("Irelia", 0, 0, queue));
		guilds.get(0).addMember(new Hero("Yasuo", 0, 0, queue));
		guilds.add(new Guild("Freljord", 0));
		guilds.get(1).addMember(new Hero("Rammus", 0, 1, queue));
		guilds.get(1).addMember(new Hero("Ashe", 0, 1, queue));
		guilds.add(new Guild("Void", 0));
		guilds.get(2).addMember(new Hero("KhaZix", 0, 2, queue));
		guilds.get(2).addMember(new Hero("Kassadin", 0, 2, queue));
		guilds.add(new Guild("Explorers", 0));
		guilds.get(3).addMember(new Hero("Ezreal", 0, 3, queue));
		guilds.get(3).addMember(new Hero("Taric", 0, 3, queue));
		
	}

	@Override
	public void run() {
		int incrementer = 0;
		while (incrementer < randomNumber) {
			generateQuest(incrementer++);
			generateQuest(incrementer++);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		randomNumber = -1;
	}
	
	

}