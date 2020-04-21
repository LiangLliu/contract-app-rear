package pers.edwin.contract.service;

import pers.edwin.contract.entity.Employee;
import pers.edwin.contract.request.EmployeeRequest;
import pers.edwin.contract.request.LoginRequest;

import java.util.List;

/**
 * (Employee)表服务接口
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
public interface EmployeeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Employee queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Employee> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee insert(Employee employee);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee update(Employee employee);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    boolean check(EmployeeRequest employeeRequest);

    Employee login(LoginRequest loginRequest);

    List<Employee> findByName(String name);
}