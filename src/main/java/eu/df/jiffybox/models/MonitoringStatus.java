package eu.df.jiffybox.models;

import com.fasterxml.jackson.annotation.*;

/**
 * A monitoring status consists of a code and a response.
 */
@JsonAutoDetect
public class MonitoringStatus extends PrintableModel {

    /**
     * The id.
     */
    @JsonIgnore
    private Integer id;

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
     * Set the id.
     *
     * @param id     The id.
     * @param status Required by JSON for flattening.
     */
    @JsonAnySetter
    public void setId(String id, MonitoringStatus status) {
        this.id = Integer.parseInt(id);
        this.code = status.getCode();
        this.response = status.getResponse();
    }

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

    /**
     * Get the id.
     *
     * @return The id.
     */
    @JsonAnyGetter
    public Integer getId() {
        return id;
    }
}
