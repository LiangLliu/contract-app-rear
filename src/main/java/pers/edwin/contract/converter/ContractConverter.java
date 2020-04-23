package pers.edwin.contract.converter;

import pers.edwin.contract.entity.Contact;
import pers.edwin.contract.enums.ContractStatusEnum;
import pers.edwin.contract.request.CreateContactRequest;

import java.time.Instant;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/21 22:47
 */
public class ContractConverter {

    public static Contact converter(CreateContactRequest createContactRequest) {
        Instant now = Instant.now();

        return Contact.builder()
                .typeId(createContactRequest.getTypeId())
                .content(createContactRequest.getContent())
                .title(createContactRequest.getTitle())
                .partyA(createContactRequest.getPartyA())
                .partyAUrl(createContactRequest.getPartyAUrl())
                .partyB(createContactRequest.getPartyB())
                .status(ContractStatusEnum.UNSIGNED.name())
                .partyADate(now)
                .createTime(now)
                .build();
    }
}
