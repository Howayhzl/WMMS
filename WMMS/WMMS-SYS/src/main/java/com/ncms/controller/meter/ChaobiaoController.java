package com.ncms.controller.meter;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ncms.comm.http.BackEntity;
import com.ncms.comm.http.RESULT;
import com.ncms.constant.PromptMessage;
import com.ncms.model.dat.attachment.DatAttachment;
import com.ncms.model.meter.PrdChaobiao;
import com.ncms.model.meter.PrdChaobiaoVO;
import com.ncms.model.meter.upload.UploadVO;
import com.ncms.service.attachment.DatAttachmentService;
import com.ncms.service.meter.PrdChaobiaoService;
import com.ncms.service.meter.PrdMeterService;
import com.ncms.utils.FileUtils;
import com.ncms.utils.ShiroUtils;
import com.ncms.utils.UploadUtils;

/** 
 * @ClassName: ChangeMeterController 
 * @Description: 水表操作接口 
 * @author Wesley.Xia 
 * @date 2018年5月25日 下午10:05:28 
 *  
 */
@RestController
public class ChaobiaoController {

	@Autowired
	private PrdChaobiaoService chaobiaoService;
	
	@Autowired
	private PrdMeterService meterService;
    
    @Autowired
    DatAttachmentService attachmentService;
	
	@RequestMapping(value = "/chaobiao/list", method = RequestMethod.POST)
	public BackEntity getAllMeters(@RequestParam Map<String,Object> paramMap,
			int cur_page_num,int page_count)
	{
		
		Page<PrdChaobiaoVO> meterList = chaobiaoService.queryAllChaobiaos(paramMap,cur_page_num,page_count);
		return BackEntity.ok(PromptMessage.SELECT_USER_SUCCESS,meterList.toPageInfo());
	}
	
	public @ResponseBody BackEntity uploadFile(HttpServletRequest request, @RequestBody UploadVO uploadObj){
//    	Calendar now = Calendar.getInstance(); 
//    	String dateStr=now.get(Calendar.YEAR)+""+(now.get(Calendar.MONTH) + 1)+now.get(Calendar.DAY_OF_MONTH);
    	String pic_path;
    	String tomcat_path = System.getProperty( "user.dir" );  
    	//获取Tomcat服务器所在路径的最后一个文件目录  
    	String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());  
    	//若最后一个文件目录为bin目录，则服务器为手动启动  
    	if(("bin").equals(bin_path)){//手动启动Tomcat时获取路径为：D:\Software\Tomcat-8.5\bin  
    		//获取保存上传图片的文件路径  
    		pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("\\")) +"\\webapps"+"\\upload-files\\";  
//              pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("\\")) +"\\webapps"+"\\upload-files\\";  
    	}else{//服务中自启动Tomcat时获取路径为：D:\Software\Tomcat-8.5  
//              pic_path = tomcat_path+"\\webapps"+"\\upload-files\\"+"\\"+prvCode+"\\"+"\\"+dateStr+"\\";  
    		pic_path = tomcat_path+"\\webapps"+"\\upload-files\\";  
    	}  
    	
//    	String headSculpturePath = request.getSession().getServletContext().getRealPath("/");
//    	 String bin_path = headSculpturePath.substring(headSculpturePath.lastIndexOf("/")+1,headSculpturePath.length());  
		
    	String requestUrl = request.getScheme() //当前链接使用的协议
    		    +"://" + request.getServerName()//服务器地址 
    		    + ":" + request.getServerPort(); //端口号 
//    	String 	fileUrl =requestUrl+"/"+ bin_path;
    	String 	fileUrl =requestUrl+"\\upload-files\\";
    	UploadUtils upload = new UploadUtils();
		upload.setDirName("files");
//		upload.setSavePath(headSculpturePath);
		upload.setSavePath(pic_path);
		upload.setSaveUrl(fileUrl);
		Map<String, Object> map = upload.newuploadFile(uploadObj.getImageFile(), null, uploadObj.getImageName());//去掉payment PromptMessageComm.URL_PAYMENT+prvCode
		if (map != null && map.get("err") != null
				&& !map.get("err").toString().equals("true")) {// 文件验证失败
	        return BackEntity.error("验证失败");
		}
		if (map != null && map.get("saveErr") != null
				&& !map.get("saveErr").toString().equals("true")) {// 文件上传保存失败
	        return BackEntity.error("验证失败");
		}
		DatAttachment attach = new DatAttachment();
		attach.setAttachmentName(map.get("name").toString());
		attach.setAttachmentUrl(map.get("fileUrl").toString());
		String fileExtension=FileUtils.getFileExtension(map.get("name").toString());//取扩展名
		attach.setAttachmentNote(fileExtension);
		int i=attachmentService.insertAttachment(attach);
    	if(i>0){
    		return BackEntity.ok("上传附件：【"+attach.getAttachmentName()+"】成功", map);
    	}else{
    		return BackEntity.error("上传附件：【"+attach.getAttachmentName()+"】失败", map);
    	}
    }
	@RequestMapping(value = "/chaobiao/add", method = RequestMethod.POST)
	public BackEntity addChaobiao(String meterId, Date chaobiaoDate, double preValue, double currentValue, String image,
			Date startDate,Date endDate)
	{
		PrdChaobiao chaobiao = new PrdChaobiao();
		
		String chaobiaoId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
		
		chaobiao.setChaobiaoId(chaobiaoId);
		chaobiao.setMeterId(meterId);
		chaobiao.setChaobiaoDate(chaobiaoDate);
		chaobiao.setPreValue(preValue);
		chaobiao.setCurrentValue(currentValue);
		chaobiao.setSubmiterId(ShiroUtils.getUserId());
		chaobiao.setImage(image);
		chaobiao.setStartDate(startDate);
		chaobiao.setEndDate(endDate);		
		String result = chaobiaoService.addChaobiao(chaobiao);
		
		if (result.equals(RESULT.SUCCESS_1)) {
			result = meterService.updateMeterValue(meterId, currentValue, false);
		}
		
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("添加水表操作成功");
		}else{
			return BackEntity.error("加水表操作失败");
		}
	}
	
	@RequestMapping(value = "/chaobiao/delete", method = RequestMethod.POST)
	public BackEntity deleteChaobiao(String chaobiaoId)
	{
	
		String result = chaobiaoService.deleteChaobiaobyId(chaobiaoId);
		
		if(result.equals(RESULT.SUCCESS_1)){
			return BackEntity.ok("添加水表操作成功");
		}else{
			return BackEntity.error("加水表操作失败");
		}
	}
}
