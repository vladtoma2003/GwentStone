package classes.myClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Error {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    private boolean err = false;

    private String message = null;

    /**
     * Returns if the command is correct
     *
     * @return
     */
    public boolean getErr() {
        return err;
    }

    /**
     * Sets the error status
     *
     * @param err
     */
    public void setErr(final boolean err) {
        this.err = err;
    }

    /**
     * Returns the message of the error
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the error
     *
     * @param message
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
