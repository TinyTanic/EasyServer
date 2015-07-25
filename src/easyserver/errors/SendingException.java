package easyserver.errors;

/**
 *
 * @author Luca Aguzzoli
 */
public class SendingException extends EasyServerException {

    /**
     * Creates a new instance of <code>SendingException</code> without detail
     * message.
     */
    public SendingException() {
    }

    /**
     * Constructs an instance of <code>SendingException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SendingException(String msg) {
        super(msg);
    }
}
