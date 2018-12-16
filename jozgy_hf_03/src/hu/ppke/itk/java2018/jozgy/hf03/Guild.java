package hu.ppke.itk.java2018.jozgy.hf03;

import java.util.ArrayList;

public class Guild {
	private String name;
	private ArrayList<Hero> members = new ArrayList<>();
	private int sumOfReputation;
	
	public synchronized int getSumOfReputation() {
		return sumOfReputation;
	}

	public synchronized void setSumOfReputation(int sumOfReputation) {
		this.sumOfReputation = sumOfReputation;
	}

	public void addMember(Hero hero) {
		members.add(hero);
	}

	public Guild(int sumOfGold) {
		this.sumOfReputation = sumOfGold;
	}

	public Guild(String name, ArrayList<Hero> members, int sumOfReputation) {
		super();
		this.name = name;
		this.members = members;
		this.sumOfReputation = sumOfReputation;
	}
	
	public Guild(String name, int sumOfReputation) {
		super();
		this.name = name;
		this.sumOfReputation = sumOfReputation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
