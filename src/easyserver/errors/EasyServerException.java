/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liuksoft.easyserver.errors;

/**
 *
 * @author Luca
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
