package net.bplaced.clayn.sandy.domain.chat;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class ChatMessage
{

    private static final class ImmutableChatMessage extends ChatMessage
    {

        public ImmutableChatMessage(String user, String message, long timestamp,
                boolean incoming)
        {
            super(user, message, timestamp, incoming);
        }

        @Override
        public void setMessage(String message)
        {
        }

        @Override
        public void setTimestamp(long timestamp)
        {
        }

        @Override
        public void setUser(String user)
        {
        }

        @Override
        public void setIncoming(boolean incoming)
        {
        }

    }
    private String user;
    private String message;
    private long timestamp;
    private boolean incoming;

    public ChatMessage()
    {
    }

    public ChatMessage(String user, String message, long timestamp,
            boolean incoming)
    {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
        this.incoming = incoming;
    }

    public boolean isIncoming()
    {
        return incoming;
    }

    public void setIncoming(boolean incoming)
    {
        this.incoming = incoming;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public ChatMessage immutable()
    {
        return new ImmutableChatMessage(user, message, timestamp, incoming);
    }

}
