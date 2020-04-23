package pers.edwin.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.Contact;
import pers.edwin.contract.entity.ContractType;
import pers.edwin.contract.entity.Employee;
import pers.edwin.contract.enums.TypeEnum;

import java.time.Instant;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/23 22:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private Integer id;

    private String type;

    private String title;

    private String content;
    /**
     * 甲方
     */
    private Integer aId;

    private String partyA;
    /**
     * 甲方签名
     */
    private String partyAUrl;
    /**
     * 甲方签署日期
     */
    private Instant partyADate;
    /**
     * 乙方
     */
    private Integer bId;

    private String partyB;
    /**
     * 乙方签名
     */
    private String partyBUrl;
    /**
     * 乙方签署日期
     */
    private Instant partyBDate;
    /**
     * 创建时间
     */
    private Instant createTime;
    /**
     * 合同状态
     */
    private String status;

    public static ContractDto from(Contact contact, TypeEnum type, Company a, Company b) {
        return from(contact, type.getName(), a.getId(), a.getCompanyName(), b.getId(), b.getCompanyName());
    }

    public static ContractDto from(Contact contact, TypeEnum type, Company company, Employee employee) {
        return from(contact, type.getName(), company.getId(), company.getCompanyName(), employee.getId(), employee.getEmployeeName());
    }

    public static ContractDto from(Contact contact, TypeEnum type, Employee a, Employee b) {
        return from(contact, type.getName(), a.getId(), a.getEmployeeName(), b.getId(), b.getEmployeeName());
    }


    private static ContractDto from(Contact contact, String type, Integer aId, String partyA, Integer bId, String partyB) {
        ContractDto contractDto = ContractDto.builder().build();
        BeanUtils.copyProperties(contact, contractDto);
        contractDto.setType(type);
        contractDto.setAId(aId);
        contractDto.setBId(bId);
        contractDto.setPartyA(partyA);
        contractDto.setPartyB(partyB);
        return contractDto;
    }

}
