package com.ncms.comm.http;



/**
 * 全局回调返回对象
 * @author admin
 */
public class BackEntity {

	public String success;
	public String code;
	public String msg;
	public Object Obj;

	/**
	 * 正确返回
	 * @param code 200
	 * @param msg
	 * @return
	 */
	public static BackEntity ok(String msg){
		BackEntity entity = new BackEntity();
		entity.setSuccess(RESULT.SUCCESS_1);
		entity.setCode(HTTPCODE.CODE_SUCCESS);
		entity.setMsg(msg);
		return entity;
	}
	
	/**
	 * 正确返回
	 * @param code 200
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static BackEntity ok(String msg, Object obj){
		BackEntity entity = new BackEntity();
		entity.setSuccess(RESULT.SUCCESS_1);
		entity.setCode(HTTPCODE.CODE_SUCCESS);
		entity.setMsg(msg);
		entity.setObj(obj);
		return entity;
	}

	/**
	 * 异常返回
	 * @param code 500-业务逻辑错误
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static BackEntity error(String msg){
		BackEntity entity = new BackEntity();
		entity.setSuccess(RESULT.FAIL_0);
		entity.setCode(HTTPCODE.CODE_ERROR_SERVICE);
		entity.setMsg(msg);
		return entity;
	}

	/**
	 * 异常返回
	 * @param code 500-业务逻辑错误
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static BackEntity error(String msg, Object obj){
		BackEntity entity = new BackEntity();
		entity.setSuccess(RESULT.FAIL_0);
		entity.setCode(HTTPCODE.CODE_ERROR_SERVICE);
		entity.setMsg(msg);
		entity.setObj(obj);
		return entity;
	}

	/**
	 * 异常返回
	 * @param code com.ncms.comm.http.HTTPCODE
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static BackEntity error(String code, String msg,Object obj){
		BackEntity entity = new BackEntity();
		entity.setSuccess(RESULT.FAIL_0);
		entity.setCode(code);
		entity.setMsg(msg);
		entity.setObj(obj);
		return entity;
	}
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return Obj;
	}
	public void setObj(Object obj) {
		Obj = obj;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "BackEntity [success=" + success + ", code=" + code + ", msg="
				+ msg + ", Obj=" + Obj + "]";
	}

}
