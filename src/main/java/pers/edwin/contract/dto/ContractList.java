package pers.edwin.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.edwin.contract.entity.Contact;
import pers.edwin.contract.response.PageResponse;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/23 1:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractList {
    private Integer id;
    private String title;
    private Instant createTime;

    public static PageResponse<ContractList> from(PageResponse<Contact> contactPageResponse) {

        return PageResponse.<ContractList>builder()
                .count(contactPageResponse.getCount())
                .size(contactPageResponse.getSize())
                .page(contactPageResponse.getPage())
                .list(from(contactPageResponse.getList()))
                .build();
    }


    private static List<ContractList> from(List<Contact> contactList) {
        return contactList.stream().map(ContractList::from).collect(Collectors.toList());
    }

    private static ContractList from(Contact contact) {
        return ContractList.builder()
                .id(contact.getId())
                .title(contact.getTitle())
                .createTime(contact.getCreateTime())
                .build();
    }
}
