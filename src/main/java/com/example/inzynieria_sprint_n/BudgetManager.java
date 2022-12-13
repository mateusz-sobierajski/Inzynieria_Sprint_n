package com.example.inzynieria_sprint_n;

public class BudgetManager {
    //TODO zdecydować czy zrobić osobną klasę do algorytmu czy wrzucać tu
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
