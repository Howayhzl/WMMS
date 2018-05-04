package com.ncms.comm.exception;


/**
 * 自定义异常
 * @author Administrator
 * @date 2018-1-1 14:39:18
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String err;
    private String code;

    public BizException(String err) {
        super(err);
        this.err = err;
    }

    public BizException(String code, String err) {
        super(err);
        this.code = code;
        this.err = err;
    }

    public String getErr() {
        return err;
    }
    public void setErr(String err) {
        this.err = err;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
    
}