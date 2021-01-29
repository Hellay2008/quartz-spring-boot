package com.dji.quartz.boot.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum LogStatusEnum {
    NORMAL("0", "正常"),
    ERROR("1", "异常");


    private final String code;
    private final String name;

    private static final Map<String, LogStatusEnum> map = new HashMap<>();

    LogStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    static {
        for(LogStatusEnum e : LogStatusEnum.values()){
            map.put(e.code, e);
        }
    }

    public static LogStatusEnum get(final String code) {
        LogStatusEnum e = map.get(code);
        if (e == null){
            throw new IllegalArgumentException( "No such enum code " + code);
        }
        return e;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
