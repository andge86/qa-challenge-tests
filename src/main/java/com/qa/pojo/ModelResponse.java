package com.qa.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelResponse {

    private String name;
    private String owner;
    @JsonProperty("id")
    private UUID uuid;

}
