package pers.edwin.contract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pers.edwin.contract.converter.ContractConverter;
import pers.edwin.contract.dto.ContractDto;
import pers.edwin.contract.dto.SearchDto;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.Contact;
import pers.edwin.contract.entity.Employee;
import pers.edwin.contract.enums.ContractStatusEnum;
import pers.edwin.contract.enums.TypeEnum;
import pers.edwin.contract.request.CreateContactRequest;
import pers.edwin.contract.request.InvalidContractRequest;
import pers.edwin.contract.request.SignContractRequest;
import pers.edwin.contract.service.CompanyService;
import pers.edwin.contract.service.ContactService;
import org.springframework.web.bind.annotation.*;
import pers.edwin.contract.service.EmployeeService;
import pers.edwin.contract.util.ResultUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

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

    @Resource
    private CompanyService companyService;

    @Resource
    private EmployeeService employeeService;

    /**
     * 毁约
     */
    @PutMapping("/invalid")
    public ResponseEntity invalid(@RequestBody @Valid InvalidContractRequest invalidContractRequest,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【毁约合同】参数不正确，invalidContractRequest = {}", invalidContractRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "毁约信息必须填写完整 ");
        }
        Contact contact = contactService.queryById(invalidContractRequest.getId());
        if (contact == null || !String.valueOf(invalidContractRequest.getBId()).equals(contact.getPartyB()) || !ContractStatusEnum.UNSIGNED.name().equals(contact.getStatus())) {
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "该合同信息不正确，不能操作");
        }
        contact.setStatus(ContractStatusEnum.INVALID.name());
        contactService.update(contact);
        return ResultUtil.success(HttpStatus.OK);
    }


    /**
     * 签署合同
     *
     * @param signContractRequest
     * @param bindingResult
     * @return
     */
    @PutMapping("/sign")
    public ResponseEntity sign(@RequestBody @Valid SignContractRequest signContractRequest,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【签署合同】参数不正确，signContractRequest = {}", signContractRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "签署信息必须填写完整 ");
        }
        Contact contact = contactService.queryById(signContractRequest.getId());
        if (contact == null || !String.valueOf(signContractRequest.getBId()).equals(contact.getPartyB()) || !ContractStatusEnum.UNSIGNED.name().equals(contact.getStatus())) {
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "该合同不正确，不饿能签署");
        }
        contact.setPartyBUrl(signContractRequest.getPartyBUrl());
        contact.setStatus(ContractStatusEnum.COMPLETE.name());
        contact.setPartyBDate(Instant.now());
        contactService.update(contact);
        return ResultUtil.success(HttpStatus.OK);
    }

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
            log.error("【创建合同】参数不正确，createContactRequest = {}", createContactRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "创建信息必须填写完整 ");
        }
        Contact contact = ContractConverter.converter(createContactRequest);
        contactService.insert(contact);
        return ResultUtil.success(HttpStatus.CREATED);
    }


    /**
     * 通过ID查询合同详情
     *
     * @return 单条数据
     */
    @GetMapping("/findById")
    public ResponseEntity queryCompanyList(@RequestParam(value = "id") Integer id) {
        Contact contact = contactService.queryById(id);
        if (contact == null || contact.getTypeId() == null) {
            return ResultUtil.error(HttpStatus.NOT_FOUND, "合同信息不正确");
        }

        int typeId = contact.getTypeId();

        ContractDto contractDto = new ContractDto();
        if (typeId == 1) {
            contractDto = ContractDto.from(contact, TypeEnum.BUSINESS_CONTRACT,
                    companyService.queryById(Integer.valueOf(contact.getPartyA())),
                    companyService.queryById(Integer.valueOf(contact.getPartyB()))
            );
        }
        if (typeId == 2) {
            contractDto = ContractDto.from(contact, TypeEnum.EMPLOYEE_CONTRACT,
                    companyService.queryById(Integer.valueOf(contact.getPartyA())),
                    employeeService.queryById(Integer.valueOf(contact.getPartyB()))
            );
        }

        if (typeId == 3) {
            contractDto = ContractDto.from(contact, TypeEnum.PERSONAL_CONTRACT,
                    employeeService.queryById(Integer.valueOf(contact.getPartyA())),
                    employeeService.queryById(Integer.valueOf(contact.getPartyB()))
            );
        }
        return ResultUtil.success(HttpStatus.OK, contractDto);
    }


}