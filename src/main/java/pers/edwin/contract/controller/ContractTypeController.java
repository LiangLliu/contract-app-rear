package pers.edwin.contract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pers.edwin.contract.converter.CompanyConverter;
import pers.edwin.contract.dto.NameListDto;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.ContractType;
import pers.edwin.contract.request.CompanyRequest;
import pers.edwin.contract.request.NameRequest;
import pers.edwin.contract.service.ContractTypeService;
import org.springframework.web.bind.annotation.*;
import pers.edwin.contract.util.ResultUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * (ContractType)表控制层
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
@RestController
@RequestMapping("/contractType")
@Slf4j
public class ContractTypeController {
    /**
     * 服务对象
     */
    @Resource
    private ContractTypeService contractTypeService;

    /**
     * 合同的所有类型
     *
     * @return 单条数据
     */
    @GetMapping("/list")
    public ResponseEntity queryList() {
        List<ContractType> contractTypeList = contractTypeService.queryAll();
        List<NameListDto> nameListDtoList = NameListDto.fromContractTypeList(contractTypeList);
        return ResultUtil.success(HttpStatus.OK, nameListDtoList);
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid NameRequest nameRequest,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【添加类型】参数不正确，nameRequest = {}", nameRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "类型名称必须填写");
        }
        if (!contractTypeService.check(nameRequest)) {
            log.error("【添加类型】添加类型内容异常，nameRequest = {}", nameRequest);
            return ResultUtil.error(HttpStatus.NOT_FOUND, "该类型已经存在，不要重复添加");
        }
        contractTypeService
                .insert(ContractType
                        .builder()
                        .type(nameRequest.getName())
                        .build());
        return ResultUtil.success(HttpStatus.CREATED);
    }


}