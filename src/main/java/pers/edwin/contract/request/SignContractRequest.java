package pers.edwin.contract.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/23 23:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignContractRequest {
    @NotNull
    private Integer id;
    /**
     * 乙方
     */
    @NotNull
    private Integer bId;

    /**
     * 乙方签名
     */
    @NotNull
    private String partyBUrl;

}
