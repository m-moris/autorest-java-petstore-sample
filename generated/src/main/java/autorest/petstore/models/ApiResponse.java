/**
 * Describe license header.
 *
 */

package autorest.petstore.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ApiResponse model.
 */
public class ApiResponse {
    /**
     * The code property.
     */
    @JsonProperty(value = "code")
    private Integer code;

    /**
     * The type property.
     */
    @JsonProperty(value = "type")
    private String type;

    /**
     * The message property.
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Get the code value.
     *
     * @return the code value
     */
    public Integer code() {
        return this.code;
    }

    /**
     * Set the code value.
     *
     * @param code the code value to set
     * @return the ApiResponse object itself.
     */
    public ApiResponse withCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * Get the type value.
     *
     * @return the type value
     */
    public String type() {
        return this.type;
    }

    /**
     * Set the type value.
     *
     * @param type the type value to set
     * @return the ApiResponse object itself.
     */
    public ApiResponse withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the message value.
     *
     * @return the message value
     */
    public String message() {
        return this.message;
    }

    /**
     * Set the message value.
     *
     * @param message the message value to set
     * @return the ApiResponse object itself.
     */
    public ApiResponse withMessage(String message) {
        this.message = message;
        return this;
    }

}
