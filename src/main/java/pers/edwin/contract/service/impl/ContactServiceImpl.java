package pers.edwin.contract.service.impl;

import pers.edwin.contract.entity.Contact;
import pers.edwin.contract.dao.ContactDao;
import pers.edwin.contract.service.ContactService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Contact)表服务实现类
 *
 * @author makejava
 * @since 2020-04-19 17:27:14
 */
@Service("contactService")
public class ContactServiceImpl implements ContactService {
    @Resource
    private ContactDao contactDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Contact queryById(Integer id) {
        return this.contactDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Contact> queryAllByLimit(int offset, int limit) {
        return this.contactDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param contact 实例对象
     * @return 实例对象
     */
    @Override
    public Contact insert(Contact contact) {
        this.contactDao.insert(contact);
        return contact;
    }

    /**
     * 修改数据
     *
     * @param contact 实例对象
     * @return 实例对象
     */
    @Override
    public Contact update(Contact contact) {
        this.contactDao.update(contact);
        return this.queryById(contact.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.contactDao.deleteById(id) > 0;
    }
}