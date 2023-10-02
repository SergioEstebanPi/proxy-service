package com.proxyapis.proxyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@RedisHash("Request")
public class RequestEntity implements Serializable {
    private String id;
    private String ip;
    private String path;
    private String method;
    private int status;
    private long duration;
    private Date date;

    public RequestEntity(String ip, String path, String method, int status, long duration, Date date) {
        this.ip = ip;
        this.path = path;
        this.method = method;
        this.status = status;
        this.duration = duration;
        this.date = date;
    }
}
