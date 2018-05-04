package com.ncms.comm.validator;

import org.apache.commons.lang3.StringUtils;

import com.ncms.comm.exception.BizException;

/**
 * 数据校验
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String err) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(err);
        }
    }

    public static void isNull(Object object, String err) {
        if (object == null) {
            throw new BizException(err);
        }
    }
}
