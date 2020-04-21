package pers.edwin.contract.service;

import pers.edwin.contract.entity.ContractType;
import pers.edwin.contract.request.NameRequest;

import java.util.List;

/**
 * (ContractType)表服务接口
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
public interface ContractTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ContractType queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ContractType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param contractType 实例对象
     * @return 实例对象
     */
    ContractType insert(ContractType contractType);

    /**
     * 修改数据
     *
     * @param contractType 实例对象
     * @return 实例对象
     */
    ContractType update(ContractType contractType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<ContractType> queryAll();

    boolean check(NameRequest nameRequest);
}