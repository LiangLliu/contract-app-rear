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

        PageResponse<Contact> contactPageResponse = contactService.queryAllPage(employeeId, page, size);

        PageResponse<ContractList> pageResponse = ContractList.from(contactPageResponse);
        return ResultUtil.success(HttpStatus.OK, pageResponse);
    }

}
