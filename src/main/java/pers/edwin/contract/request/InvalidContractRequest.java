package pers.edwin.contract.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/23 23:57
 */
@Data
public class InvalidContractRequest {
    @NotNull
    private Integer id;
    /**
     * 乙方
     */
    @NotNull
    private Integer bId;
}
