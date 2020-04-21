package pers.edwin.contract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pers.edwin.contract.converter.CompanyConverter;
import pers.edwin.contract.dto.NameListDto;
import pers.edwin.contract.dto.SearchDto;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.request.CompanyRequest;
import pers.edwin.contract.service.CompanyService;
import org.springframework.web.bind.annotation.*;
import pers.edwin.contract.util.ResultUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * (Company)表控制层
 *
 * @author makejava
 * @since 2020-04-19 17:27:13
 */
@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {
    /**
     * 服务对象
     */
    @Resource
    private CompanyService companyService;

    /**
     * 注册公司
     *
     * @param companyRequest ：注册公司的信息
     * @param bindingResult  ： 要到参数错误
     * @return 响应
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CompanyRequest companyRequest,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【注册公司】参数不正确，topicRequest = {}", companyRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "注册信息必须填写完整 ");
        }

        if (!companyService.check(companyRequest)) {
            log.error("【注册公司】注册公司内容异常，companyRequest = {}", companyRequest);
            return ResultUtil.error(HttpStatus.NOT_FOUND, "该公司已经存在，不要重复注册");
        }
        companyService.insert(CompanyConverter.convert(companyRequest));
        return ResultUtil.success(HttpStatus.CREATED);
    }

    /**
     * 获取公司列表
     *
     * @return 单条数据
     */
    @GetMapping("/list")
    public ResponseEntity queryCompanyList() {
        List<Company> companyList = companyService.queryAll();
        return ResultUtil.success(HttpStatus.OK, NameListDto.fromCompanyList(companyList));
    }

    /**
     * 注册公司
     *
     * @param companyRequest ：注册公司的信息
     * @param bindingResult  ： 要到参数错误
     * @return 响应
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid CompanyRequest companyRequest,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors() || companyRequest == null) {
            log.error("【更新公司】参数不正确，topicRequest = {}", companyRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "注册信息必须填写完整 ");
        }

        if (companyService.queryById(companyRequest.getId()) == null) {
            log.error("【更新公司】更新公司内容异常，companyRequest = {}", companyRequest);
            return ResultUtil.error(HttpStatus.NOT_FOUND, "ID不正确，没有查到该公司");
        }
        companyService.update(Company.builder()
                .id(companyRequest.getId())
                .address(companyRequest.getAddress())
                .companyName(companyRequest.getCompanyName())
                .logo(companyRequest.getLogo())
                .telephone(companyRequest.getTelephone())
                .signatureUrl(companyRequest.getSignatureUrl())
                .build());
        return ResultUtil.success(HttpStatus.CREATED);
    }

    /**
     * 获取公司列表
     *
     * @return 单条数据
     */
    @GetMapping("/findByName")
    public ResponseEntity queryCompanyList(@RequestParam(value = "name") String name) {
        List<Company> companyList = companyService.findByName(name);
        return ResultUtil.success(HttpStatus.OK, SearchDto.fromCompany(companyList));
    }

    /**
     * 获取公司公章
     *
     * @return 单条数据
     */
    @GetMapping("/findSignatureUrl")
    public ResponseEntity findUrl(@RequestParam(value = "id") Integer id) {
        String url =  companyService.findUrl(id);
        return ResultUtil.success(HttpStatus.OK, url);
    }

}