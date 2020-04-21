package pers.edwin.contract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pers.edwin.contract.converter.ContractConverter;
import pers.edwin.contract.entity.Contact;
import pers.edwin.contract.request.CreateContactRequest;
import pers.edwin.contract.service.ContactService;
import org.springframework.web.bind.annotation.*;
import pers.edwin.contract.util.ResultUtil;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * (Contact)表控制层
 *
 * @author makejava
 * @since 2020-04-19 17:27:14
 */
@RestController
@RequestMapping("/contract")
@Slf4j
public class ContactController {
    /**
     * 服务对象
     */
    @Resource
    private ContactService contactService;

    /**
     * 创建合同
     *
     * @param createContactRequest：合同
     * @param bindingResult           ：异常
     * @return 创建结果
     */
    @PostMapping("/create")
    public ResponseEntity register(@RequestBody @Valid CreateContactRequest createContactRequest,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建合同】参数不正确，topicRequest = {}", createContactRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "创建信息必须填写完整 ");
        }
        Contact contact = ContractConverter.converter(createContactRequest);
        contactService.insert(contact);
        return ResultUtil.success(HttpStatus.CREATED);
    }


}