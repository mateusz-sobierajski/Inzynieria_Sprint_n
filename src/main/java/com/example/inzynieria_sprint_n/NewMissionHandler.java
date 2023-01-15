package com.example.inzynieria_sprint_n;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewMissionHandler implements Initializable {

    protected final List<Mission> missionArrayList;
    public Button deleteMissionBtnId;
    public Button addMissionBtnId;
    public ListView<Mission> missionChosenListView;
    public Button budgetBtnId;
    public Button editMissionBtnId;
    @FXML
    private ListView<Mission> missionListView;
    @FXML
    private TextField missionNameTextField, budgetTextField, priorityTextField;
    @FXML
    private CheckBox blacklistedCheckBox;
    private boolean isEditMode = false;
    private Mission selectedMission;
    private ObservableList<Mission> allMissions;

    @FXML
    public void handleEditBtn(ActionEvent event) {
        if (!isEditMode) {
            editMissionBtnId.setText("Tryb edycji");
            selectedMission = missionListView.getSelectionModel().getSelectedItem();
            if (selectedMission != null) {
                missionNameTextField.setText(selectedMission.getMissionName());
                budgetTextField.setText(selectedMission.getBudgetString());
                blacklistedCheckBox.setSelected(selectedMission.isBlacklisted());
                missionNameTextField.setEditable(true);
                budgetTextField.setEditable(true);
                blacklistedCheckBox.setDisable(false);
                isEditMode = true;
            }
        } else {
            editMissionBtnId.setText("Zapisz edycje");
            selectedMission.setMissionName(missionNameTextField.getText());
            selectedMission.setBudgetString(budgetTextField.getText());
            selectedMission.setBlacklisted(blacklistedCheckBox.isSelected());
            missionListView.refresh();
            missionNameTextField.clear();
            budgetTextField.clear();
            blacklistedCheckBox.setSelected(false);
            missionNameTextField.setEditable(false);
            budgetTextField.setEditable(false);
            isEditMode = false;
            editMissionBtnId.setText("Tryb edycji");
        }
        //checkBudgetExceeded();
    }

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
                budgetTextField.setText(selectedMission.getBudgetString());
                blacklistedCheckBox.setSelected(selectedMission.isBlacklisted());
            }
        });


        missionListView.setCellFactory(listView -> new ListCell<Mission>() {
            @Override
            protected void updateItem(Mission item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    //TODO ZABAWA FORMATAMI ZEBY LADNIE WYGLADALO
                    setText(item.getMissionName() + " | " + item.getBudgetString() + "$ | " + item.getPriority() + " | " + item.isBlacklisted());

                    if (Objects.requireNonNull(item).isBlacklisted()) {
                        setTextFill(Color.RED);
                    }

                    //setting colours due to priority
                    int priority = -1;
                    if (!item.getPriority().equals("UNP"))
                        priority = Integer.parseInt(item.getPriority());

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

    @FXML
    private Button budgetFilterBtn;

    @FXML
    private Button clearFilterBtn;

    @FXML
    public void budgetFilterPressed() {
        FilteredList<Mission> filteredData = new FilteredList<>(allMissions, p -> true);
        filteredData.setPredicate(mission -> Integer.parseInt(mission.getBudgetString()) > 100000);
        missionListView.setItems(filteredData);
    }

    public void removeBudgetFilter() {
        missionListView.setItems(allMissions);
    }


    public NewMissionHandler() throws IOException, URISyntaxException {
        URL fileUrl = getClass().getResource("/csv/proposed_mission_list.csv");
        File fileMissionList = Paths.get(Objects.requireNonNull(fileUrl).toURI()).toFile();

        if (!fileMissionList.exists()) {
            if (fileMissionList.createNewFile()) {
                System.out.println("Plik stworzony prawidłowo");
            }
        } else {
            System.out.println("Plik już istnieje!");
        }
        if (!fileMissionList.canRead()) {
            throw new IOException("Brak dostępu do pliku z listą misji.");
        }
        missionArrayList = new ArrayList<>();
        String line = "";
        String splitBy = ";";
        BufferedReader br = new BufferedReader(new FileReader(fileMissionList));
        while ((line = br.readLine()) != null) {
            String[] missionDetails = line.split(splitBy);
            missionArrayList.add(new Mission(missionDetails[0], missionDetails[1], missionDetails[2], Boolean.parseBoolean(missionDetails[3])));
        }
    }

    @FXML
    public void addMission() {
        String missionName = missionNameTextField.getText();
        String budgetString = budgetTextField.getText();
        String priority = "UNP";
        boolean isBlacklisted = blacklistedCheckBox.isSelected();
        Mission mission = new Mission(missionName, budgetString, priority, isBlacklisted);
        missionListView.getItems().add(mission);
        missionListView.refresh();

        missionNameTextField.clear();
        budgetTextField.clear();
        blacklistedCheckBox.setSelected(false);

        //checkBudgetExceeded();
    }

    @FXML
    public void deleteMission() {

        Mission selectedMission = missionListView.getSelectionModel().getSelectedItem();
        missionListView.getItems().remove(selectedMission);
        missionListView.refresh();
    }

    private void checkBudgetExceeded() {
        int budget = Integer.parseInt(budgetTextField.getText());
        int totalCost = 0;
        for (Mission mission : allMissions) {
            totalCost += Integer.parseInt(mission.getBudgetString());
        }
        if (totalCost > budget) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Budget exceeded");
            alert.setHeaderText("The total cost of all missions has exceeded the budget");
            alert.setContentText("Please review your budget and missions");
            alert.showAndWait();
        }
    }
}
