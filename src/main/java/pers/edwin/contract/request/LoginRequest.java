package pers.edwin.contract.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/19 22:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    private String telephone;

    @NotNull
    private String password;
}
