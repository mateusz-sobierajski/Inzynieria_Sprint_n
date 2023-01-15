package com.example.inzynieria_sprint_n;

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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewMissionHandler implements Initializable {

    public Button deleteMissionBtnId;
    public Button addMissionBtnId;
    public ListView<Mission> missionChosenListView;
    public Button budgetBtnId;
    @FXML
    private ListView<Mission> missionListView;
    @FXML
    private TextField missionNameTextField, budgetTextField, priorityTextField;
    @FXML
    private CheckBox blacklistedCheckBox;

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

        for (Mission mission : listHandler.missionArrayList) {
            missionListView.getItems().add(mission);
        }
    }

    @FXML
    private MissionListHandler listHandler;


    public NewMissionHandler() throws IOException, URISyntaxException {
        listHandler = new MissionListHandler();
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
    }

    @FXML
    public void deleteMission() {

        Mission selectedMission = missionListView.getSelectionModel().getSelectedItem();
        missionListView.getItems().remove(selectedMission);
        missionListView.refresh();
    }
}
