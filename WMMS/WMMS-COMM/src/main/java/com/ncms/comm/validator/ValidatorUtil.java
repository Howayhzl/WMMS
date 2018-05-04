package com.ncms.comm.validator;

/**
 * Created by Administrator on 2017/8/9.
 */

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.ncms.comm.exception.BizException;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-15 10:50
 */
public class ValidatorUtil{
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws BizException  校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups){
        if(object==null){
            throw new BizException("参数不能为空");
        }

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                if(StringUtils.isNotBlank(msg.toString())){
                    msg.append("\n");
                }
                msg.append(constraint.getMessage());
            }
            throw new BizException(msg.toString());
        }
    }
}
