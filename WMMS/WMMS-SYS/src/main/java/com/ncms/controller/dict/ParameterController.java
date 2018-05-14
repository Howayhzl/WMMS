package com.ncms.controller.dict;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.model.sys.dict.SysParameter;
import com.ncms.service.dict.SysParameterService;
import com.ncms.utils.id.T_ID_GEN;


@RestController
public class ParameterController{
	
	@Autowired
	private SysParameterService sysParameterService;
	/**
	 * 查询所有参数
	 * @param session
	 * @param cur_page_num
	 * @param page_count
	 * @param prvId
	 * @param paraCode
	 * @param paraValue
	 * @param paraNote
	 * @return
	 */
	@RequestMapping(value="/queryParameter", method = RequestMethod.GET)
	public BackEntity queryParameter(int cur_page_num,int page_count,
			String paraCode,String paraValue,String paraNote){
		SysParameter sysparameter = new SysParameter();
		sysparameter.setParaCode(paraCode);
		sysparameter.setParaValue(paraValue);
		sysparameter.setParaNote(paraNote);
		/*sysparameter.setParaState(paraState);
		sysparameter.setParaOrder(paraOrder);*/
		Page<SysParameter> SysParameterPage = sysParameterService.queryParameter(sysparameter,cur_page_num,page_count);
		return BackEntity.ok("查询系统参数成功！", SysParameterPage.toPageInfo());
	}
	/**
	 * 很据id获取参数对象
	 * @param paraId
	 * @return
	 */
	@RequestMapping(value="/getParameter",method=RequestMethod.GET)
	public BackEntity getParameter(String paraId){
		SysParameter sysParameter = sysParameterService.getParameter(paraId);
		return BackEntity.ok("查询成功", sysParameter);
	}
	/**
	 * 修改参数
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateParameter",method=RequestMethod.POST)
	public BackEntity updateParameter(HttpServletRequest request){
		SysParameter sysParameter = new SysParameter();
		sysParameter.setParaId(request.getParameter("para_id"));
		sysParameter.setParaCode(request.getParameter("para_code"));
		sysParameter.setParaValue(request.getParameter("para_value"));
		sysParameter.setParaNote(request.getParameter("para_note"));
		sysParameter.setParaState(Integer.parseInt(request.getParameter("para_state")));
		sysParameter.setParaOrder(Integer.parseInt(request.getParameter("para_order")));
		String res = sysParameterService.updateParameter(sysParameter);
		if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("修改系统参数{"+sysParameter.getParaCode()+"}成功！");
		}else{
			return BackEntity.ok("修改系统参数{"+sysParameter.getParaCode()+"}失败！");
		}
	}
	/**
	 * 新增参数
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/insertParameter",method=RequestMethod.POST)
	public BackEntity insertParameter(HttpServletRequest request){
		SysParameter sysParameter = new SysParameter();
		sysParameter.setParaId(T_ID_GEN.sys_id().replace("-", ""));
		sysParameter.setParaCode(request.getParameter("para_code"));
		sysParameter.setParaValue(request.getParameter("para_value"));
		sysParameter.setParaNote(request.getParameter("para_note"));
		sysParameter.setParaState(0);
		sysParameter.setParaOrder(Integer.parseInt(request.getParameter("para_order")));
		String res = sysParameterService.insertParameter(sysParameter);
		if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("新增系统参数{"+sysParameter.getParaCode()+"}成功！");
		}else{
			return BackEntity.ok("新增系统参数{"+sysParameter.getParaCode()+"}失败！");
		}
	}
	/**
	 * 启用参数
	 * @param session
	 * @param paraId
	 * @return
	 */
	@RequestMapping(value = "openParameter",method=RequestMethod.POST)
	public BackEntity openParameter(String paraId,String paraCode){
		String res = sysParameterService.openParameter(paraId);
		if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("启用系统参数{"+paraCode+"}成功！");
		}else{
			return BackEntity.ok("启用系统参数{"+paraCode+"}失败！");
		}
	}
	/**
	 * 删除参数
	 * @param session
	 * @param paraId
	 * @return
	 */
	@RequestMapping(value = "deleteParameter",method=RequestMethod.POST)
	public BackEntity deleteParameter(String paraId,String paraCode){
		String res = sysParameterService.deleteParameter(paraId);
		if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("删除系统参数{"+paraCode+"}成功！");
		}else{
			return BackEntity.ok("删除系统参数{"+paraCode+"}失败！");
		}
	}
	/**
	 * 停用参数
	 * @param session
	 * @param paraId
	 * @return
	 */
	@RequestMapping(value = "stopParameter",method=RequestMethod.POST)
	public BackEntity stopParameter(String paraId,String paraCode){
		String res = sysParameterService.stopParameter(paraId);
		if(res.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("停用系统参数{"+paraCode+"}成功！");
		}else{
			return BackEntity.ok("停用系统参数{"+paraCode+"}失败！");
		}
	}
}
