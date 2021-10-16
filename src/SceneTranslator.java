import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class SceneTranslator {
    private TextArea taEngLang = new TextArea();
    private TextArea taViLang = new TextArea();
    private Button btnTranslator = new Button("Translate");
    private Label lbEngLang = new Label("From:");
    private Label lbViLang = new Label("To:");
    private Label lbHeader = new Label("Translate");
    private Scene root;
    private Group container = new Group();
    private Group layout = new Group();
    private String tEngLang;
    private  String tViLang;

    public SceneTranslator(Dictionary dictionary) {
        this.root = new Scene(container, 1200, 800, Color.web("#3DB2FF", 1));
        container.getChildren().add(new Border().getBorder());
        container.getChildren().add(layout);
        setLayout();
        getTextEngLang();
    }

    private void setLayout() {
        layout.getChildren().addAll(taEngLang, taViLang, lbHeader, lbEngLang, lbViLang, btnTranslator);
        layout.setLayoutX(400);
        layout.getStylesheets().add("css/Translator.css");
        setTextArea();
        setLbHeader();
        setBtnTranslator();
    }

    private void setTextArea() {
        taEngLang.setLayoutY(70);
        taViLang.setLayoutY(450);
        taEngLang.setWrapText(true);
        taViLang.setWrapText(true);
        taEngLang.getStyleClass().add("text-area");
        taViLang.getStyleClass().add("text-area");
        taEngLang.setPromptText("English");
        taViLang.setPromptText("Vietnamese");
        taEngLang.setFocusTraversable(false);
        taViLang.setFocusTraversable(false);
    }

    private void setLbHeader() {
        lbHeader.setLayoutX(250);
        lbHeader.getStyleClass().add("header");
        lbEngLang.setLayoutY(30);
        lbEngLang.setFont(Font.font(30));
        lbViLang.setFont(Font.font(30));
        lbViLang.setLayoutY(410);
    }

    private void setBtnTranslator() {
        btnTranslator.getStyleClass().add("button");
        btnTranslator.getStylesheets().add("css/Button.css");
        btnTranslator.setFont(Font.font(20));
        btnTranslator.setLayoutX(270);
        btnTranslator.setLayoutY(380);
    }

    public void getTextEngLang() {
        btnTranslator.setOnMousePressed(event -> {
            if (!taEngLang.getText().isEmpty()) {
               tEngLang = taEngLang.getText();
                try {
                    tViLang= Translator.translate(tEngLang);
                    setTranslateViText(tViLang);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setTranslateViText(String paragraph) {
        taViLang.setText(paragraph);
    }

    public void addMenu(Menu menu) {
        container.getChildren().add(menu.getLayout());
    }

    public Scene getScene() {
        return root;
    }
}
