package org.example.tictactoegame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private  Label playerXScoreLabel, playerOScoreLabel;
    private int playerXScore = 0, playerOScore = 0;
    private boolean playerXTurn = true;

    // make it gaming board ->
    private Button buttons[][] = new Button[3][3];
    private BorderPane createContent(){
        BorderPane root = new BorderPane();


        root.setPadding(new Insets(20));  // make the space for complete side
        // I will divide my board in three part
        // Title
        Label titleLabel = new Label("TIC TAC TOE");  /// label means non-editable area
        titleLabel.setStyle("-fx-font-size : 35pt;  -fx-font-weight : bold;");   // css thing - change my formating

        root.setTop(titleLabel);  // add title in board. add with root object
        BorderPane.setAlignment(titleLabel, Pos.CENTER);  // keep the title on center

        // Game Board
        GridPane gridPane = new GridPane();
        // take the gap between the button
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Add padding around the GridPane
        gridPane.setPadding(new Insets(10));

        // Set the background color of the GridPane
        gridPane.setStyle("-fx-background-color: #fac0be;");
        // add total 9 button
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
//                Button button = new Button("_");
                Button button = new Button();  /// when click the button then - X and O me se koi ek value insert hoga turn next player ke pass hoga
                button.setPrefSize(100, 100);
                // button.setStyle("-fx-background-radius: 10 10 10 10; -fx-min-width: 100px; -fx-min-height: 100px; -fx-max-width: 100px; -fx-max-height: 100px; -fx-font-size : 24pt;  -fx-font-weight : bold; -fx-background-color: #e3fae9;");
                button.setStyle(" -fx-font-size : 24pt;  -fx-font-weight : bold; -fx-background-color: #e3fae9;");
                // call the button action
                button.setOnAction(event -> buttonClicked(button));
                buttons[i][j] = button;
                // add the currentButton in gridPane
//                gridPane.add(button, i, j);  // here need to flip the location
                gridPane.add(button, j, i);
            }
        }
        // add gridButton in borderPane in center
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);  // keep the title on center


        // Score Board
        HBox scoreBoard = new HBox(20) ;/// HBox - it is called column. hbox layout pane arrange the nodes in single row
        scoreBoard.setAlignment(Pos.CENTER);  // keep in center
        playerXScoreLabel = new Label("Player X : 0");   // player with x value
        playerXScoreLabel.setStyle("-fx-font-size : 16pt;  -fx-font-weight : bold;"); // styling with label
        playerOScoreLabel = new Label("Player O : 0");   // player with O value
        playerOScoreLabel.setStyle("-fx-font-size : 16pt;  -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel, playerOScoreLabel);  /// add all in HBOX childrean area
        root.setBottom(scoreBoard);  // then finally keep the bottom area


        return root;
    }


    // button event
    private void buttonClicked(Button button){
        // whenever particular button will click then add the parmater

        // when click any button -> for this button already filled - means someone has already filled the tile need to check
        if(button.getText().equals("")){
            // when tile area will blank then player will keep the X or O value. otherwise not
            if(playerXTurn){
                // when player X turn then keep the X in button text
                button.setText("X");
            }else{
                button.setText("O");
            }
            // change the turn value
            playerXTurn = !playerXTurn;  // inverting the turn value => when true then return false, when false then return true

            // check winner
            checkWinner();
        }
        return;
    }

    private void checkWinner(){  /// this method will call by button clicked -> when button will click then take the decision
        // here three condition for winner
        // Row -> when row all value will same => here I will check the text value
        for(int row=0; row<3; row++){
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                        !buttons[row][0].getText().isEmpty()){  /// checking for each column value is equal or not. also check box shouldn't be blank
                // we will have winner
//                System.out.println("Check : Winner");
                // changing the color - when able to win
                buttons[row][0].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
                buttons[row][1].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
                buttons[row][2].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
                String winnerName = buttons[row][0].getText();
                showWinnerDialog(winnerName);
                updateScore(winnerName);
                resetBoard();
                return;  /// if according to row winner has decided no need to rest of code
            }
        }

        // Column -> when column all value will same
        for(int column=0; column<3; column++){
            if(buttons[0][column].getText().equals(buttons[1][column].getText()) &&
                    buttons[1][column].getText().equals(buttons[2][column].getText()) &&
                        !buttons[0][column].getText().isEmpty()){

                buttons[0][column].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
                buttons[1][column].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
                buttons[2][column].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");

                String winnerName = buttons[0][column].getText();
                showWinnerDialog(winnerName);
                updateScore(winnerName);
                resetBoard();
                return;
            }
        }

        // Diagonal -> when first and second diagonal will be same
        // keep it manually - for first column
        if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().isEmpty()){

            buttons[0][0].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
            buttons[1][1].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
            buttons[2][2].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");

            String winnerName = buttons[0][0].getText();
            showWinnerDialog(winnerName);
            updateScore(winnerName);
            resetBoard();
            return;
        }

        // check for second column
        if(buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().isEmpty()){

            buttons[0][2].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
            buttons[1][1].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");
            buttons[2][0].setStyle("-fx-background-color: #21fc5b; -fx-font-size : 24pt;  -fx-font-weight : bold;");

            String winnerName = buttons[0][2].getText();
            showWinnerDialog(winnerName);
            updateScore(winnerName);
            resetBoard();
            return;
        }


        /// Tie - situation -> all button has been filled no any remain . need to reset the game for next play
        boolean tie = true;
        for(Button row[] : buttons){
            for(Button button : row){
                // abstract the each button -> then set blank
                // check any of button is empty -> means move is still possible. can't said tie
                if(button.getText().isEmpty()){
                    tie = false;
                    break;
                }
            }
        }
        if(tie){
            // when tie is happen then annouce the board
            showTieDialog();
            resetBoard();
        }

    }

    /// show winner dialog popup
    private void showWinnerDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner...");
        alert.setContentText("Congratulations Buddy !  " + winner + "  ! You won the game !");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    // when game will tie
    private void showTieDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie....");
        alert.setContentText("Game Over ! It's a tie.");
        alert.setHeaderText("");
        alert.showAndWait();
    }


    /// Update the score value after winner will announce
    private void updateScore(String winner){  /// call inside winner method -> inside here I am getting winner name
        if(winner.equals("X")){
            // when X player will win
            playerXScore ++;
            playerXScoreLabel.setText("Player X : " + playerXScore);  // update the value
        }else {
            playerOScore ++;
            playerOScoreLabel.setText("Player O : " + playerOScore); // update the score value
        }
    }

    // after updateding score need to reset the board
    private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                // abstract the each button -> then set blank
                button.setText("");  // keep it as blank
                button.setStyle("-fx-background-color: #e3fae9; -fx-font-size : 24pt;  -fx-font-weight : bold;");
            }
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}