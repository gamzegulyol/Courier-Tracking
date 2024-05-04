package com.example.Courier.Tracking.constant;

public class Path {

    private Path() {
        throw new IllegalStateException("Path cannot be initialize");
    }

    public static final String VERSION = "v1";
    public static final String BASE_PATH = "/" + VERSION;

    public static final String BASE_PATH_COURIER = BASE_PATH + "/courier";
    public static final String BASE_PATH_STORE = BASE_PATH + "/store";
}
