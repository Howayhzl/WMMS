package com.ncms.utils.ftp;


public class FtpServer {
	
	private FtpUtils ftpUtils;
	
	public boolean setFtpInfo(String server, int port, String userName, String userPassword) {
		if(ftpUtils==null){
			ftpUtils=new FtpUtils(server,port,userName,userPassword);
		}
		else{
			ftpUtils.setServer(server);
			ftpUtils.setPort(port);
			ftpUtils.setUserName(userName);
			ftpUtils.setUserPassword(userPassword);
		}
		return ftpUtils.open();
	}

	public String[] getFileList(String path) {
		return ftpUtils.getFileNameList(path);
	}
	
	
	
	public void downloadFile(String ftpDirectoryAndFileName, String localDirectoryAndFileName) {
		
		//从FTP上下载文件，ftpDirectoryAndFileName,FTP上的文件路径;localDirectoryAndFileName,本地文件目录
		ftpUtils.get(ftpDirectoryAndFileName, localDirectoryAndFileName);
	}


	public void close() {
		// TODO Auto-generated method stub
		ftpUtils.close();
	}
		

}
