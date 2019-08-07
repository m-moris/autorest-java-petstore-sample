/**
 * Describe license header.
 *
 */

package autorest.petstore.models;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines headers for loginUser operation.
 */
public class LoginUserHeaders {
    /**
     * calls per hour allowed by the user.
     */
    @JsonProperty(value = "X-Rate-Limit")
    private Integer xRateLimit;

    /**
     * date in UTC when token expires.
     */
    @JsonProperty(value = "X-Expires-After")
    private DateTime xExpiresAfter;

    /**
     * Get calls per hour allowed by the user.
     *
     * @return the xRateLimit value
     */
    public Integer xRateLimit() {
        return this.xRateLimit;
    }

    /**
     * Set calls per hour allowed by the user.
     *
     * @param xRateLimit the xRateLimit value to set
     * @return the LoginUserHeaders object itself.
     */
    public LoginUserHeaders withXRateLimit(Integer xRateLimit) {
        this.xRateLimit = xRateLimit;
        return this;
    }

    /**
     * Get date in UTC when token expires.
     *
     * @return the xExpiresAfter value
     */
    public DateTime xExpiresAfter() {
        return this.xExpiresAfter;
    }

    /**
     * Set date in UTC when token expires.
     *
     * @param xExpiresAfter the xExpiresAfter value to set
     * @return the LoginUserHeaders object itself.
     */
    public LoginUserHeaders withXExpiresAfter(DateTime xExpiresAfter) {
        this.xExpiresAfter = xExpiresAfter;
        return this;
    }

}
