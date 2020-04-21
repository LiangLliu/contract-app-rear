package pers.edwin.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private String message;

    public static ExceptionResponse of(String message) {
        return ExceptionResponse.builder().message(message).build();
    }

}
