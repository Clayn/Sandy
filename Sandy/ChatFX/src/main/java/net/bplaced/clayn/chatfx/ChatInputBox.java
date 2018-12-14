package net.bplaced.clayn.chatfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import net.bplaced.clayn.chatfx.skin.ChatInputBoxSkin;
import net.bplaced.clayn.sandy.domain.chat.ChatMessage;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class ChatInputBox extends Control
{

    private final ObservableList<ChatMessage> messages = FXCollections.observableArrayList();

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new ChatInputBoxSkin(this);
    }

    public ObservableList<ChatMessage> getMessages()
    {
        return messages;
    }

}
