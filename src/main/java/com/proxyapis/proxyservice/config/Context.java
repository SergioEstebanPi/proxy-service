package com.proxyapis.proxyservice.config;

import lombok.Data;

@Data
public class Context {
    private static Context INSTANCE = null;
    private long duration;

    private Context() {
    }

    public static Context getInstance() {
        if (INSTANCE == null) {
            synchronized (Context.class) {
                INSTANCE = new Context();
            }
        }
        return INSTANCE;
    }
}
