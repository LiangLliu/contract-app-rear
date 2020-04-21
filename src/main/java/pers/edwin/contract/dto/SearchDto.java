package pers.edwin.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/21 23:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    private Integer id;
    private String name;
    private String telephone;

    public static List<SearchDto> fromCompany(List<Company> companyList) {

        return companyList.stream()
                .map(it -> SearchDto.builder()
                        .id(it.getId())
                        .name(it.getCompanyName())
                        .telephone(it.getTelephone())
                        .build())
                .collect(Collectors.toList());
    }


    public static List<SearchDto> fromEmployee(List<Employee> employeeList) {
        return employeeList.stream()
                .map(it -> SearchDto.builder()
                        .id(it.getId())
                        .name(it.getEmployeeName())
                        .telephone(it.getTelephone())
                        .build())
                .collect(Collectors.toList());
    }
}
