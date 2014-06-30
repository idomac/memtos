package com.quanix.memtos.server.plugin.search.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * created by lihaoquan
 */
public class SearchException extends NestedRuntimeException {

    public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }

}

