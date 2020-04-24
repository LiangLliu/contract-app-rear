package pers.edwin.contract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.edwin.contract.converter.ContractConverter;
import pers.edwin.contract.dto.ContractDto;
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
import pers.edwin.contract.service.EmployeeService;
import pers.edwin.contract.util.ResultUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.Instant;

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
        Integer typeId = contact.getTypeId();
        if (typeId.equals(TypeEnum.EMPLOYEE_CONTRACT.getId())) {
            Employee a = employeeService.queryById(invalidContractRequest.getBId());
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "你没有签署权限");
            }

        }
        if (typeId.equals(TypeEnum.BUSINESS_CONTRACT.getId())) {
            Company a = companyService.queryById(invalidContractRequest.getBId());
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "你没有签署权限");
            }
        }

        if (typeId.equals(TypeEnum.PERSONAL_CONTRACT.getId())) {
            Employee a = employeeService.queryById(invalidContractRequest.getBId());
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "你没有签署权限");
            }
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
        Integer typeId = contact.getTypeId();
        if (typeId.equals(TypeEnum.EMPLOYEE_CONTRACT.getId())) {
            Employee a = employeeService.queryById(signContractRequest.getBId());
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "你没有签署权限");
            }

        }
        if (typeId.equals(TypeEnum.BUSINESS_CONTRACT.getId())) {
            Company a = companyService.queryById(signContractRequest.getBId());
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "你没有签署权限");
            }
        }

        if (typeId.equals(TypeEnum.PERSONAL_CONTRACT.getId())) {
            Employee a = employeeService.queryById(signContractRequest.getBId());
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "你没有签署权限");
            }
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
        Integer typeId = createContactRequest.getTypeId();
        if (typeId.equals(TypeEnum.EMPLOYEE_CONTRACT.getId())) {
            Company a = companyService.queryById(Integer.valueOf(createContactRequest.getPartyA()));
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "合同创建错误，合同方和类型不匹配");
            }

            Employee b = employeeService.queryById(Integer.valueOf(createContactRequest.getPartyB()));
            if (b == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "合同创建错误，合同方和类型不匹配");
            }
        }
        if (typeId.equals(TypeEnum.BUSINESS_CONTRACT.getId())) {
            Company a = companyService.queryById(Integer.valueOf(createContactRequest.getPartyA()));
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "合同创建错误，合同方和类型不匹配");
            }

            Company b = companyService.queryById(Integer.valueOf(createContactRequest.getPartyB()));
            if (b == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "合同创建错误，合同方和类型不匹配");
            }
        }

        if (typeId.equals(TypeEnum.PERSONAL_CONTRACT.getId())) {
            Employee a = employeeService.queryById(Integer.valueOf(createContactRequest.getPartyA()));
            if (a == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "合同创建错误，合同方和类型不匹配");
            }

            Employee b = employeeService.queryById(Integer.valueOf(createContactRequest.getPartyB()));
            if (b == null) {
                return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "合同创建错误，合同方和类型不匹配");
            }
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
        if (typeId == TypeEnum.EMPLOYEE_CONTRACT.getId()) {
            contractDto = ContractDto.from(contact, TypeEnum.EMPLOYEE_CONTRACT,
                    companyService.queryById(Integer.valueOf(contact.getPartyA())),
                    employeeService.queryById(Integer.valueOf(contact.getPartyB()))
            );

        }
        if (typeId == TypeEnum.BUSINESS_CONTRACT.getId()) {
            contractDto = ContractDto.from(contact, TypeEnum.BUSINESS_CONTRACT,
                    companyService.queryById(Integer.valueOf(contact.getPartyA())),
                    companyService.queryById(Integer.valueOf(contact.getPartyB()))
            );
        }

        if (typeId == TypeEnum.PERSONAL_CONTRACT.getId()) {
            contractDto = ContractDto.from(contact, TypeEnum.PERSONAL_CONTRACT,
                    employeeService.queryById(Integer.valueOf(contact.getPartyA())),
                    employeeService.queryById(Integer.valueOf(contact.getPartyB()))
            );
        }
        return ResultUtil.success(HttpStatus.OK, contractDto);
    }

}