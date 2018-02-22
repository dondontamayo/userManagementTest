package com.project.userManagementTest.exception;

public class FailedRisksProcessingException extends RuntimeException {

    public FailedRisksProcessingException(final String msg) {
        super(msg);
    }

    public FailedRisksProcessingException(final String msg, final Exception e) {
        super(msg, e);
    }

}
