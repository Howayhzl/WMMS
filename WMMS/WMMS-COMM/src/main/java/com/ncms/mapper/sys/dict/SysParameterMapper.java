package com.ncms.mapper.sys.dict;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.dict.SysParameter;

public interface SysParameterMapper extends MyMapper<SysParameter>{

	/**  
	 * @author zsf
	 * @date 2018年5月14日  
	 * @Description: 系统参数模糊查询
	 *
	 * @param sysParameter
	 * @return
	 */
	@Select({"<script>"+"select sys_parameter.para_id,sys_parameter.para_code,sys_parameter.para_value,sys_parameter.para_note,sys_parameter.para_state,sys_parameter.para_order "
						+ "from sys_parameter"
			            +"<where>"
							+ "<when test=\"paraCode != null and paraCode != ''\">"
							+"and LOCATE(#{paraCode,jdbcType=VARCHAR},'para_code') &gt; 0"
							+"</when>"
							+"<when test=\"paraValue != null and paraValue != ''\">"
							+"and LOCATE(#{paraValue,jdbcType=VARCHAR},'para_value') &gt; 0"
							+"</when>"
							+"<when test=\"paraNote != null and paraNote != ''\">"
							+"and LOCATE(#{paraNote,jdbcType=VARCHAR},'para_note') &gt; 0"	
							+"</when>"
						+"</where>"
						+"order by para_order"
		    +"</script>"}
		)
	public List<SysParameter> queryParameter(SysParameter sysParameter);

	/**  
	 * @author zsf
	 * @date 2018年5月14日  
	 * @Description: 修改系统参数状态
	 *
	 * @param state
	 * @param paraId
	 * @return
	 */
	@Update({"<script>" +"update sys_parameter set para_state = #{state}"
						+"where para_id = #{paraId,jdbcType=VARCHAR}"
			+"</script>"}
			)
	public int updateParameterState(@Param("state") Integer state,@Param("paraId") String paraId);
}
