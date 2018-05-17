import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javax.swing.*;

import java.util.Date;

import static javax.swing.JOptionPane.CANCEL_OPTION;

public class Main extends Application {
    ObservableList<Note> list;
    String answ;
    serviceFrame frame;
    databaseApp db;
    final Date date = new Date();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("NoteMaker by EY");

        //плавающая компановка
        //добавим горизонтальные и вертикальные зазоры
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);

        //Parent rootNode - класс Paren наследник Node
        Scene myScene = new Scene(rootNode, 500, 500);

        primaryStage.setScene(myScene);

        Button button = new Button("Add new note");

        TableView<Note> table = new TableView<>();
        table.setPrefSize(300, 400);
        db = new databaseApp();
        list = db.getNotes();
        TableColumn<Note, String> col1 = new TableColumn<>("All notes");
        TableColumn<Note, String> col2 = new TableColumn<>("Dates of notes");

        col1.setSortable(false);
        col1.setCellValueFactory(new PropertyValueFactory<>("content"));
        col1.setMaxWidth(200);
        col1.setMinWidth(200);

        col2.setSortable(false);
        col2.setCellValueFactory(new PropertyValueFactory<>("dateCol"));
        col2.setMaxWidth(98);
        col2.setMinWidth(98);

        table.setItems(list);
        table.setFixedCellSize(100);

       table.getColumns().addAll(col1, col2);
        button.setOnAction(event -> {
            answ = JOptionPane.showInputDialog("Enter");
            if ((answ  != null))
                if ((!(answ.isEmpty()))) {
                    list.add(new Note(answ, date));
                    table.setItems(list);
                    db.setNote(answ);
                }
        });
        rootNode.setPadding(new Insets(10));
        rootNode.getChildren().addAll(button, table);

        primaryStage.show();
    }
}
