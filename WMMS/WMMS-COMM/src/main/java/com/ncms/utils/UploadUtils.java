package com.ncms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.setting.dialect.Props;
import com.xiaoleilu.hutool.util.StrUtil;


/**
 * 文件上传工具类
 * 
 * @author zhujj
 * @date 2017-08-14
 */
public class UploadUtils {
	/**
	 * 表单字段常量
	 */
	public static final String FORM_FIELDS = "form_fields";
	/**
	 * 文件域常量
	 */
	public static final String FILE_FIELDS = "file_fields";

	// 存储表单字段和非表单字段
	Map<String,Object> fields = new HashMap<>();
	// 最大文件大小
	private long maxSize = 10540000;
	// 定义允许上传的文件扩展名
	private Map<String, String> extMap = new HashMap<String, String>();
	// 文件保存目录相对路径 
	/*private static PropertiesLoader loader = new PropertiesLoader("properties/sysConfig.properties");
	private String basePath = loader.getProperty("UploadUrls");*/
	public static  Props props = new Props("properties/sysConfig.properties");
	public static  String basePath = props.getProperty("UploadUrls");
	// 文件的目录名
	private String dirName = "images";
	// 上传临时路径
	private static final String TEMP_PATH = "/temp";
	private String tempPath = basePath + TEMP_PATH;
	// 若不指定则文件名默认为 yyyyMMddHHmmss_xyz
	private String fileName;

	// 文件保存目录路径
	private String savePath;
	// 文件保存目录url
	private String saveUrl;
	// 文件最终的url包括文件名
	private String fileUrl;
	//数据库保存路径
	private String dataUrl;

	public UploadUtils() {
		// 其中images,flashs,medias,files,对应文件夹名称,对应dirName
		// key文件夹名称
		// value该文件夹内可以上传文件的后缀名
		extMap.put("images", "gif,jpg,jpeg,png,bmp");
		extMap.put("flashs", "swf,flv");
		extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("files", "doc,docx,xls,xlsx,ppt,pdf,htm,html,txt,zip,rar,gz,bz2,gif,jpg,jpeg,png,bmp");
	}

	/**
	 * 文件上传
	 * @param request
	 * @param newName 新文件名，传Null则不重命名
	 * @param path 增加自定义目录
	 * @return infos  验证文件域返回错误信息  上传文件错误信息  savePath  saveUrl  fileUrl
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> uploadFile(HttpServletRequest request,String newName,String path) {
		Map<String,Object> infos=new HashMap<>();
		
		// 验证
		String err=this.validateFields(request,path);
		infos.put("err",err);
		// 初始化表单元素
		Map<String, Object> fieldsMap = new HashMap<String, Object>();
		if (err.equals("true")) {
			fieldsMap = this.initFields(request);
		}
		
		// 上传 datacollecttypeId
		List<FileItem> fiList = (List<FileItem>) fieldsMap.get(UploadUtils.FILE_FIELDS);
		if (fiList != null) {
			for (FileItem item : fiList) {
				 
				String name=item.getName();
				if(StrUtil.isBlank(newName)){//防止文件名重名。
					String fileNameWithoutExtension=FileUtils.getFileNameWithoutExtension(name);//没有扩展名的文件名
					newName=fileNameWithoutExtension+"_"+System.currentTimeMillis();//+"."+fileExtension
				}
				String fileExtension=FileUtils.getFileExtension(name);//取扩展名
				fileName=StrUtil.isNotBlank(newName)?newName.replace(" ","")+"."+fileExtension:name.replace(" ","");
				fileName=fileName.replace(" ","");//去掉文件名中的空格  
				newName=newName.replace(" ","");
				infos.put("fileName",fileName);//新名称
				infos.put("name",name);//本身的名称
				infos.put("saveErr",this.saveFile(item,newName));
			}
			
			infos.put("savePath",savePath);
			infos.put("saveUrl",saveUrl);
			infos.put("fileUrl",fileUrl);
			infos.put("path",dataUrl+fileName);
		}
		return infos;
	}
	/**
	 * 文件上传
	 * @param request
	 * @param newName 新文件名，传Null则不重命名
	 * @param path 增加自定义目录
	 * @return infos  验证文件域返回错误信息  上传文件错误信息  savePath  saveUrl  fileUrl
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> newuploadFile(String imageFile,String newName,String imageName) {
/*        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码      
        byte[] imageByte = null;
        try {
            imageByte = decoder.decodeBuffer(imageFile);      
            for (int i = 0; i < imageByte.length; ++i) {      
                if (imageByte[i] < 0) {// 调整异常数据      
                    imageByte[i] += 256;      
                }      
            }      
        } catch (Exception e) {
             e.printStackTrace(); 
        }   */                
		
	
		
		Map<String,Object> infos=new HashMap<>();
		
		// 验证
//		String err=this.validatePathFields(path);
//		infos.put("err",err);
		// 初始化表单元素
//		Map<String, Object> fieldsMap = new HashMap<String, Object>();
//		if (err.equals("true")) {
//			fieldsMap = this.initFields(request);
//		}
//		
		// 上传 datacollecttypeId
//		List<FileItem> fiList = (List<FileItem>) fieldsMap.get(UploadUtils.FILE_FIELDS);
//		if (fiList != null) {
//			for (FileItem item : fiList) {
				 
//				String name=item.getName();
				if(StrUtil.isBlank(newName)){//防止文件名重名。
					String fileNameWithoutExtension=FileUtils.getFileNameWithoutExtension(imageName);//没有扩展名的文件名
					newName=fileNameWithoutExtension+"_"+System.currentTimeMillis();//+"."+fileExtension
				}
				String fileExtension=FileUtils.getFileExtension(imageName);//取扩展名
				fileName=StrUtil.isNotBlank(newName)?newName.replace(" ","")+"."+fileExtension:imageName.replace(" ","");
				fileName=fileName.replace(" ","");//去掉文件名中的空格  
				newName=newName.replace(" ","");
				
				
				
				byte[] bt = null;  
		         try {  
			         sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
			         bt = decoder.decodeBuffer(imageFile);  
		         } catch (IOException e) {  
		        	 e.printStackTrace();  
		         }  
		        
		/*		 try {
				     // 生成文件         
				     File imageFile = new File(filename);
				     imageFile.createNewFile();
				        if(!imageFile.exists()){
				            imageFile.createNewFile();
				         }
				         OutputStream imageStream = new FileOutputStream(imageFile);
				         imageStream.write(bt);
				         imageStream.flush();
				         imageStream.close();                    
				 } catch (Exception e) {         
				     e.printStackTrace();
				 }*/
				
				
				
				infos.put("fileName",fileName);//新名称
				infos.put("name",imageName);//本身的名称
				infos.put("saveErr",this.saveNewFile(bt,newName));
//			}
			
			infos.put("savePath",savePath);
			infos.put("saveUrl",saveUrl);
			infos.put("fileUrl",fileUrl);
			infos.put("path",dataUrl+fileName);
//		}
		return infos;
	}
	/**
	 * 文件上传
	 * @param request
	 * @param newName 新文件名，传Null则不重命名
	 * @param path 增加自定义目录
	 * @return infos  验证文件域返回错误信息  上传文件错误信息  savePath  saveUrl  fileUrl
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject patchUploadFile(String imageFile,String newName,String path,String imageName) {
		 JSONObject infos =new JSONObject(true);
//		Map<String,Object> infos=Maps.newHashMap();
			String err=this.validatePathFields(path);
				if(StrUtil.isBlank(newName)){//防止文件名重名。
					String fileNameWithoutExtension=FileUtils.getFileNameWithoutExtension(imageName);//没有扩展名的文件名
					newName=fileNameWithoutExtension+"_"+System.currentTimeMillis();//+"."+fileExtension
				}
				String fileExtension=FileUtils.getFileExtension(imageName);//取扩展名
				fileName=StrUtil.isNotBlank(newName)?newName.replace(" ","")+"."+fileExtension:imageName.replace(" ","");
				fileName=fileName.replace(" ","");//去掉文件名中的空格  
				newName=newName.replace(" ","");
				byte[] bt = null;  
		         try {  
			         sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
			         bt = decoder.decodeBuffer(imageFile);  
		         } catch (IOException e) {  
		        	 e.printStackTrace();  
		         }  
				
				
				infos.put("fileName",fileName);//新名称
				infos.put("name",imageName);//本身的名称
				infos.put("saveErr",this.saveNewFile(bt,newName));
//			}
			
			infos.put("savePath",savePath);
			infos.put("saveUrl",saveUrl);
			infos.put("fileUrl",fileUrl);
			infos.put("path",dataUrl+fileName);
//		}
		return infos;
	}
	/**
	 * 上传验证,并初始化文件目录
	 * 
	 * @param request
	 */
	private String validateFields(HttpServletRequest request,String path) {
		String errorInfo = "true";
		// boolean errorFlag = true;
		// 获取内容类型
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		// 文件保存目录路径
		savePath = basePath + "/";
		// 文件保存目录URL
		saveUrl =  basePath + "/";
		File uploadDir = new File(savePath);
		if (contentType == null || !contentType.startsWith("multipart")) {
			// TODO
			System.out.println("请求不包含multipart/form-data流");
			errorInfo = "请求不包含multipart/form-data流";
		} else if (maxSize < contentLength) {
			// TODO
			System.out.println("上传文件大小超出文件最大大小");
			errorInfo = "上传文件大小超出文件最大大小[" + maxSize + "]";
		} else if (!ServletFileUpload.isMultipartContent(request)) {
			// TODO
			errorInfo = "请选择文件";
		} else if (!uploadDir.isDirectory()) {// 检查目录
			// TODO
			errorInfo = "上传目录[" + savePath + "]不存在";
			//FileUtils.createDirectory(saveUrl);
		} else if (!uploadDir.canWrite()) {
			// TODO
			errorInfo = "上传目录[" + savePath + "]没有写权限";
		} else if (!extMap.containsKey(dirName)) {
			// TODO
			errorInfo = "目录名不正确";
		} else {
			// .../basePath/dirName/
			// 创建文件夹
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			dataUrl ="/" + dirName + "/";

			if(StrUtil.isNotBlank(path)){
				savePath +=path + "/";// 增加自定义目录
				saveUrl += path + "/";
				dataUrl += path + "/";
			}
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			// .../basePath/dirName/yyyyMMdd/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			String ymd = sdf.format(new Date());
			savePath +=ymd + "/";// prvCode + "/" +
			saveUrl += ymd + "/";
			dataUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			// 获取上传临时路径
			tempPath = tempPath + "/";
			File file = new File(tempPath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}

		return errorInfo;
	}
	private String validatePathFields(String path) {
		String errorInfo = "true";
		// boolean errorFlag = true;
		// 文件保存目录路径
//		savePath = basePath + "/";
//		// 文件保存目录URL
//		saveUrl =  basePath + "/";
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {// 检查目录
			// TODO
			errorInfo = "上传目录[" + savePath + "]不存在";
			//FileUtils.createDirectory(saveUrl);
		} else if (!uploadDir.canWrite()) {
			// TODO
			errorInfo = "上传目录[" + savePath + "]没有写权限";
		} else if (!extMap.containsKey(dirName)) {
			// TODO
			errorInfo = "目录名不正确";
		} else {
			// .../basePath/dirName/
			// 创建文件夹
//			savePath += dirName + "/";
//			saveUrl += dirName + "/";
//			dataUrl ="/" + dirName + "/";

			if(StrUtil.isNotBlank(path)){
				savePath +=path +File.separator;// 增加自定义目录
				saveUrl += path + File.separator;
//				dataUrl += path + "/";
			}
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			// .../basePath/dirName/yyyyMMdd/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			String ymd = sdf.format(new Date());
			savePath +=ymd + File.separator;// prvCode + "/" +
			saveUrl += ymd + File.separator;
//			dataUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
/*
			// 获取上传临时路径
			tempPath = tempPath + "/";
			File file = new File(tempPath);
			if (!file.exists()) {
				file.mkdirs();
			}*/
		}

		return errorInfo;
	}
	/**
	 * 处理上传内容
	 * 
	 * @param request
	 * @param maxSize
	 * @return
	 */
//	@SuppressWarnings("unchecked")
	private Map<String, Object> initFields(HttpServletRequest request) {

		// 第一步：判断request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 第二步：解析request
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 阀值,超过这个值才会写到临时目录,否则在内存中
			factory.setSizeThreshold(1024 * 1024 * 10);
			factory.setRepository(new File(tempPath));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setHeaderEncoding("UTF-8");

			// 最大上传限制
			upload.setSizeMax(maxSize);

			/* FileItem */
			List<FileItem> items = null;
			// Parse the request
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 第3步：处理uploaded items
			if (items != null && items.size() > 0) {
				Iterator<FileItem> iter = items.iterator();
				// 文件域对象
				List<FileItem> list = new ArrayList<FileItem>();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// 处理所有表单元素和文件域表单元素
					if (item.isFormField()) { // 表单元素
						String name = item.getFieldName();
						String value = item.getString();
						fields.put(name, value);
					} else { // 文件域表单元素
						list.add(item);
					}
				}
				fields.put(FORM_FIELDS, fields);
				fields.put(FILE_FIELDS, list);
			}
		}
		return fields;
	}
/*
	*//**
	 * 保存文件
	 * 
	 * @param obj
	 *            要上传的文件域
	 * @param file
	 * @return
	 */
	private String saveFile(FileItem item,String newName) {
		String error = "true";
		String fileName = item.getName().replace(" ", "");
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

		if (item.getSize() > maxSize) { // 检查文件大小
			// TODO
			error = "上传文件大小超过限制";
		} else if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {// 检查扩展名
			error = "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
		} else {
			String newFileName;
			if(StrUtil.isNotBlank(newName)){
				newFileName = newName+ "." + fileExt;//+ "." + fileExt
			}else if ("".equals(fileName.trim())) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			} else{
				newFileName = fileName;
			}
			// .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
			fileUrl = saveUrl + newFileName;
			try {
				File uploadedFile = new File(savePath, newFileName);

				item.write(uploadedFile);

			/*	
				 * FileOutputStream fos = new FileOutputStream(uploadFile); // 文件全在内存中 if (item.isInMemory()) { fos.write(item.get()); } else { InputStream is = item.getInputStream(); byte[] buffer =
				 * new byte[1024]; int len; while ((len = is.read(buffer)) > 0) { fos.write(buffer, 0, len); } is.close(); } fos.close(); item.delete();*/
				 
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("上传失败了！！！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return error;
	}

	/**
	 * 保存文件
	 * 
	 * @param obj
	 *            要上传的文件域
	 * @param file
	 * @return
	 */
	private String saveNewFile(byte[] bt,String newName) {
		String error = "true";
//		String fileName = item.getName().replace(" ", "");
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

		if (bt.length > maxSize) { // 检查文件大小
			// TODO
			error = "上传文件大小超过限制";
		} else if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {// 检查扩展名
			error = "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。";
		} else {
			String newFileName;
			if(StrUtil.isNotBlank(newName)){
				newFileName = newName+ "." + fileExt;//+ "." + fileExt
			}else if ("".equals(fileName.trim())) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			} else{
				newFileName = fileName;
			}
//			 String bin_path = savePath.substring(savePath.lastIndexOf("\\")+1,savePath.length());  
//			fileUrl =saveUrl+"\\"+ bin_path +"\\"+ newFileName;
			fileUrl =saveUrl+ newFileName;
//			fileUrl = saveUrl + newFileName;
//			fileUrl =saveUrl+"\\"+ savePath+"\\" + newFileName;
			try {
				File imageFile = new File(savePath, newFileName);
				imageFile.createNewFile();
		        if(!imageFile.exists()){
		            imageFile.createNewFile();
		         }
		         OutputStream imageStream = new FileOutputStream(imageFile);
		         imageStream.write(bt);
		         imageStream.flush();
		         imageStream.close();              
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("上传失败了！！！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return error;
	}
	/** **********************get/set方法********************************* */

	public String getSavePath() {
		return savePath;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public Map<String, String> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, String> extMap) {
		this.extMap = extMap;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
		tempPath = basePath + TEMP_PATH;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
