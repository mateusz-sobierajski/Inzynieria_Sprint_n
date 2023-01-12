package com.example.inzynieria_sprint_n;

public class Mission {
    private String missionName;
    private String budgetString;
    private String priority;
    private boolean isBlacklisted;

    public Mission() {
        missionName = "Mission Name";
        budgetString = "0";
        priority = "UNP";
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
        if (missionDetails[3] == "true") {
            this.isBlacklisted = true;
        } else {
            this.isBlacklisted = false;
        }
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
