package com.studentmanager.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

    private String message;

    private boolean status;

    //Specific errors in API request processing
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<String> details;
}
