package com.example.inzynieria_sprint_n.tests;

import com.example.inzynieria_sprint_n.Mission;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MissionTest {
    private final String missionName = "Test Mission";
    private final long budget = 1000000;
    private final int priority = 1;
    private final boolean isBlacklisted = false;

    @Test
    public void testDefaultConstructor() {
        Mission mission = new Mission();
        assertEquals("Mission Name", mission.getMissionName());
        assertEquals(0, mission.getBudget());
        assertEquals(0, mission.getPriority());
        assertFalse(mission.isBlacklisted());
    }

    @Test
    public void testStringConstructor() {
        Mission mission = new Mission(missionName, Long.toString(budget), Integer.toString(priority), isBlacklisted);
        assertEquals(missionName, mission.getMissionName());
        assertEquals(budget, mission.getBudget());
        assertEquals(priority, mission.getPriority());
        assertEquals(isBlacklisted, mission.isBlacklisted());
    }

    @Test
    public void testStringArrayConstructor() {
        String[] missionDetails = {missionName, Long.toString(budget), Integer.toString(priority), Boolean.toString(isBlacklisted)};
        Mission mission = new Mission(missionDetails);
        assertEquals(missionName, mission.getMissionName());
        assertEquals(budget, mission.getBudget());
        assertEquals(priority, mission.getPriority());
        assertEquals(isBlacklisted, mission.isBlacklisted());
    }

    @Test
    public void testSetters() {
        Mission mission = new Mission();
        mission.setMissionName(missionName);
        mission.setBudget(budget);
        mission.setPriority(priority);
        mission.setBlacklisted(isBlacklisted);

        assertEquals(missionName, mission.getMissionName());
        assertEquals(budget, mission.getBudget());
        assertEquals(priority, mission.getPriority());
        assertEquals(isBlacklisted, mission.isBlacklisted());
    }

    @Test
    public void testToString() {
        Mission mission = new Mission(missionName, Long.toString(budget), Integer.toString(priority), isBlacklisted);
        assertEquals(missionName + ";" + budget + ";" + priority + ";" + isBlacklisted, mission.toString());
    }
}