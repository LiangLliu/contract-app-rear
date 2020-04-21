package pers.edwin.contract.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Employee)实体类
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 321829577069321992L;

    private Integer id;

    private String employeeName;

    private String password;

    private Integer companyId;

    private String telephone;

    private String signatureUrl;
    /**
     * 0 : 普通员工 1： 业务员
     */
    private Integer role;

    private String avatarUrl;
}