package org.server;

public class ClientObjectInitException extends Exception {
    public ClientObjectInitException(String clientObjectCanNotInititlalized) {
        super(clientObjectCanNotInititlalized);
    }
}
