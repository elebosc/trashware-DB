package it.unibo.trashware.view.controllers;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.trashware.controller.api.WorkShiftsController;
import it.unibo.trashware.controller.impl.WorkShiftsControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InsertWorkShiftPageController {
    
    @FXML
    private TextField dateField;

    @FXML
    private TextField endTimeField;

    @FXML
    private Button insertBtn;

    @FXML
    private TextField operationIDField1;

    @FXML
    private TextField operationIDField2;

    @FXML
    private TextField operationIDField3;

    @FXML
    private TextField operatorFiscalCodeField;

    @FXML
    private TextField startTimeField;

    @FXML
    private TextArea taskDescriptionField1;

    @FXML
    private TextArea taskDescriptionField2;

    @FXML
    private TextArea taskDescriptionField3;

    private WorkShiftsController controller; 

    @FXML
    public void initialize() {
        try {
            this.controller = new WorkShiftsControllerImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        insertBtn.addEventHandler(ActionEvent.ACTION, e -> {
            insertWorkShift();
            insertTasks();
        });
    }

    private void insertWorkShift() {
        this.controller.registerWorkShift(
            this.operatorFiscalCodeField.getText(), 
            getLocalDateFromString(this.dateField.getText()), 
            Time.valueOf(this.startTimeField.getText() + ":00"),
            Time.valueOf(this.endTimeField.getText() + ":00")
        );
    }

    private void insertTasks() {
        final List<String> tasksDescriptions = new LinkedList<>();
        tasksDescriptions.add(this.taskDescriptionField1.getText());
        tasksDescriptions.add(this.taskDescriptionField2.getText());
        tasksDescriptions.add(this.taskDescriptionField3.getText());
        final List<Optional<String>> operationsIDs = new LinkedList<>();
        operationsIDs.add(
            this.operationIDField1.getText().equals("")
            ? Optional.of(this.operationIDField1.getText())
            : Optional.empty()
        );
        operationsIDs.add(
            this.operationIDField2.getText().equals("")
            ? Optional.of(this.operationIDField2.getText())
            : Optional.empty()
        );
        operationsIDs.add(
            this.operationIDField3.getText().equals("")
            ? Optional.of(this.operationIDField3.getText())
            : Optional.empty()
        );
        for (int i = 0; i < tasksDescriptions.size(); i++) {
            if (tasksDescriptions.get(i).equals("")) {
                // Empty description
                continue;
            }
            this.controller.registerTask(
                this.operatorFiscalCodeField.getText(), 
                getLocalDateFromString(this.dateField.getText()),
                Time.valueOf(this.startTimeField.getText() + ":00"),
                i + 1, 
                tasksDescriptions.get(i),
                operationsIDs.get(i)
            );
        }
    }

    private LocalDate getLocalDateFromString(final String stringDate) {
        final String[] parsedDate = stringDate.split("-");
        final int year = Integer.parseInt(parsedDate[0]);
        final int month = Integer.parseInt(parsedDate[1]);
        final int day = Integer.parseInt(parsedDate[2]);
        return LocalDate.of(year, month, day);
    }

}
