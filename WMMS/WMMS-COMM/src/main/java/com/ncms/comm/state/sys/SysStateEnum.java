package com.ncms.comm.state.sys;

import com.ncms.comm.base.enums.BaseEnum;

/**
 * 系统配置管理下状态类型 枚举类
 * @author admin
 *
 */
public class SysStateEnum {

	public static final DeptStateEnum DeptStateEnum = new DeptStateEnum();
	public static final DictStateEnum DictStateEnum = new DictStateEnum();
	public static final MenuStateEnum MenuStateEnum = new MenuStateEnum();
	public static final UserStateEnum UserStateEnum = new UserStateEnum();
	public static final RegStateEnum RegStateEnum = new RegStateEnum();
	public static final ParameterStateEnum ParameterStateEnum = new ParameterStateEnum();
	public static final LogTypeEnum LogTypeEnum= new LogTypeEnum();
	
	/**
	 * 枚举 参数状态
	* 
	* Title: ParameterStateEnum
	* @author 
	 */
	public static class ParameterStateEnum extends BaseEnum<Object>{
		
		private static final long serialVersionUID = -4414860276928024596L;

		/**
		 * 部门状态正常 DEPT_STATE_0  可用
		 */
		public static Integer CAN_USE = 0;
		
		/**
		 * 部门状态停用 DEPT_STATE_9  停用
		 */
		public static Integer STOP_USE = 9;
		
		/**
		 * 部门状态已删除 DEPT_STATE__1  已删除
		 */
		public static Integer DROPED = -1;
		
		/**
		 * @Description: 房租缴费提醒天数
		 */
		public static String RENT_ALERT_DAYS="RENT_ALERT_DAYS";
		
		/**
		 * @Description: 合同到期预警提醒天数
		 */
		public static String CONT_ALERT = "CONT_ALERT_DAYS";

		private ParameterStateEnum(){
			super.putEnum(CAN_USE, "可用");
			super.putEnum(STOP_USE, "停用");
			super.putEnum(DROPED, "已删除");
			super.putEnum(RENT_ALERT_DAYS, "RENT_ALERT_DAYS");
		}
	}
	/**
	 * 枚举 部门状态
	* 
	* Title: RegStateEnum
	* @author 
	 */
	public static class DeptStateEnum extends BaseEnum<Object>{
		
		private static final long serialVersionUID = 8075596544011186924L;

		/**
		 * 部门状态正常 DEPT_STATE_0  可用
		 */
		public static Integer CAN_USE = 0;
		
		/**
		 * 部门状态停用 DEPT_STATE_9  停用
		 */
		public static Integer STOP_USE = 9;
		
		/**
		 * 部门状态已删除 DEPT_STATE__1  已删除
		 */
		public static Integer DROPED = -1;

		private DeptStateEnum(){
			super.putEnum(CAN_USE, "可用");
			super.putEnum(STOP_USE, "停用");
			super.putEnum(DROPED, "已删除");
		}
	}
	/**
	 * 枚举 区县状态
	* 
	* Title: DeptStateEnum
	* @author 
	 */
	public static class RegStateEnum extends BaseEnum<Object>{
		
		private static final long serialVersionUID = 8075596544011186924L;

		/**
		 * 区县状态正常 DEPT_STATE_0  可用
		 */
		public static Integer CAN_USE = 0;
		
		/**
		 * 区县状态停用 DEPT_STATE_9  停用
		 */
		public static Integer STOP_USE = 9;
		
		/**
		 * 区县状态已删除 DEPT_STATE__1  已删除
		 */
		public static Integer DROPED = -1;

		private RegStateEnum(){
			super.putEnum(CAN_USE, "可用");
			super.putEnum(STOP_USE, "停用");
			super.putEnum(DROPED, "已删除");
		}
	}
	
	/**
	 * 枚举 数据字典状态
	* 
	* Title: DictStateEnum
	* @author 
	 */
	public static class DictStateEnum extends BaseEnum<Object>{

		private static final long serialVersionUID = 1174133575180480014L;

		/**
		 * 数据字典状态正常 DICT_STATE_0  可用
		 */
		public static Integer CAN_USE = 0;
		
		/**
		 * 数据字典状态停用 DICT_STATE_9  停用
		 */
		public static Integer STOP_USE = 9;
		
		/**
		 * 数据字典状态已删除 DICT_STATE__1  已删除
		 */
		public static Integer DROPED = -1;

		private DictStateEnum(){
			super.putEnum(CAN_USE, "可用");
			super.putEnum(STOP_USE, "停用");
			super.putEnum(DROPED, "已删除");
		}
	}
	
	/**
	 * 枚举 菜单状态
	* 
	* Title: MenuStateEnum
	* @author 
	 */
	public static class MenuStateEnum extends BaseEnum<Object>{

		private static final long serialVersionUID = -4617198336786947935L;

		/**
		 * 菜单状态正常 MENU_STATE_0  可用
		 */
		public static Integer CAN_USE = 0;
		
		/**
		 * 菜单字典状态停用 MENU_STATE_9  停用
		 */
		public static Integer STOP_USE = 9;
		
		/**
		 * 菜单字典状态已删除 MENU_STATE__1  已删除
		 */
		public static Integer DROPED = -1;

		private MenuStateEnum(){
			super.putEnum(CAN_USE, "可用");
			super.putEnum(STOP_USE, "停用");
			super.putEnum(DROPED, "已删除");
		}
	}
	
	/**
	 * 枚举 用户状态
	* 
	* Title: UserStateEnum
	* @author 
	 */
	public static class UserStateEnum extends BaseEnum<Object>{

		private static final long serialVersionUID = -3704311803727497659L;

		/**
		 * 用户状态正常 MENU_STATE_0  可用
		 */
		public static Integer CAN_USE = 0;
		
		/**
		 * 用户状态停用 MENU_STATE_9  停用
		 */
		public static Integer STOP_USE = 9;
		
		/**
		 * 用户状态已删除 MENU_STATE__1  已删除
		 */
		public static Integer DROPED = -1;
		/**
		 * 新增用户默认密码
		 */
		public static String U_PASS_WORD = "1234qwer";

		private UserStateEnum(){
			super.putEnum(CAN_USE, "可用");
			super.putEnum(STOP_USE, "停用");
			super.putEnum(DROPED, "已删除");
			super.putEnum(U_PASS_WORD, "用户默认密码");
		}
	}
	
	public static class LogTypeEnum extends BaseEnum<Object>{

		private static final long serialVersionUID = 2053557275473983131L;
		
		/**
		 * @Description: INFO-提示日志
		 */
		public static String LOG_INFO = "INFO";
		/**
		 * @Description: ERROR-错误日志
		 */
		public static String LOG_ERROR = "ERROR";
		/**
		 * @Description: WARN-警告日志
		 */
		public static String LOG_WARN = "WARN";
		/**
		 * @Description: DEBUG-调试日志
		 */
		public static String LOG_DEBUG = "DEBUG";
		
		
		
	}
}
