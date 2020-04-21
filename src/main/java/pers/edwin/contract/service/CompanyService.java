package pers.edwin.contract.service;

import pers.edwin.contract.entity.Company;
import pers.edwin.contract.request.CompanyRequest;

import java.util.List;

/**
 * (Company)表服务接口
 *
 * @author makejava
 * @since 2020-04-19 17:27:12
 */
public interface CompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Company queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Company> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Company insert(Company company);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Company update(Company company);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    boolean check(CompanyRequest companyRequest);

    List<Company> queryAll();

    List<Company> findByName(String name);

    String findUrl(Integer id);
}