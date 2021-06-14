package com.studentmanager.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

    public Payload(String message, boolean status){
        this.message = message;
        this.status = status;
    }
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private boolean status;

    //Specific errors in API request processing
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private Map<String, String> details;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<String> detailString;
}
