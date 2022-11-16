package classes.myClasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Error {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    private boolean err = false;

    private String message = null;

    public boolean getErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
