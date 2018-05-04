package com.ncms.comm.base;

import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;

/**
 * Created by Admin.
 * <p>
 * Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @param <T> the model type parameter
 */
public interface BaseService<T> {
    /**
     *
     * @param model
     */
    int insert(T model);

    /**
     * 通过主鍵刪除
     * @param id
     */
    int deleteById(String id);

    /**
     * 批量刪除 eg：ids -> “1,2,3,4”
     * @param ids
     */
    int deleteByIds(String ids);

    /**
     * 更新
     * @param model
     */
    int update(T model);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 通过某个成员属性查找,value需符合unique约束
     * @param property
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String property, Object value);

    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据对象做查询条件查找
     * @param condition
     * @return
     */
    List<T> findByEntity(T entity);

    /**
     * 根据查询条件查找
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取该表所有数据
     * @return
     */
    List<T> findAll();

    /**
     * 获取该表唯一一条数据
     * @return
     */
	T findOne(T entity);

    /**
     * 分页查询该表所有数据
     * @return
     */
    Page<T> findByPage(T entity, int start, int count);

    /**
     * 根据条件分页查询该表所有数据
     * @return
     */
    Page<T> findByPageCondition(Condition condition, int start, int count);
}
