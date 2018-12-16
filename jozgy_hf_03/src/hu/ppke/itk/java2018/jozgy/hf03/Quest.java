package hu.ppke.itk.java2018.jozgy.hf03;

import java.util.ArrayList;

public class Quest {
	private int rewardReputation;
	private double requiredTime;
	private int requiredReputation;
	private ArrayList<String> requiredEquipments = new ArrayList<>();
	
	public int getRewardReputation() {
		return rewardReputation;
	}

	public void setRewardReputation(int rewardReputation) {
		this.rewardReputation = rewardReputation;
	}

	public double getRequiredTime() {
		return requiredTime;
	}

	public void setRequiredTime(double requiredTime) {
		this.requiredTime = requiredTime;
	}

	public int getRequiredReputation() {
		return requiredReputation;
	}

	public void setRequiredReputation(int requiredReputation) {
		this.requiredReputation = requiredReputation;
	}

	public ArrayList<String> getRequiredEquipments() {
		return requiredEquipments;
	}

	public void setRequiredEquipments(ArrayList<String> requiredEquipments) {
		this.requiredEquipments = requiredEquipments;
	}

	public Quest(int rewardReputation, int requiredTime, int requiredReputation, ArrayList<String> requiredEquipments) {
		super();
		this.rewardReputation = rewardReputation;
		this.requiredTime = requiredTime;
		this.requiredReputation = requiredReputation;
		this.requiredEquipments = requiredEquipments;
	}

}