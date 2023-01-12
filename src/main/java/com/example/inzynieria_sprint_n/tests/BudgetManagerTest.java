package com.example.inzynieria_sprint_n.tests;

import static org.junit.Assert.*;

import com.example.inzynieria_sprint_n.BudgetManager;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BudgetManagerTest {
    @Test
    public void testSetCurrentAgencyBudget() throws IOException {
        BudgetManager budgetManager = new BudgetManager();
        long newBudget = 1000000;
        budgetManager.setCurrentAgencyBudget(newBudget);
        assertEquals(newBudget, budgetManager.getCurrentBudget());
    }

}