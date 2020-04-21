package pers.edwin.contract.service.impl;

import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.Employee;
import pers.edwin.contract.dao.EmployeeDao;
import pers.edwin.contract.request.EmployeeRequest;
import pers.edwin.contract.request.LoginRequest;
import pers.edwin.contract.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Employee)表服务实现类
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Employee queryById(Integer id) {
        return this.employeeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Employee> queryAllByLimit(int offset, int limit) {
        return this.employeeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee) {
        this.employeeDao.insert(employee);
        return employee;
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee update(Employee employee) {
        this.employeeDao.update(employee);
        return this.queryById(employee.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.employeeDao.deleteById(id) > 0;
    }

    @Override
    public boolean check(EmployeeRequest employeeRequest) {

        List<Employee> listByName = employeeDao.queryAll(Employee
                .builder()
                .employeeName(employeeRequest.getEmployeeName())
                .build());
        List<Employee> listByTelephone = employeeDao.queryAll(Employee
                .builder()
                .telephone(employeeRequest.getTelephone())
                .build());

        return listByName.size() == 0 && listByTelephone.size() == 0;
    }

    @Override
    public Employee login(LoginRequest loginRequest) {
        List<Employee> employeeList = employeeDao.queryAll(Employee.builder()
                .telephone(loginRequest.getTelephone())
                .password(loginRequest.getPassword())
                .build());
        if (employeeList.size() == 0) {
            return null;
        }

        return employeeList.get(0);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeDao.findByName("%" + name + "%");
    }

}