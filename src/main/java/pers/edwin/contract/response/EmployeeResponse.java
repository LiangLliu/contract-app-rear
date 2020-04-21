package pers.edwin.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.edwin.contract.dto.NameListDto;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.Employee;


import java.util.List;

/**
 * @Author Jinzi Yuan
 * Create Data: 2020/4/10 23:06
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private Integer employeeId;

    private String employeeName;

    private String employeeTelephone;

    private String employeeSignatureUrl;

    private String employeeAvatarUrl;
    /**
     * 0 : 普通员工 1： 业务员
     */
    private Integer employeeRole;

    private Integer companyId;

    private String companyName;

    private String companyAddress;

    private String companyTelephone;

    private String companyLogo;

    public static EmployeeResponse from(Employee employee, Company company) {

        return EmployeeResponse.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getEmployeeName())
                .employeeRole(employee.getRole())
                .employeeTelephone(employee.getTelephone())
                .employeeSignatureUrl(employee.getSignatureUrl())
                .employeeAvatarUrl(employee.getAvatarUrl())
                .companyAddress(company.getAddress())
                .companyId(company.getId())
                .companyName(company.getCompanyName())
                .companyLogo(company.getLogo())
                .companyTelephone(company.getTelephone())
                .build();
    }
}
