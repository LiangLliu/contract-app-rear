package pers.edwin.contract.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/19 22:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private Integer id;

    @NotNull
    private String employeeName;

    @NotNull
    private String telephone;

    @NotNull
    private String password;

    @NotNull
    private Integer companyId;

    private String signatureUrl;
    /**
     * 0 : 普通员工 1： 业务员
     */
    @NotNull
    private Integer role;

    private String avatarUrl;

}
