package com.ncms.comm.http;

public class HTTPCODE {

	/**
	 * 200 OK - [GET]：服务器成功返回用户请求的数据
	 */
	public final static String CODE_SUCCESS = "200";

	/**
	 * 201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
	 */
	public final static String CODE_CREATED = "201";

	/**
	 * 202 ACCEPTED - [*]：表示一个请求已经进入后台排队（异步任务）
	 */
	public final static String CODE_ACCEPTED = "202";

	/**
	 * 204 NO CONTENT - [DELETE]：用户删除数据成功
	 */
	public final static String CODE_DELETE = "204";

	/**
	 *	301-代表永久性转移
     */
	public final static String CODE_PERMANENTLY_MOVED = "301";

	/**
	 *	400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作
     */
	public final static String CODE_INVALID_REQUEST = "400";

	/**
	 *	406 NOT ACCEPTABLE - [GET]：用户请求的格式不可得
     */
	public final static String CODE_NOT_ACCEPTABLE = "406";

	/**
	 * 500-业务逻辑错误
	 */
	public final static String CODE_ERROR_SERVICE = "500";

	/**
	 * 501-功能不完善，无对应方法
	 */
	public final static String CODE_ERROR_FUNCTION = "501";

	/**
	 * 502-网络异常
	 */
	public final static String CODE_ERROR_WEB = "502";
	/**
	 * 503-未知其它
	 */
	public final static String CODE_ERROR_OTHER = "503";

}
