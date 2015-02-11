package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A message is sometimes returned by responses. It consists of a type and a
 * message text.
 *
 * @see eu.df.jiffybox.models.MessageType
 * @see eu.df.jiffybox.models.Response
 */
@JsonAutoDetect
public class Message extends PrintableModel {

    /**
     * The type.
     */
    @JsonProperty("type")
    private MessageType type;

    /**
     * The message.
     */
    @JsonProperty("message")
    private String messageText;

    /**
     * Get the type.
     *
     * @return The type.
     */
    @JsonGetter("type")
    public MessageType getType() {
        return type;
    }

    /**
     * Set the type.
     *
     * @param type The type.
     */
    @JsonSetter("type")
    public void setType(MessageType type) {
        this.type = type;
    }

    /**
     * Get the message text.
     *
     * @return The message text.
     */
    @JsonGetter("message")
    public String getMessageText() {
        return messageText;
    }

    /**
     * Set the message text.
     *
     * @param messageText The message text.
     */
    @JsonSetter("message")
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
