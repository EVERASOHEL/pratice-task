package com.demosecurity.utils;

public enum Permissions {
    WRITE("canWrite"),
    READ("canRead"),
    UPDATE("canUpdate"),
    DELETE("canDelete");

    private final String value;

    Permissions(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
