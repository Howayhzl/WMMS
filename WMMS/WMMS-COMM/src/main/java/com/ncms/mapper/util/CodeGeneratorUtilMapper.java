package com.ncms.mapper.util;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CodeGeneratorUtilMapper {
	
	/**
	 * 查询最小不连续code
	 * @author SongJL
	 * @param param
	 * @return
	 */
	@Select({"<script>"+"select MIN(substring(${colName}, 2)+1) from ${tableName} where 1=1 " +
			"<when test=\"parentId!=null and parentId != ''\"> "+
				"AND ${pcolName}=#{parentId, jdbcType=VARCHAR} "+
				"AND substring(${colName}, 2)+1 not in (select substring(${colName}, 2) from ${tableName} where ${pcolName}=#{parentId, jdbcType=VARCHAR}) "+
			"</when>"+
		    "</script>"}
		)
	public String selectMinCodeFromTable(@Param("tableName") String tableName, @Param("colName") String colName,
			@Param("pcolName") String pcolName, @Param("parentId") String parentId);
	/**
	 * 查询最小不连续code（系统专业）
	 * @param param
	 * @return
	 */
	@Select({"<script>"+"select MIN(SUBSTRING_INDEX(${colName},'.',-1)+1) from ${tableName} where 1=1 " +
			"<when test=\"parentId != null and parentId!=''\"> "+
			"AND ${pcolName}=#{parentId, jdbcType=VARCHAR} "+
			"AND SUBSTRING_INDEX(${colName},'.',-1)+1 not in ( select SUBSTRING_INDEX(${colName},'.',-1) from ${tableName} where ${pcolName}=#{parentId, jdbcType=VARCHAR}) "+
			"</when>"+
			"<when test=\"parentId == null or parentId ==''\"> "+
			"AND ${pcolName}  = '' "+
			"AND SUBSTRING_INDEX(${colName},'.',-1)+1 not in ( select SUBSTRING_INDEX(${colName},'.',-1) from ${tableName} where ${pcolName}  = '') "+
			"</when>"+
			"</script>"})
	public String selectMajorMinCodeFromTable(@Param("tableName") String tableName, @Param("colName") String colName,
			@Param("pcolName") String pcolName, @Param("parentId") String parentId);
	/**
	 * 查询自增最大code
	 * @author SongJL
	 * @param param
	 * @return
	 */
	@Select("select MAX(substring(${colName}, 2)+1) from ${tableName}")
	public String selectMaxCodeFromTable( @Param("colName")String colName,@Param("tableName")String tableName);
	/**
	 * 获取最大可用编码完整信息
	 * @author zhujj
	 * @param param
	 * @return
	 */
	@Select("select MAX(substring(${colName}, 2)+1) from ${tableName} where ${whereSql} limit 1")
	public String selectMaxCodeAllInfoFromTable(@Param("colName")String colName,@Param("tableName") String tableName, @Param("whereSql")String whereSql);
	
}
