package com.sssystems.activemqdemo.messge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleMessage {
    @JsonProperty("message") String message;
    @JsonProperty("identifier") int identifier;


    public SampleMessage(String message, int identifier) {
        this.message = message;
        this.identifier = identifier;
    }
}
