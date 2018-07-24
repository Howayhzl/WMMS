package com.ncms.model.meter.upload;

import java.io.Serializable;
/**
 * 上传类
 * @author admin
 * @date 2018-04-03
 */
public class UploadVO implements Serializable{

	private static final long serialVersionUID = 1645965141674806157L;
	 
	//上传的图片（base64）
	private String imageFile;
	 
	//图片名称
	private String imageName;

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
}
