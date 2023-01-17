package com.example.inzynieria_sprint_n.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.inzynieria_sprint_n.Mission;
import com.example.inzynieria_sprint_n.MissionDistributor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class MissionDistributorTest {
    private final int capacity = 15;
    private File proposedMissionsFile;
    private File chosenMissionsFile;
    private MissionDistributor missionDistributor = MissionDistributor.getInstance();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        // Create a test file with proposed missions
        proposedMissionsFile = folder.newFile("proposed_mission_list.csv");
        try (FileWriter writer = new FileWriter(proposedMissionsFile)) {
            writer.write("Mission 1;5;3;false\n");
            writer.write("Mission 2;7;5;false\n");
            writer.write("Mission 3;3;2;false\n");
            writer.write("Mission 4;8;8;false\n");
            writer.write("Mission 5;2;1;false\n");
            writer.write("Mission 6;9;3;true\n");
        }

        // Set the path to the proposed missions file
        System.setProperty("proposed_mission_list", proposedMissionsFile.getAbsolutePath());
    }

    @Test
    public void testChooseMissions() throws IOException {
        List<Mission> selectedMissions = missionDistributor.chooseMissions(capacity);

        // Check if the selected missions are correct
        assertEquals(4, selectedMissions.size());

        assertEquals("Mission 2", selectedMissions.get(0).getMissionName());
        assertEquals(700000, selectedMissions.get(0).getBudget());
        assertEquals(5, selectedMissions.get(0).getPriority());

        assertEquals("Mission 4", selectedMissions.get(1).getMissionName());
        assertEquals(800000, selectedMissions.get(1).getBudget());
        assertEquals(8, selectedMissions.get(1).getPriority());

        assertEquals("Mission 1", selectedMissions.get(2).getMissionName());
        assertEquals(500000, selectedMissions.get(2).getBudget());
        assertEquals(3, selectedMissions.get(2).getPriority());

        assertEquals("Mission 3", selectedMissions.get(3).getMissionName());
        assertEquals(300000, selectedMissions.get(3).getBudget());
        assertEquals(2, selectedMissions.get(3).getPriority());

        // Check if the chosen missions file has been written correctly
        try (FileReader fileReader = new FileReader(chosenMissionsFile)) {
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withDelimiter(';').withIgnoreEmptyLines(true));
            List<CSVRecord> csvRecords = csvParser.getRecords();
            csvParser.close();

            assertEquals("Mission 2", csvRecords.get(0).get(0));
            assertEquals("700000", csvRecords.get(0).get(0));
            //assertEquals("Mission 2", csvRecords.get(0).get(0));
            //assertEquals("700000", csvRecords.get(0).get(0));

        }
    }
}