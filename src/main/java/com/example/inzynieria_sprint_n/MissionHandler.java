package com.example.inzynieria_sprint_n;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Klasa MissionHandler która obsługuje ekran posiadające karty dodania misji i pokazania wybranych misji
 */
public class MissionHandler implements Initializable {

    protected final List<Mission> missionArrayList;
    @FXML
    public ListView<Mission> missionChosenListViewId;
    protected List<Mission> chosenMissionArrayList;
    @FXML
    private ListView<Mission> missionListView;
    public Button deleteMissionBtnId;
    public Button addMissionBtnId;
    public Button budgetBtnId;
    public Button editMissionBtnId;
    public Button generateBtnId;
    @FXML
    private TextField missionNameTextField;
    @FXML
    private TextField budgetTextField;
    @FXML
    private CheckBox blacklistedCheckBox;
    private boolean isEditMode = false;
    private Mission selectedMission;
    private ObservableList<Mission> allMissions;

    /**
     * metoda handleEditBtn obsługuje przycisk "Edit" znajdujący się na ekranie dodania misji.
     */

    @FXML
    public void handleEditBtn() {
        /*
        Mission selectedMission = missionListView.getSelectionModel().getSelectedItem();
        if (selectedMission != null) {
            String newMissionName = missionNameTextField.getText();
            String newBudgetString = budgetTextField.getText();
            boolean newIsBlacklisted = blacklistedCheckBox.isSelected();

            selectedMission.setMissionName(newMissionName);
            selectedMission.setBudget(Long.parseLong(newBudgetString));
            selectedMission.setBlacklisted(newIsBlacklisted);
            missionListView.refresh();

            // Update the csv file with the edited mission
            URL fileUrl = getClass().getResource("/csv/proposed_mission_list.csv");
            File fileMissionList = Paths.get(Objects.requireNonNull(fileUrl).toURI()).toFile();
            try (Reader reader = new FileReader(fileMissionList);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
                List<CSVRecord> csvRecords = csvParser.getRecords();
                for (int i = 0; i < csvRecords.size(); i++) {
                    CSVRecord record = csvRecords.get(i);
                    if (record.get(0).equals(selectedMission.getMissionName())) {
                        csvRecords.set(i, CSVFormat.DEFAULT.parse(reader).getRecords().get(0));
                        break;
                    }
                }
                try (FileWriter fileWriter = new FileWriter(fileMissionList);
                     CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
                    csvPrinter.printRecords(csvRecords);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

         */
    }

    /**
     * metoda handleBudgetBtn obsługuje zdarzenie przycisku pokazującego budżet jako wykres kołowy
     *
     * @param event
     */
    @FXML
    public void handleBudgetBtn(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Budget_ass_screen.fxml")));
            Scene budgetScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(budgetScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        missionListView.setOnMouseClicked(event -> {
            Mission selectedMission = missionListView.getSelectionModel().getSelectedItem();
            if (selectedMission != null) {
                missionNameTextField.setText(selectedMission.getMissionName());
                budgetTextField.setText(String.valueOf(selectedMission.getBudget()));
                blacklistedCheckBox.setSelected(selectedMission.isBlacklisted());
            }
        });

        missionListView.setCellFactory(listView -> new ListCell<Mission>() {

            /**
             * Funkcje updateItem pozwala na edytowanie pól danej instancji klasy @see Misja
             */
            @Override
            protected void updateItem(Mission item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {

                    setText(item.getMissionName() + " | " + item.getBudget() + "$ | " + item.getPriority() + " | " + item.isBlacklisted());

                    if (Objects.requireNonNull(item).isBlacklisted()) {
                        setTextFill(Color.RED);
                    } else {
                        setTextFill(Color.BLACK);
                    }

                    //setting colours due to priority
                    int priority = item.getPriority();

                    if (priority > 6) {
                        setStyle("-fx-background-color: green;");
                    } else if (priority > 3) {
                        setStyle("-fx-background-color: yellow;");
                    } else {
                        setStyle("-fx-background-color: orange;");
                    }
                }
            }

        });

        for (Mission mission : this.missionArrayList) {
            missionListView.getItems().add(mission);
        }
    }

    /**
     * Funkcja MissionHandler otwiera plik csv z misjami i tworzy na ich podstawie listę obiektów @see Mission
     */
    public MissionHandler() {

        chosenMissionArrayList = new ArrayList<>();
        missionArrayList = new ArrayList<>();
        try (Reader reader = new FileReader("src/main/java/com/example/inzynieria_sprint_n/csv/proposed_mission_list.csv");
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreEmptyLines(true).withDelimiter(';'))) {
            {
                for (CSVRecord record : csvParser.getRecords()) {
                    missionArrayList.add(new Mission(record.get(0), record.get(1), record.get(2), Boolean.parseBoolean(record.get(3))));
                }
                csvParser.close();
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Funkcja addMission() pobiera dane z pól tekstowych aplikacji i na ich podstawie tworzy obiekt @see Mission
     *
     */

    @FXML
    public void addMission() throws IOException {
        String missionName = missionNameTextField.getText();
        String budgetString = budgetTextField.getText();
        boolean isBlacklisted = blacklistedCheckBox.isSelected();

        Mission mission = new Mission(missionName, budgetString, isBlacklisted);
        missionListView.getItems().add(mission);
        missionListView.refresh();

        missionNameTextField.clear();
        budgetTextField.clear();
        blacklistedCheckBox.setSelected(false);

        System.out.println(mission);

        // Save the new mission to the csv file

        String fileMissionList = "src/main/java/com/example/inzynieria_sprint_n/csv/proposed_mission_list.csv";
        FileWriter fileWriter = new FileWriter(fileMissionList, true);
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withDelimiter(';'));

        csvPrinter.print(missionName);
        csvPrinter.print(budgetString);
        csvPrinter.print(mission.getPriority());
        csvPrinter.print(isBlacklisted);
        csvPrinter.println();

        csvPrinter.flush();
        fileWriter.close();

        //checkBudgetExceeded();
    }

    /**
     * Funkcja deleteMission() usuwa zaznaczoną misję z listy misji
     */

    @FXML
    public void deleteMission() {

        Mission selectedMission = this.missionListView.getSelectionModel().getSelectedItem();
        String fileMissionList = "src/main/java/com/example/inzynieria_sprint_n/csv/proposed_mission_list.csv";
        try (Reader reader = new FileReader(fileMissionList);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreEmptyLines(true).withDelimiter(';'))) {
            // Create a new list to store the remaining missions
            List<Mission> remainingMissions = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                String missionName = csvRecord.get(0);
                String budgetString = csvRecord.get(1);
                String priority = csvRecord.get(2);
                boolean isBlacklisted = Boolean.parseBoolean(csvRecord.get(3));
                Mission mission = new Mission(missionName, budgetString, priority, isBlacklisted);
                if (!mission.getMissionName().equals(selectedMission.getMissionName())) {
                    remainingMissions.add(mission);
                }
            }

            // Write the remaining missions to the csv file
            try (FileWriter fileWriter = new FileWriter(fileMissionList);
                 CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withDelimiter(';'))) {
                for (Mission mission : remainingMissions) {
                    csvPrinter.printRecord(mission.getMissionName(), mission.getBudget(), mission.getPriority(), mission.isBlacklisted());
                }
                csvPrinter.close(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        missionListView.getItems().remove(selectedMission);
        missionListView.refresh();
    }

    private void checkBudgetExceeded() {
        int budget = Integer.parseInt(budgetTextField.getText());
        int totalCost = 0;
        for (Mission mission : allMissions) {
            totalCost += mission.getBudget();
        }
        if (totalCost > budget) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Budget exceeded");
            alert.setHeaderText("The total cost of all missions has exceeded the budget");
            alert.setContentText("Please review your budget and missions");
            alert.showAndWait();
        }
    }

    public void setChosenMissions(MouseEvent mouseEvent) throws IOException {
        MissionDistributor missionDistributor = MissionDistributor.getInstance();
        this.chosenMissionArrayList = missionDistributor.chooseMissions(1000);
        missionChosenListViewId.getItems().removeAll();
        for (Mission mission : this.chosenMissionArrayList) {
            missionChosenListViewId.getItems().add(mission);
        }
        missionChosenListViewId.setCellFactory(missionListView1 -> new ListCell<Mission>() {
            @Override
            protected void updateItem(Mission item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getMissionName() + " | " + item.getBudget() + "$ | " + item.getPriority() + " | " + item.isBlacklisted());

                    //setting colours due to priority
                    int priority = item.getPriority();

                    if (priority > 6) {
                        setStyle("-fx-background-color: green;");
                    } else if (priority > 3) {
                        setStyle("-fx-background-color: yellow;");
                    } else {
                        setStyle("-fx-background-color: orange;");
                    }
                }

            }
        });
    }
}
