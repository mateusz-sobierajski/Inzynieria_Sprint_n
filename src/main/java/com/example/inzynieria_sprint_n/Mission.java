package com.example.inzynieria_sprint_n;

public class Mission {
    private String missionName;
    private String budgetString;
    private String priority;
    private boolean isBlacklisted;
    Mission(){
        missionName = "Mission Name";
        budgetString = "0";
        priority = "UNP";
        isBlacklisted = false;
    }

    Mission(String missionName, String budgetString, String priority, boolean isBlacklisted){
        this.missionName = missionName;
        this.budgetString = budgetString;
        this.priority = priority;
        this.isBlacklisted = isBlacklisted;
    }

    Mission(String[] missionDetails){
        this.missionName = missionDetails[0];
        this.budgetString = missionDetails[1];
        this.priority = missionDetails[2];
        if(missionDetails[3] == "true"){
            this.isBlacklisted = true;
        }
        else{
            this.isBlacklisted = false;
        }
    }

}
