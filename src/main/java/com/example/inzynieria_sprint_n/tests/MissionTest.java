package com.example.inzynieria_sprint_n.tests;

import com.example.inzynieria_sprint_n.Mission;
import org.junit.Test;
import static org.junit.Assert.*;

public class MissionTest {
    @Test
    public void testDefaultConstructor() {
        Mission mission = new Mission();
        assertEquals("Mission Name", mission.getMissionName());
        assertEquals("0", mission.getBudgetString());
        assertEquals("UNP", mission.getPriority());
        assertFalse(mission.isBlacklisted());
    }

    @Test
    public void testFullConstructor() {
        Mission mission = new Mission("Test Mission", "1000", "HIGH", true);
        assertEquals("Test Mission", mission.getMissionName());
        assertEquals("1000", mission.getBudgetString());
        assertEquals("HIGH", mission.getPriority());
        assertTrue(mission.isBlacklisted());
    }

    @Test
    public void testArrayConstructor() {
        String[] missionDetails = {"Test Mission", "1000", "HIGH", "true"};
        Mission mission = new Mission(missionDetails);
        assertEquals("Test Mission", mission.getMissionName());
        assertEquals("1000", mission.getBudgetString());
        assertEquals("HIGH", mission.getPriority());
        assertTrue(mission.isBlacklisted());
    }

    @Test
    public void testToString() {
        Mission mission = new Mission("Test Mission", "1000", "HIGH", true);
        assertEquals("Test Mission;1000;HIGH;true\n", mission.toString());
    }

    @Test
    public void testGetMissionName() {
        Mission mission = new Mission("Test Mission", "1000", "HIGH", true);
        assertEquals("Test Mission", mission.getMissionName());
    }

    @Test
    public void testGetBudgetString() {
        Mission mission = new Mission("Test Mission", "1000", "HIGH", true);
        assertEquals("1000", mission.getBudgetString());
    }

    @Test
    public void testGetPriority() {
        Mission mission = new Mission("Test Mission", "1000", "HIGH", true);
        assertEquals("HIGH", mission.getPriority());
    }

    @Test
    public void testIsBlacklisted() {
        Mission mission = new Mission("Test Mission", "1000", "HIGH", true);
        assertTrue(mission.isBlacklisted());
    }

}
