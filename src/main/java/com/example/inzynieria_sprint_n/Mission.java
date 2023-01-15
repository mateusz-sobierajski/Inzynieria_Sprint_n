package com.example.inzynieria_sprint_n;

import java.util.Objects;

public class Mission {
    private String missionName;
    private String budgetString;
    private String priority;
    private boolean isBlacklisted;

    public Mission() {
        this.missionName = "Mission Name";
        this.budgetString = "0";
        this.priority = "UNP";
        isBlacklisted = false;
    }

    public Mission(String missionName, String budgetString, String priority, boolean isBlacklisted) {
        this.missionName = missionName;
        this.budgetString = budgetString;
        this.priority = priority;
        this.isBlacklisted = isBlacklisted;
    }

    public Mission(String missionName, String budgetString) {
        this.missionName = missionName;
        this.budgetString = budgetString;
        this.priority = "UNP";
        this.isBlacklisted = false;
    }

    public Mission(String[] missionDetails) {
        this.missionName = missionDetails[0];
        this.budgetString = missionDetails[1];
        this.priority = missionDetails[2];
        this.isBlacklisted = Objects.equals(missionDetails[3], "true");
    }

    public String getMissionName() {
        return missionName;
    }

    public String getBudgetString() {
        return budgetString;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isBlacklisted() {
        return isBlacklisted;
    }

    @Override
    public java.lang.String toString() {
        return this.missionName + ";" + this.budgetString + ";" + this.priority + ";" + this.isBlacklisted + "\n";
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public void setBudgetString(String budgetString) {
        this.budgetString = budgetString;
    }

    public void setBlacklisted(boolean isBlacklisted) {
        this.isBlacklisted = isBlacklisted;
    }
}
