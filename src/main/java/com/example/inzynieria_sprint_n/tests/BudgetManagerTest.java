package com.example.inzynieria_sprint_n.tests;

import com.example.inzynieria_sprint_n.BudgetManager;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class BudgetManagerTest {
    @Test
    public void testSetCurrentAgencyBudget() throws IOException, URISyntaxException {
        BudgetManager budgetManager = new BudgetManager();
        long newBudget = 1000000;
        budgetManager.setCurrentAgencyBudget(newBudget);
        assertEquals(newBudget, budgetManager.getCurrentBudget());
    }

}