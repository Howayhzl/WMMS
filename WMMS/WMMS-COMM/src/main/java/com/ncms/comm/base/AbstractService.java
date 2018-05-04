package com.ncms.comm.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncms.comm.exception.BizException;
import com.ncms.config.mybatis.MyMapper;

/**
 * Created by Admin.
 * <p>
 * AbstractService  Implement By MyBatis
 * <p>
 * 基于通用Mapper的MyBatis来实现Service接口的抽象Service
 */
public abstract class AbstractService<T> implements BaseService<T> {

    @Autowired
    protected MyMapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    @SuppressWarnings("unchecked")
	public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public int insert(T model) {
        return mapper.insertSelective(model);
    }

    @Override
    public int deleteById(String id) {
    	return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(String ids) {
       return mapper.deleteByIds(ids);
    }

    @Override
    public int update(T model) {
    	return mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T findById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String property, Object value) {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    @Override
    public List<T> findByIds(String ids) {

        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findByEntity(T entity) {
        return mapper.select(entity);
    }

    @Override
    public T findOne(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }
    
    @Override
	public Page<T> findByPage(T entity, int start, int count){
		PageHelper.offsetPage(start, count);
		return (Page<T>) mapper.select(entity);

	}
    
    @Override
	public Page<T> findByPageCondition(Condition condition, int start, int count){
		PageHelper.offsetPage(start, count);
		return (Page<T>) mapper.selectByCondition(condition);

	}
}