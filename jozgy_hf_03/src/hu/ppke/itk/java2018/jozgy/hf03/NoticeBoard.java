package hu.ppke.itk.java2018.jozgy.hf03;

import java.util.ArrayList;

public class NoticeBoard {
	private ArrayList<Quest> availableQuests = new ArrayList<>();
	private ArrayList<String> availableItems = new ArrayList<>();
	
	public void generateGuilds(Queue queue) {
		ArrayList<Guild> guilds = new ArrayList<>();
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
		queue.setGuilds(guilds);
		
	}
	
	public ArrayList<Quest> getAvailableQuests() {
		return availableQuests;
	}

	public void addQuest(Quest newQuest) {
		availableQuests.add(newQuest);
	}
	
	public void removeQuest(Quest newQuest) {
		availableQuests.remove(newQuest);
	}
	
	public void addItem(String item) {
		availableItems.add(item);
	}
	
	public void removeItem(String item) {
		availableItems.remove(item);
	}

	public ArrayList<String> getAvailableItems() {
		return availableItems;
	}
}
