package com.example.inzynieria_sprint_n;

import java.util.Objects;

public class Mission {
    private String missionName;
    private long budget;
    private int priority;
    private boolean isBlacklisted;

    public Mission() {
        this.missionName = "Mission Name";
        this.budget = 0;
        this.priority = 0;
        isBlacklisted = false;
    }

    public Mission(String missionName, String budgetString, String priority, boolean isBlacklisted) {
        this.missionName = missionName;
        this.budget = Long.parseLong(budgetString);
        this.priority = Integer.parseInt(priority);
        this.isBlacklisted = isBlacklisted;
    }

    public Mission(String missionName, String budgetString) {
        this.missionName = missionName;
        this.budget = Long.parseLong(budgetString);
        this.priority = 0;
        this.isBlacklisted = false;
    }

    public Mission(String[] missionDetails) {
        this.missionName = missionDetails[0];
        this.budget = Long.parseLong(missionDetails[1]);
        this.priority = Integer.parseInt(missionDetails[2]);
        this.isBlacklisted = Objects.equals(missionDetails[3], "true");
    }

    public Mission(String missionName, String budgetString, boolean isBlacklisted) {
        this.missionName = missionName;
        this.budget = Long.parseLong(budgetString);
        this.priority = 0;
        this.isBlacklisted = isBlacklisted;
    }

    public String getMissionName() {
        return missionName;
    }

    public long getBudget() {
        return budget;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isBlacklisted() {
        return isBlacklisted;
    }

    @Override
    public java.lang.String toString() {
        return this.missionName + ";" + this.budget + ";" + this.priority + ";" + this.isBlacklisted;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setBlacklisted(boolean isBlacklisted) {
        this.isBlacklisted = isBlacklisted;
    }
}