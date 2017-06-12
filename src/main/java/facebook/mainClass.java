package facebook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainClass extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        DataProfileManager DPM = new DataProfileManager();

        primaryStage.setTitle("Facebook Service");
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Facebook Service");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 3, 1);


        TextArea displayArea = new TextArea();
        grid.add(displayArea, 2, 1, 2, 9);
//==========================================================================================
//==============================Refresh=====================================================
        Label refreshButtonLabel = new Label("Refresh database");
        grid.add(refreshButtonLabel, 0, 1);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DPM.refresh();
                displayArea.setText("Data Base refreshed!");
            }
        });
        grid.add(refreshButton, 1, 1);
//==========================================================================================
        int row = 1;

//==============================FindUserByID=================================================
        Label findByIdButtonLabel = new Label("Find user by ID");
        TextField findByIdTextField = new TextField();
        Button findByIdButton = new Button("Find user");
        findByIdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!findByIdTextField.getText().isEmpty()) {
                        if (DPM.isIdExisting(Integer.parseInt(findByIdTextField.getText())))
                            displayArea.setText(DPM.findById(findByIdTextField.getText()).toString());
                        else displayArea.setText("ID is invalid, try again!");
                    } else
                        displayArea.setText("Please type an ID!");
                }catch (java.lang.NumberFormatException e){
                    displayArea.setText("ID should be a number, try again!");
                }
            }
        });



        grid.add(findByIdButtonLabel, 0, ++row);
        grid.add(findByIdButton, 0, ++row);
        grid.add(findByIdTextField, 1, row);

//==========================================================================================
//================================FindMostCommonWords=======================================
        Label findMostCommonWordsButtonLabel = new Label("Find the most common words on facebook");

        Button findMostCommonWordsButton = new Button("Find words");
        findMostCommonWordsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayArea.setText(DPM.findMostCommonWords().toString());

            }
        });

        grid.add(findMostCommonWordsButtonLabel, 0, ++row);
        grid.add(findMostCommonWordsButton, 0, ++row);

//==========================================================================================
//=================================FindPostsIDsByKeyword====================================
        Label findPostIdsByKeywordButtonLabel = new Label("Find posts IDs by keyword");
        TextField findPostIdsByKeywordTextField = new TextField();
        Button findPostIdsByKeywordButton = new Button("Find posts");
        findPostIdsByKeywordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!findPostIdsByKeywordTextField.getText().isEmpty())
                    displayArea.setText(DPM.findPostIdsByKeyword(findPostIdsByKeywordTextField.getText()).toString());
                else
                    displayArea.setText("Please type a keyword!");
            }
        });



        grid.add(findPostIdsByKeywordButtonLabel, 0, ++row);
        grid.add(findPostIdsByKeywordButton, 0, ++row);
        grid.add(findPostIdsByKeywordTextField, 1, row);
//==========================================================================================
//=================================FindAll==================================================
        Label findAllButtonLabel = new Label("Find all profiles");
        grid.add(findAllButtonLabel, 0, ++row);

        Button findAllButton = new Button("Find profiles");
        findAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayArea.setText(DPM.findAll().toString());
            }
        });
        grid.add(findAllButton, 0, ++row);

//==========================================================================================



        Scene scene = new Scene(grid, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
