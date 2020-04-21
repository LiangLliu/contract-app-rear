package pers.edwin.contract.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/19 17:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private Integer id;

    @NotNull
    private String companyName;

    @NotNull
    private String address;

    @NotNull
    private String telephone;

    @NotNull
    private String signatureUrl;

    @NotNull
    private String logo;
}