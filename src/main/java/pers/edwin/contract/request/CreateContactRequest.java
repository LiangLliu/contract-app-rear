package pers.edwin.contract.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/21 22:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactRequest {
    @NotNull
    private Integer typeId;

    @NotNull
    private String title;

    @NotNull
    private String content;
    /**
     * 甲方
     */
    @NotNull
    private String partyA;
    /**
     * 甲方签名
     */
    @NotNull
    private String partyAUrl;

    /**
     * 乙方
     */
    @NotNull
    private String partyB;

}
