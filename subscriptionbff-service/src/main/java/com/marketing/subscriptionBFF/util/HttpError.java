package com.marketing.subscriptionBFF.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpError {

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("path")
    private String path;

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

}
