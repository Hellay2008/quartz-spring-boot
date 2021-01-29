package com.dji.quartz.boot.core.bean;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseDTO implements Serializable {
    private static final long serialVersionUID = -4873814384088149839L;
    private final String traceID = UUID.randomUUID().toString();
    private final long timestamp = System.currentTimeMillis();
    private String language = "zh";

    protected BaseDTO() {
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTraceID() {
        return this.traceID;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
