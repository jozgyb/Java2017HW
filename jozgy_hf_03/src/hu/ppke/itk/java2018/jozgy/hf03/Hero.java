package hu.ppke.itk.java2018.jozgy.hf03;

import java.time.Instant;
import java.util.ArrayList;

public class Hero implements Runnable {
	private String name;
	private int reputation;
	private int guildID;
	private ArrayList<String> inventory = new ArrayList<>();
	private Quest currentQuest;
	private Queue queue;

	private static Object questQueue = new Object();
	private static Object storageQueue = new Object();

	public Hero(String name, int notoriety) {
		super();
		this.name = name;
		this.reputation = notoriety;
		new Thread(this).start();
	}
	
	public Hero(String name, int notoriety, int guildID, Queue queue) {
		super();
		this.name = name;
		this.reputation = notoriety;
		this.guildID = guildID;
		this.queue = queue;
		new Thread(this).start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public void setInventory(String item) {
		inventory.add(item);
	}

	public void removeFromInventory(String item) {
		inventory.remove(item);
	}

	protected void approachNoticeBoard() {
		Instant instant = Instant.now();
		System.out.println(instant + " " + name + " " + "odamegy a hirdetőtáblához és végignézi a listát.");
	}

	protected void pickQuest() {
		for (int i = queue.getBoard().getAvailableQuests().size() - 1; i >= 0; i--) {
			if (queue.getBoard().getAvailableQuests().get(i).getRequiredReputation() <= reputation) {
				Instant instant = Instant.now();
				System.out.println(
						instant + " " + name + " " + "elvállalja a " + ". küldetést, és átadja a helyét a táblánál.");
				currentQuest = queue.getBoard().getAvailableQuests().get(i);
				queue.getBoard().getAvailableQuests().remove(i);
				break;
			} else {
				Instant instant = Instant.now();
				System.out.println(instant + " " + name + " " + "nem talált megfelelő küldetést, ezért pihen.");
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	protected void checkStorage() {
		ArrayList<String> availableItems = queue.getBoard().getAvailableItems();
		ArrayList<String> requiredItems = currentQuest.getRequiredEquipments();
		for (int j = 0; j < requiredItems.size(); j++) {
			for (int i = 0; i < availableItems.size(); i++) {
				if (!inventory.contains(requiredItems.get(i)) && requiredItems.get(j) == availableItems.get(i)) {
					inventory.add(availableItems.get(i));
					queue.getBoard().removeItem(availableItems.get(i));
				}
			}
		}

		if (inventory.size() == requiredItems.size()) {
			Instant instant = Instant.now();
			System.out.printf(instant + " " + name + "felmarkolja a {");
			for (int i = 0; i < inventory.size(); i++) {
				if (i != inventory.size() - 1) {
					System.out.println(inventory.get(i) + ", ");
				} else {
					System.out.println(inventory.get(i) + "}-t  és elindul a küldetésre.\n");
				}
				try {
					Thread.sleep((long) currentQuest.getRequiredTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		
		// TODO ha nem talalja az itemet
	}

	protected void returnFromQuest() {
		for (int i = 0; i < inventory.size(); i++) {
			queue.getBoard().addItem(inventory.get(i));
		}
		inventory.clear();

		reputation += currentQuest.getRewardReputation();

		Instant instant = Instant.now();
		System.out.println(instant + " " + name + "teljesítette a küldetést visszateszi a raktárba"
				+ " a felszereléseket és felramkolja a(z) " + currentQuest.getRewardReputation() + " hírnevet.");
	}

	protected void returnToQueue() {
		Instant instant = Instant.now();
		System.out.println(instant + " " + name + "bejegyzi a szovetsegenel (" + queue.getGuilds().get(guildID).getName() + ") majd visszamegy a hirdetotablanal varakozok koze.");
		queue.getGuilds().get(guildID).setSumOfReputation(queue.getGuilds().get(guildID).getSumOfReputation() + currentQuest.getRewardReputation());
		this.run();
	}
	
	protected void endDay() {
		Instant instant = Instant.now();
		System.out.println(instant + " " + name + "befejezte a munkat mara es lepihent.");
	}
	

	@Override
	public void run() {
		while (!queue.isDayOver()) {
			synchronized (questQueue) {
				approachNoticeBoard();
				pickQuest();
			}
			while (true) {
				synchronized (storageQueue) {
					checkStorage();
				}
				if (inventory.size() == currentQuest.getRequiredEquipments().size()) {
					break;
				} else {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			synchronized (storageQueue) {
				returnFromQuest();
			}
			returnToQueue();
		}
		endDay();
	}

}
