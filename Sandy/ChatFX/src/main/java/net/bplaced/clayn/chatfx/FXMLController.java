package net.bplaced.clayn.chatfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import net.bplaced.clayn.sandy.domain.chat.ChatMessage;

public class FXMLController implements Initializable
{

    @FXML
    private BorderPane root;

    ChatInputBox box = new ChatInputBox();

    @FXML
    private void onAction()
    {
        ChatMessage mes1 = new ChatMessage("Test",
                "Incoming of a longer message than before",
                System.currentTimeMillis(), true);
        ChatMessage mes2 = new ChatMessage("Test",
                "Outgoing of a longer message than before",
                System.currentTimeMillis(), false);
        box.getMessages().addAll(mes1, mes2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        box = new ChatInputBox();
        ChatMessage mes1 = new ChatMessage("Test", "Incoming",
                System.currentTimeMillis(), true);
        ChatMessage mes2 = new ChatMessage("Test", "Outgoing",
                System.currentTimeMillis(), false);
        Platform.runLater(() -> box.getMessages().addAll(mes1, mes2));
        root.setCenter(box);
    }
}
