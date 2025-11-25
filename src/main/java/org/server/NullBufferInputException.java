package org.server;

public class NullBufferInputException extends Exception {
    public NullBufferInputException(String nullBufferValueReadin) {
        super(nullBufferValueReadin);
    }
}
