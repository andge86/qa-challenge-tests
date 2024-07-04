package com.qa.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionResponse {

    private String name;
    private String hugging_face_model;
    private String parent_model_id;
    @JsonProperty("id")
    private UUID uuid;

}
