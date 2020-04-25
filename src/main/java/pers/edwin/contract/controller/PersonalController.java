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
 * Create Data: 2020/4/22 23:39
 */
@Slf4j
@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Resource
    private ContactService contactService;

    /**
     * 获取话题的评论列表
     *
     * @return 评论列表
     */
    @GetMapping("/{employeeId}/{page}/{size}")
    public ResponseEntity all(@PathVariable Integer employeeId,
                              @PathVariable Integer page,
                              @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryPersonalAllPage(employeeId, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

    /**
     * 我创建的合同
     *
     * @return 评论列表
     */
    @GetMapping("/create/{employeeId}/{page}/{size}")
    public ResponseEntity create(@PathVariable Integer employeeId,
                                 @PathVariable Integer page,
                                 @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryPersonalCreate(employeeId, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }


    /**
     * 我创建的合同，待他签
     *
     * @return 评论列表
     */
    @GetMapping("/create/unsigned/{employeeId}/{page}/{size}")
    public ResponseEntity createByUnsigned(@PathVariable Integer employeeId,
                                 @PathVariable Integer page,
                                 @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryPersonalCreate(employeeId, ContractStatusEnum.UNSIGNED, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

    /**
     * 待签的
     */
    @GetMapping("/unsigned/{employeeId}/{page}/{size}")
    public ResponseEntity unsigned(@PathVariable Integer employeeId,
                                   @PathVariable Integer page,
                                   @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryPersonalCondition(employeeId, ContractStatusEnum.UNSIGNED, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

    /**
     * 已经签署
     */
    @GetMapping("/complete/{employeeId}/{page}/{size}")
    public ResponseEntity complete(@PathVariable Integer employeeId,
                                   @PathVariable Integer page,
                                   @PathVariable Integer size) {

        PageResponse<Contact> contactPageResponse = contactService.queryPersonalCondition(employeeId, ContractStatusEnum.COMPLETE, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

    /**
     * 作废合同
     */
    @GetMapping("/invalid/{employeeId}/{page}/{size}")
    public ResponseEntity invalid(@PathVariable Integer employeeId,
                                  @PathVariable Integer page,
                                  @PathVariable Integer size) {
        PageResponse<Contact> contactPageResponse = contactService.queryPersonalCondition(employeeId, ContractStatusEnum.INVALID, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

}
