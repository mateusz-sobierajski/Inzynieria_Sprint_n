package com.example.inzynieria_sprint_n;

public class BudgetManager {

    private long currentAgencyBudget;

    long getCurrentBudget(){
        return currentAgencyBudget;
    }

    void setCurrentAgencyBudget(long newBudget){
        currentAgencyBudget = newBudget;
    }

    BudgetManager(){
        currentAgencyBudget = 0;
    }
}
