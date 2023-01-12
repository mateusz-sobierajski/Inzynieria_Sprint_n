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

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFoundException() throws IOException {
        new BudgetManager();
    }

    @Test(expected = IOException.class)
    public void testIOException() throws IOException {
        BudgetManager budgetManager = new BudgetManager();
        budgetManager.setCurrentAgencyBudget(1000000);
    }
}