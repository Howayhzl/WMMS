package com.ncms.mapper.sys.region;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ncms.config.mybatis.MyMapper;
import com.ncms.model.sys.region.SysRegion;

/**
 * @date 2018-01-09 15:07:21
 */
public interface SysRegionMapper extends MyMapper<SysRegion> {
	/**
	 * @author yanyy
	 * @date 2018年5月14日  
	 * @Description: 查询市级所属区域
	 *
	 * @param prvId
	 * @param regIds
	 * @return
	 */
	@Select({"<script>"+
		    "SELECT reg_id AS regId,reg_code AS regCode,reg_name AS regName "+
			"FROM sys_region where reg_state=0 "+
		    "<when test=\"prvId!=null and prvId!=''\">"+
		    	"AND preg_id=#{prvId, jdbcType=VARCHAR} "+
		    "</when>"+
		    "<when test=\"regIds!=null and regIds.size()>0\">"+
		    	"AND reg_id in "+
		    	"<foreach collection=\"regIds\" item=\"_item\" open=\"(\" separator=\",\" close=\")\" >"+
		    		"#{_item} "+
		    	"</foreach>"+
		    "</when>"+
			"</script>"})
	public List<SysRegion> queryCityList(@Param("prvId")String prvId,@Param("regIds")List<String> regIds);
}