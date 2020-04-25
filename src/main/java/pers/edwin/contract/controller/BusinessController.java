package pers.edwin.contract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.edwin.contract.dto.ContractList;
import pers.edwin.contract.entity.Contact;
import pers.edwin.contract.enums.ContractStatusEnum;
import pers.edwin.contract.response.PageResponse;
import pers.edwin.contract.service.ContactService;
import pers.edwin.contract.util.ResultUtil;

import javax.annotation.Resource;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/24 23:58
 */
@Slf4j
@RestController
@RequestMapping("/business")
public class BusinessController {
    @Resource
    private ContactService contactService;

    /**
     * 获取话题的评论列表
     *
     * @return 评论列表
     */
    @GetMapping("/{companyId}/{page}/{size}")
    public ResponseEntity all(@PathVariable Integer companyId,
                              @PathVariable Integer page,
                              @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryBusinessAllPage(companyId, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

    /**
     * 我创建的合同
     *
     * @return 评论列表
     */
    @GetMapping("/create/{companyId}/{page}/{size}")
    public ResponseEntity create(@PathVariable Integer companyId,
                                 @PathVariable Integer page,
                                 @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryBusinessCreate(companyId, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }


    /**
     * 我创建的合同
     *
     * @return 评论列表
     */
    @GetMapping("/create/unsigned/{companyId}/{page}/{size}")
    public ResponseEntity createByUnsigned(@PathVariable Integer companyId,
                                           @PathVariable Integer page,
                                           @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryBusinessCreate(companyId, ContractStatusEnum.UNSIGNED, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }


    /**
     * 待签的,只涉及商业合同
     */
    @GetMapping("/unsigned/{companyId}/{page}/{size}")
    public ResponseEntity unsigned(@PathVariable Integer companyId,
                                   @PathVariable Integer page,
                                   @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryBusinessCondition(companyId, ContractStatusEnum.UNSIGNED, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

    /**
     * 已经签署
     */
    @GetMapping("/complete/{companyId}/{page}/{size}")
    public ResponseEntity complete(@PathVariable Integer companyId,
                                   @PathVariable Integer page,
                                   @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryBusinessCondition(companyId, ContractStatusEnum.COMPLETE, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

    /**
     * 作废合同
     */
    @GetMapping("/invalid/{companyId}/{page}/{size}")
    public ResponseEntity invalid(@PathVariable Integer companyId,
                                  @PathVariable Integer page,
                                  @PathVariable Integer size) {
        PageResponse<Contact> contactPageResponse = contactService.queryBusinessCondition(companyId, ContractStatusEnum.INVALID, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

}
