module org.example.tictactoegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tictactoegame to javafx.fxml;
    exports org.example.tictactoegame;
}