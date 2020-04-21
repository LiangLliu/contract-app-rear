package pers.edwin.contract.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/20 23:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameRequest {
    @NotNull
    private String name;
}
