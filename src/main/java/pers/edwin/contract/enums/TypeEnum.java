package pers.edwin.contract.enums;

import lombok.Getter;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/23 22:22
 */
@Getter
public enum TypeEnum {
    EMPLOYEE_CONTRACT(1, "员工合同"),
    BUSINESS_CONTRACT(2, "企业合同"),
    PERSONAL_CONTRACT(3, "个人合同"),
    ;

    private Integer id;
    private String name;

    TypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
