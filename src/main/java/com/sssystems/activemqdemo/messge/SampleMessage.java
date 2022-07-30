package com.sssystems.activemqdemo.messge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.core.SerializableString;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleMessage implements Serializable {
    @JsonProperty("message") String message;
    @JsonProperty("identifier") int identifier;
    @JsonProperty("email") String email;

/*
    public SampleMessage(String message, int identifier, String email) {
        this.message = message;
        this.identifier = identifier;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SampleMessage{" +
                "message='" + message + '\'' +
                ", identifier=" + identifier +
                ", email='" + email + '\'' +
                '}';
    }*/
}
