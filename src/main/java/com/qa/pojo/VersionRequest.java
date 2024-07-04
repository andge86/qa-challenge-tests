package com.qa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VersionRequest {

    private String name;
    private String hugging_face_model;

}
