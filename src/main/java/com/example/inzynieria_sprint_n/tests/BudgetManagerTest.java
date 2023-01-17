package com.example.inzynieria_sprint_n.tests;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.inzynieria_sprint_n.BudgetManager;
import org.junit.Before;
import org.junit.Test;

public class BudgetManagerTest {
    private BudgetManager budgetManager;
    private final long updatedBudget = 2000000;

    @Before
    public void setUp() throws Exception {
        // Create a test file with the initial budget
        File budgetFile = File.createTempFile("budget_file", ".csv");
        budgetFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(budgetFile)) {
            long initialBudget = 1000000;
            writer.write(Long.toString(initialBudget));
        }
        budgetManager = BudgetManager.getInstance();
    }

    @Test
    public void testGetCurrentBudget() {
        assertEquals(updatedBudget, budgetManager.getCurrentBudget());
    }

    @Test
    public void testSetCurrentAgencyBudget() throws IOException {
        budgetManager.setCurrentAgencyBudget(updatedBudget);
        assertEquals(updatedBudget, budgetManager.getCurrentBudget());
    }

    @Test(expected = IOException.class)
    public void testSetCurrentAgencyBudget_noAccess() throws IOException {
        // Make the budget file read-only
        budgetManager.getBudgetFile().setReadOnly();
        budgetManager.setCurrentAgencyBudget(updatedBudget);
    }
}