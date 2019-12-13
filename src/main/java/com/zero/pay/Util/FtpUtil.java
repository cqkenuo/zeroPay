package com.zero.pay.Util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;


/** 远程通过ftp上传静态资源文件至CDN服务器的工具类 */
public class FtpUtil {
	public static String FTP_URL = "122.51.129.31";
	public static int FTP_PORT = 21;
	public static String FTP_ACCOUNT = "zeroteam";
	public static String FTP_PASSWORD = "zxc123,.";
	public static FTPClient FTP_CLIENT;

	/** 初始化ftp服务器 */
	public static void initFTP_CLIENT() {
		try {
			FTP_CLIENT = new FTPClient();
			FTP_CLIENT.setControlEncoding("utf-8");
			FTP_CLIENT.enterLocalPassiveMode();//设置链接方式为被动模式
			FTP_CLIENT.connect(FTP_URL, FTP_PORT); // 连接ftp服务器
			FTP_CLIENT.login(FTP_ACCOUNT, FTP_PASSWORD); // 登录ftp服务器
			int replyCode = FTP_CLIENT.getReplyCode(); // 是否成功登录服务器
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("登录FTP服务器失败！无法上传文件");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 上传文件 */
	@SuppressWarnings("static-access")
	public static boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
		boolean flag = false;
		try {
			initFTP_CLIENT();
			FTP_CLIENT.setFileType(FTP_CLIENT.BINARY_FILE_TYPE);
			createDirecroty(pathname);
			FTP_CLIENT.makeDirectory(pathname);
			FTP_CLIENT.changeWorkingDirectory(pathname);
			FTP_CLIENT.storeFile(fileName, inputStream);
			inputStream.close();
			FTP_CLIENT.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (FTP_CLIENT.isConnected()) {
				try {
					FTP_CLIENT.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/** 上传文件公共模块 */
	public static String uploadFile(MultipartFile file, String directory) throws IOException, InterruptedException {
		if (file == null) {
			return null;
		}
		String fileName = null;
		String Slash = Constants.SLASH_LEFT;
		String ofName = file.getOriginalFilename().toUpperCase();
		// 雪花流获取生成的图片名称保证不会重复
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		long id = snowflake.nextId();
		fileName = ""+id;
		fileName += ofName.substring(ofName.lastIndexOf('.'));
		String path = Slash + directory;
		FtpUtil.uploadFile(path, fileName, file.getInputStream());
		// 文件上传完毕之后转化成url接口返回
		return Constants.imgToUrl(directory, fileName);
	}


	
	// 改变目录路径
	public static boolean changeWorkingDirectory(String directory) {
		boolean flag = true;
		try {
			flag = FTP_CLIENT.changeWorkingDirectory(directory);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return flag;
	}

	// 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
	public static boolean createDirecroty(String remote) throws IOException {
		boolean success = true;
		String directory = remote + "/";
		// 如果远程目录不存在，则递归创建远程服务器目录
		if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			String path = "";
			String paths = "";
			while (true) {
				String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "UTF-8");
				path = path + "/" + subDirectory;
				if (!existFile(path)) {
					if (makeDirectory(subDirectory)) {
						changeWorkingDirectory(subDirectory);
					} else {
						changeWorkingDirectory(subDirectory);
					}
				} else {
					changeWorkingDirectory(subDirectory);
				}

				paths = paths + "/" + subDirectory;
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return success;
	}

	// 判断ftp服务器文件是否存在
	public static boolean existFile(String path) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = FTP_CLIENT.listFiles(path);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}

	// 创建目录
	public static boolean makeDirectory(String dir) {
		boolean flag = true;
		try {
			if (dir.indexOf(".") < 0) {
				flag = FTP_CLIENT.makeDirectory(dir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/** 格式化图片访问路径，将相对路径转换为ngnix访问的真实地址路径 */
	public static String formatFtpUrl(String file_url) {
		if (file_url == null || "".equals(file_url)) {
			return "";
		}
		return Constants.IMG_PATH + file_url;
	}

	/** 反转图片路径，将绝对路径转换为相对路径 */
	public static String reverseFtpUrl(String file_url) {
		if (file_url == null || "".equals(file_url)) {
			return null;
		}
		return file_url.replace(Constants.IMG_PATH, "");
	}

}
