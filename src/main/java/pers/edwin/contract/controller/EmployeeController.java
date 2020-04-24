package pers.edwin.contract.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.edwin.contract.dto.SearchDto;
import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.Employee;
import pers.edwin.contract.request.EmployeeRequest;
import pers.edwin.contract.request.LoginRequest;
import pers.edwin.contract.response.EmployeeResponse;
import pers.edwin.contract.service.CompanyService;
import pers.edwin.contract.service.EmployeeService;
import pers.edwin.contract.util.ResultUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    @Resource
    private CompanyService companyService;


    /**
     * 注册公司
     *
     * @param employeeRequest ：注册公司的信息
     * @param bindingResult   ： 要到参数错误
     * @return 响应
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid EmployeeRequest employeeRequest,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【注册员工】参数不正确，topicRequest = {}", employeeRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "注册信息必须填写完整 ");
        }

        if (!employeeService.check(employeeRequest)) {
            log.error("【注册员工】注册员工内容异常，companyRequest = {}", employeeRequest);
            return ResultUtil.error(HttpStatus.NOT_FOUND, "该员工已经存在，不要重复注册");
        }
        employeeService.insert(
                Employee.builder()
                        .employeeName(employeeRequest.getEmployeeName())
                        .password(employeeRequest.getPassword())
                        .role(employeeRequest.getRole())
                        .companyId(employeeRequest.getCompanyId())
                        .telephone(employeeRequest.getTelephone())
                        .avatarUrl(employeeRequest.getAvatarUrl())
                        .build());
        return ResultUtil.success(HttpStatus.CREATED);
    }

    /**
     * 获取公司列表
     *
     * @return 单条数据
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【员工登录】参数不正确，loginRequest = {}", loginRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "用户登录信息必须填写完整 ");
        }

        Employee employee = employeeService.login(loginRequest);
        if (employee == null) {
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "用户名或密码错误");
        }

        Company company = companyService.queryById(employee.getCompanyId());

        EmployeeResponse employeeResponse = EmployeeResponse.from(employee, company);

        return ResultUtil.success(HttpStatus.OK, employeeResponse);
    }

    /**
     * 注册公司
     *
     * @param employeeRequest ：用户的信息
     * @param bindingResult   ： 要到参数错误
     * @return 响应
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid EmployeeRequest employeeRequest,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors() || employeeRequest.getId() == null) {
            log.error("【更新员工】参数不正确，employeeRequest = {}", employeeRequest);
            return ResultUtil.error(HttpStatus.NOT_ACCEPTABLE, "注册信息必须填写完整 ");
        }

        if (employeeService.queryById(employeeRequest.getId()) == null) {
            log.error("【更新员工】员工信息内容异常，employeeRequest = {}", employeeRequest);
            return ResultUtil.error(HttpStatus.NOT_FOUND, "ID不正确，没有查到该公司");
        }


        employeeService.update(
                Employee.builder()
                        .id(employeeRequest.getId())
                        .employeeName(employeeRequest.getEmployeeName())
                        .password(employeeRequest.getPassword())
                        .signatureUrl(employeeRequest.getSignatureUrl())
                        .role(employeeRequest.getRole())
                        .companyId(employeeRequest.getCompanyId())
                        .telephone(employeeRequest.getTelephone())
                        .build());
        return ResultUtil.success(HttpStatus.CREATED);
    }

    @GetMapping("/findByName")
    public ResponseEntity queryEmployeeList(@RequestParam(value = "name") String name) {
        List<Employee> employeeList = employeeService.findByName(name);
        return ResultUtil.success(HttpStatus.OK, SearchDto.fromEmployee(employeeList));
    }

}