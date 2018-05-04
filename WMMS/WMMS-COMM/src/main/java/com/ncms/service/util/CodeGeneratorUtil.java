package com.ncms.service.util;

import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ncms.comm.exception.BizException;
import com.ncms.constant.UserCodeComm;
import com.ncms.mapper.util.CodeGeneratorUtilMapper;
import com.xiaoleilu.hutool.date.DateUtil;

/**
 * @author SongJL
 * @date 2017年5月25日
 * @description 主要放一些code生成方法
 */
@Component
public class CodeGeneratorUtil{


	@Resource
	private  CodeGeneratorUtilMapper codeUtilMapper;
	private static CodeGeneratorUtilMapper codeGeneratorUtilMapper;

	
	@PostConstruct  
    public void init(){  
		 CodeGeneratorUtil.codeGeneratorUtilMapper = codeUtilMapper;
    }  
	/**
	 * 生成组织部门代码
	 * @param PId 传入父CODE
	 * @return
	 */
	public static String DeptCodeGet(String PId,String Pcode){
		try {
			return getCode(UserCodeComm.CODEPREFIX_DEPT, "sys_department", "dep_code", "pdep_id", PId, Pcode);
		} catch (Exception e) {
			throw new BizException("生成组织部门代码失败");
		}
	}
	/**
	 * 生成系统专业代码
	 * @param PId 传入父CODE
	 * @return
	 */
	public static String MajorCodeGet(String PId,String PCode){
		try {
			return getCode("sys_major", "major_code", "pmajor_id", PId, PCode);
		} catch (Exception e) {
			throw new BizException("生成系统专业代码失败");
		}
	}
	/**
	 * 生成功能菜单代码
	 * @param PId 传入父CODE
	 * @return
	 */
	public static String MenuCodeGet(String PId, String PCode){
		try {
			return getCode(UserCodeComm.CODEPREFIX_MENU, "sys_menu", "menu_code", "pmenu_id", PId, PCode);	
		} catch (Exception e) {
			throw new BizException("生成功能菜单代码失败");
		}
	}
	
	/**
	 * 生成角色代码
	 * @param PId 传入父CODE
	 * @return
	 */
	public static String RoleCodeGet(){
		try {
			return getCode(UserCodeComm.CODEPREFIX_ROLE, "sys_role", "role_code");	
		} catch (Exception e) {
			throw new BizException("生成角色代码失败");
		}
	}
	/**
	 * 生成租费报账汇总单号
	 * @param pre 功能省份前缀 ZDHZD-AH-20170719-00001
	 * 自维电费：ZDHZD
	 * 铁塔电费：TDHZD
	 * 自维租费：ZZHZD
	 * 铁塔租费（铁塔产品服务费）：TZHZD；
	 * @return
	 */
	public static String BillAmountCodeGet(String prvCode){
		try {					
			String pre="ZZHZD-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyyMMdd");
			String whereSql="billamount_code like '"+prex+"%'";
			return getAmountCode(prex, "rnt_billamount", "billamount_code",5,whereSql);	
		} catch (Exception e) {
			throw new BizException("生成租费报账汇总单号失败");
		}
	}
	
	/**
	 * 生成电费缴费报账汇总单号
	 * @param pre 功能省份前缀 ZDHZD-AH-20170719-00001
	 * 自维电费：ZDHZD
	 * 铁塔电费：TDHZD
	 * 自维租费：ZZHZD
	 * 铁塔租费（铁塔产品服务费）：TZHZD；
	 * @return
	 */
	public static String BillElecAmountCodeGet(String prvCode){
		try {
			String pre="ZDHZD-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyyMMdd");
			String whereSql="billamount_code like '"+prex+"%'";
			return getAmountCode(prex, "ele_billamount", "billamount_code",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("生成电费缴费报账汇总单号失败");
		}
	}
	
	/**
	 * 
	 * 根据表名和列名获取code 格式如：ZDHZD-AH-20170719-00001
	 * @param prex 前缀
	 * @param tableName 表名
	 * @param colName 字段名
	 * @param length  序号长度
	 * @return 日期+序号 例如：ZDHZD-AH-20170719-00001
	 */
	private static synchronized String getAmountCode(String prex, 
			String tableName, String colName, int length,String whereSql){
		String minCode = getMaxCodeAllInfo(tableName, colName, "","",whereSql);
		
		if(minCode == null || "".equals(minCode)||minCode.split("-").length<4){
			minCode=codeAddZero("1", length);
		}else{

			String[] strSplit=minCode.split("-");
			
			int num=Integer.parseInt(strSplit[3])+1;
			minCode=codeAddZero(""+num, length);
		}
		
		return prex +"-"+ minCode;
	}
	/**
	 * 
	 * 根据表名和列名获取code
	 * @param prex 前缀
	 * @param tableName
	 * @param colName
	 * @param pcolName
	 * @param PId
	 * @return
	 */
	@SuppressWarnings("unused")
	private static synchronized String getCode(String prex, String tableName, 
			String colName, String pcolName, String PId, String PCode){
		String minCode = getMinCode(tableName, colName, pcolName, PId);
		if(minCode == null || "".equals(minCode)){
			if(PId!=null || PId!="")
				return PCode + "01";
			else
				return prex + "01";
		}
		if(minCode.length()%2 == 1){//少位
			return prex + "0" + minCode;
		}
		return prex + minCode;
	}
	/**
	 * 根据表名和列名获取code（系统专业，例1.1.2）
	 * @param tableName
	 * @param colName
	 * @param pcolName
	 * @param PId
	 * @param PCode
	 * @return
	 */
	private static synchronized String getCode(String tableName, 
			String colName, String pcolName, String PId, String PCode){
		String minCode = getMajorMinCode(tableName, colName, pcolName, PId);
		if(minCode == null || "".equals(minCode)){
			if(PId!=null && PId!="")
				return PCode + ".1";
		}
		if(PId==null || "".equals(PId)){
			return minCode;
		}
		if(PId.equals("2017")){
			return minCode;
		}
		return PCode + "." + minCode;
	}
	/**
	 * 
	 * 获取自增角色code
	 * @param prex 前缀
	 * @param tableName
	 * @param colName
	 * @param pcolName
	 * @return
	 */
	private static synchronized String getCode(String prex, String tableName, 
			String colName){
		String maxCode = getMaxCode(tableName, colName);
		if(maxCode == null || maxCode.length() <= 1 || maxCode.length()%2 == 1){//少位
			return prex + "0" + maxCode;
		}
		return prex + maxCode;
	}
	/**
	 * 根据表名和列名获取不连续最小code
	 * @param tableName
	 * @param colName
	 * @return
	 */
	private static String getMaxCode(String tableName, String colName){
		return codeGeneratorUtilMapper.selectMaxCodeFromTable(colName,colName);
	}
	/**
	 * 根据表名和列名获取已存在最大code
	 * @param tableName
	 * @param colName
	 * @return
	 */
	private static String getMinCode(String tableName, String colName, String pcolName, String PId){
		return codeGeneratorUtilMapper.selectMinCodeFromTable(tableName,colName,pcolName,PId);
	}
	/**
	 * 根据表名和列名获取最小不连续code(系统专业)
	 * @param tableName
	 * @param colName
	 * @return
	 */
	private static String getMajorMinCode(String tableName, String colName, String pcolName, String PId){
		return codeGeneratorUtilMapper.selectMajorMinCodeFromTable(tableName,colName,pcolName,PId);
	}
	/**
	 * 根据表名和列名获取已存在最大code完整信息
	 * @param tableName
	 * @param colName
	 * @author zhujj
	 * @return
	 */
	private static String getMaxCodeAllInfo(String tableName, String colName, String pcolName, String PId,String whereSql){
		return codeGeneratorUtilMapper.selectMaxCodeAllInfoFromTable(colName,tableName,whereSql);
	}
	
	/**
	 * 
	 * @param type
	 * @param provinceCode
	 * @param number
	 * @return
	 */
	public static String buildBillAccountCode(String type,String provinceCode,long number){
		StringBuffer sb = new StringBuffer(type).append("-");
		sb.append(provinceCode).append("-");
		sb.append(DateUtil.format(new Date(), "yyyy")).append("-");
		DecimalFormat df = new DecimalFormat("000000");
		sb.append(df.format(number));
		return sb.toString();
	}
	
	/**
	 * 生成租费抱枕点code
	 * @param pre 功能省份前缀 ZZBZD-AH-2018-00001
	 * @return
	 */
	public static String RentBillaccountCodeGet(String prvCode){
		try {
			String pre="ZZBZD-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyyMMdd");
			String whereSql="billaccount_code like '"+prex+"%'";
			return getAmountCode(prex, "rnt_billaccount", "billaccount_code",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("生成租费报账点编码失败！");
		}
	}
	
	/**
	 * 生成电费预付费code
	 * @param pre 功能省份前缀 ZDHZD-AH-20170719-00001
	 * 自维电费：ZDHZD
	 * 铁塔电费：TDHZD
	 * 自维租费：ZZHZD
	 * 铁塔租费（铁塔产品服务费）：TZHZD；
	 * @return
	 */
	public static String ElecLoanCodeGet(String prvCode){
		try {
			String pre="ZDYFF-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyyMMdd");
			String whereSql="loan_code like '"+prex+"%'";
			return getAmountCode(prex, "ele_loan", "loan_code",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("生成电费预付费报账汇总单号失败");
		}finally{
		}
	}
	
	/**
	 * 生成电费核销code
	 * @param pre 功能省份前缀 ZDHZD-AH-20170719-00001
	 * 自维电费：ZDHZD
	 * 铁塔电费：TDHZD
	 * 自维租费：ZZHZD
	 * 铁塔租费（铁塔产品服务费）：TZHZD；
	 * @return
	 */
	public static String ElecVerificationCodeGet(String prvCode){
		try {
			String pre="ZDHXD-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyyMMdd");
			String whereSql="verification_code like '"+prex+"%'";
			return getAmountCode(prex, "ele_verification", "verification_code",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("生成电费核销报账汇总单号失败");
		}finally{
		}
	}
	
	/**
	 * 生成站点cuid
	 * @param pre 功能省份前缀 Site-AH-20170719-00001
	 * @return
	 */
	public static String BasesiteCuidGet(String prvCode){
		try {
			String pre="Site-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyy");
			String whereSql="basesite_cuid like '"+prex+"%'";
			return getAmountCode(prex, "dat_basesite", "basesite_cuid",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("生成站点CUID失败");
		}finally{
		}
	}
	
	/**
	 * 生成资源cuid
	 * @param pre 功能省份前缀 ""-AH-20170719-00001
	 * @return
	 */
	public static String BaseresourceCuidGet(String prvCode,Integer resourceType){
		try {
			String pre="";
			if(0 == resourceType){
				pre="Room-"+prvCode;
			}else if(1 == resourceType){
				pre="ResourceSpot-"+prvCode;
			}else if(2 == resourceType){
				pre="HootSpot-"+prvCode;
			}else if(3 == resourceType){
				pre="Position-"+prvCode;
			}
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyy");
			String whereSql="baseresource_cuid like '"+prex+"%'";
			return getAmountCode(prex, "dat_baseresource", "baseresource_cuid",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			if(0 == resourceType){
				throw new BizException("生成机房CUID失败！");
			}else if(1 == resourceType){
				throw new BizException("生成资源点CUID失败！");
			}else if(2 == resourceType){
				throw new BizException("生成热点CUID失败！");
			}else if(3 == resourceType){
				throw new BizException("生成位置点CUID失败！");
			}else{
				throw new BizException("生成位置点CUID失败！");
			}
		}finally{
		}
	}
	
	/**
	 * 生成铁塔cuid
	 * @param pre 功能省份前缀 Tower-AH-20170719-00001
	 * @return
	 */
	public static String BaseTowerGet(String prvCode){
		try {
			String pre="Tower-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyy");
			String whereSql="tower_cid like '"+prex+"%'";
			return getAmountCode(prex, "dat_basetower", "tower_cid",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("生成铁塔CUID失败");
		}finally{
		}
	}
	
	/**
	 * 生成天线cuid
	 * @param pre 功能省份前缀 Antenna-AH-20170719-00001
	 * @return
	 */
	public static String BaseAntennaGet(String prvCode){
		try {
			String pre="Antenna-"+prvCode;
			String prex=pre+"-"+DateUtil.format(new Date(),"yyyy");
			String whereSql="antenna_cid like '"+prex+"%'";
			return getAmountCode(prex, "dat_baseantenna", "antenna_cid",5,whereSql);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("生成天线CUID失败");
		}finally{
		}
	}
	
	/**
	 * 给定长度编码串前边补0
	 * @param code 编码  如：01
	 * @param len 长度  如：5
	 * @return 00001
	 */
	public static String codeAddZero(String code, int len){
	   Integer intHao = Integer.parseInt(code);
	   String strHao = intHao.toString();
	   while (strHao.length() < len) {
	       strHao = "0" + strHao;
	     }
	   return strHao;
	}
	
}
