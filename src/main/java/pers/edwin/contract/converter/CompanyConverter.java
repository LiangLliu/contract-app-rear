package pers.edwin.contract.converter;

import pers.edwin.contract.entity.Company;
import pers.edwin.contract.request.CompanyRequest;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/19 17:58
 */
public class CompanyConverter {
    public static Company convert(CompanyRequest companyRequest) {
        return Company.builder()
                .address(companyRequest.getAddress())
                .companyName(companyRequest.getCompanyName())
                .logo(companyRequest.getLogo())
                .telephone(companyRequest.getTelephone())
                .signatureUrl(companyRequest.getSignatureUrl())
                .build();
    }
}
