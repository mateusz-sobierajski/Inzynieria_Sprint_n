package com.example.inzynieria_sprint_n;

import java.util.Objects;

public class Mission {
    private final String missionName;
    private final String budgetString;
    private final String priority;
    private final boolean isBlacklisted;

    public Mission(String missionName, String budgetString, String priority, String s) {
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
}
