package io.github.jnodorp.jiffybox.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A monitoring status consists of a code and a response.
 */
@JsonAutoDetect
public class MonitoringStatus {

    /**
     * The code.
     */
    @JsonProperty("code")
    private int code;

    /**
     * The response.
     */
    @JsonProperty("response")
    private String response;

    /**
     * Get the code.
     *
     * @return The code.
     */
    @JsonGetter("code")
    public int getCode() {
        return code;
    }

    /**
     * Set the code.
     *
     * @param code The code.
     */
    @JsonSetter("code")
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Get the response.
     *
     * @return The response.
     */
    @JsonGetter("response")
    public String getResponse() {
        return response;
    }

    /**
     * Set the response.
     *
     * @param response The response.
     */
    @JsonSetter("response")
    public void setResponse(String response) {
        this.response = response;
    }
}
