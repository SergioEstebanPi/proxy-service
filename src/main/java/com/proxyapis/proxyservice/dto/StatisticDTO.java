package com.proxyapis.proxyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    private long success;
    private long error;
    private long rateLimit;
    private long duration;
    private long total;
    private Date date;
}
