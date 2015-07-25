package easyserver.errors;

/**
 *
 * @author Luca Aguzzoli
 */
public class EasyServerException extends Exception {

    /**
     * Creates a new instance of <code>EasyServerException</code> without detail
     * message.
     */
    public EasyServerException() {
    }

    /**
     * Constructs an instance of <code>EasyServerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EasyServerException(String msg) {
        super(msg);
    }
}
