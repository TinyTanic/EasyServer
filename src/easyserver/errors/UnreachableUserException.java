package com.liuksoft.easyserver.errors;

/**
 *
 * @author Luca
 */
public class UnreachableUserException extends EasyServerException {

    /**
     * Creates a new instance of <code>UnreachableUserException</code> without
     * detail message.
     */
    public UnreachableUserException() {
    }

    /**
     * Constructs an instance of <code>UnreachableUserException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnreachableUserException(String msg) {
        super(msg);
    }
}
