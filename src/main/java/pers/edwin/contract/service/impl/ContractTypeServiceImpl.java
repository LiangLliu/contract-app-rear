package pers.edwin.contract.service.impl;

import pers.edwin.contract.entity.Company;
import pers.edwin.contract.entity.ContractType;
import pers.edwin.contract.dao.ContractTypeDao;
import pers.edwin.contract.request.NameRequest;
import pers.edwin.contract.service.ContractTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ContractType)表服务实现类
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
@Service("contractTypeService")
public class ContractTypeServiceImpl implements ContractTypeService {
    @Resource
    private ContractTypeDao contractTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ContractType queryById(Integer id) {
        return this.contractTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ContractType> queryAllByLimit(int offset, int limit) {
        return this.contractTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param contractType 实例对象
     * @return 实例对象
     */
    @Override
    public ContractType insert(ContractType contractType) {
        this.contractTypeDao.insert(contractType);
        return contractType;
    }

    /**
     * 修改数据
     *
     * @param contractType 实例对象
     * @return 实例对象
     */
    @Override
    public ContractType update(ContractType contractType) {
        this.contractTypeDao.update(contractType);
        return this.queryById(contractType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.contractTypeDao.deleteById(id) > 0;
    }

    @Override
    public List<ContractType> queryAll() {
        return contractTypeDao.queryAll(ContractType.builder().build());
    }

    @Override
    public boolean check(NameRequest nameRequest) {

        List<ContractType> contractTypeList = contractTypeDao
                .queryAll(ContractType.builder()
                        .type(nameRequest.getName()).build());
        return contractTypeList.size() == 0;

    }
}