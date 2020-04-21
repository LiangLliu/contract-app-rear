package pers.edwin.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.ContractType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Jinzi Yuan
 * Create Data: 2020/4/10 0:08
 * @Description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameListDto {
    private Integer id;

    private String name;

    public static List<NameListDto> fromCompanyList(List<Company> companyList) {

        return companyList.stream()
                .map(it -> NameListDto.builder()
                        .id(it.getId())
                        .name(it.getCompanyName())
                        .build())
                .collect(Collectors.toList());
    }


    public static List<NameListDto> fromContractTypeList(List<ContractType> contractTypeList) {
        return contractTypeList.stream()
                .map(it -> NameListDto.builder()
                        .id(it.getId())
                        .name(it.getType())
                        .build())
                .collect(Collectors.toList());
    }
}
