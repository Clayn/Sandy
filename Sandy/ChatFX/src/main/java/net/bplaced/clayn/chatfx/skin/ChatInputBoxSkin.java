package net.bplaced.clayn.chatfx.skin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.bplaced.clayn.chatfx.ChatInputBox;
import net.bplaced.clayn.sandy.domain.chat.ChatMessage;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class ChatInputBoxSkin extends SkinBase<ChatInputBox>
{

    private final DateFormat dateFormat = new SimpleDateFormat("HH:MM");
    public static final String INCOMING_BOX_STYLE = "chat-in";
    public static final String OUTGOING_BOX_STYLE = "chat-out";
    private final ListView<ChatMessage> messageView;

    public ChatInputBoxSkin(ChatInputBox control)
    {
        super(control);
        System.out.println("Skin created");
        messageView = new ListView<>();
        messageView.setItems(control.getMessages());
        messageView.setCellFactory(this::createListViewCell);
        getChildren().add(messageView);
    }

    private ListCell<ChatMessage> createListViewCell(
            ListView<ChatMessage> param)
    {
        return new ListCell<ChatMessage>()
        {
            @Override
            protected void updateItem(ChatMessage item, boolean empty)
            {
                super.updateItem(item, empty);
                if (item == null || empty)
                {
                    setGraphic(null);
                    return;
                }
                VBox box = new VBox();
                box.getStyleClass().add(
                        item.isIncoming() ? INCOMING_BOX_STYLE : OUTGOING_BOX_STYLE);
                Label message = new Label(item.getMessage());
                Label user = new Label(item.getUser());
                Label time = new Label(dateFormat.format(new Date(
                        item.getTimestamp())));
                box.getStyleClass().add("box");
                box.setMaxWidth(Double.NEGATIVE_INFINITY);
                box.getChildren().addAll(message, user, time);
                box.setPadding(new Insets(5));
                StackPane pane = new StackPane(box);
                pane.setAlignment(
                        item.isIncoming() ? Pos.CENTER_LEFT : Pos.CENTER_RIGHT);
                setGraphic(pane);
            }

        };
    }

}
