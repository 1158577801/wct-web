package com.cn.wct.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip压缩工具类
 * 
 * @author 郭望伟
 * @version 2013-10-31 上午10:54:44
 * @since JDK1.6
 */
public class ZipUtil {
	/** 缓存大小 */
	static final int BUFFER = 1024;

	/**
	 * 压缩文件(zip)
	 * 
	 * @designer 张超超 2013-11-7
	 * @developer 张超超 2013-11-7
	 * @modified by
	 * @param zipPathName
	 *            目标文件(zip)路径
	 * @param sourceFile
	 *            要操作的文件（夹）
	 */
	public static void compress(String zipPathName, File sourceFile) {
		// File file = new File(srcPathName);
		if (!sourceFile.exists()) {
			throw new RuntimeException(sourceFile.getName() + "不存在！");
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					zipPathName);
			BufferedOutputStream cos = new BufferedOutputStream(
					fileOutputStream);
			ZipOutputStream out = new ZipOutputStream(cos);

			String basedir = "";
			ZipUtil zipUtil = new ZipUtil();
			zipUtil.compress(sourceFile, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 压缩操作
	 * 
	 * @designer 张超超 2013-11-7
	 * @developer 张超超 2013-11-7
	 * @modified by
	 * @param file
	 *            文件
	 * @param out
	 *            zip输出流
	 * @param basedir
	 *            basedir
	 */
	private void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			this.compressDirectory(file, out, basedir);
		} else {
			this.compressFile(file, out, basedir);
		}
	}

	/**
	 * 压缩目录
	 * 
	 * @designer 张超超 2013-11-7
	 * @developer 张超超 2013-11-7
	 * @modified by
	 * @param dir
	 *            文件目录
	 * @param out
	 *            zip输出流
	 * @param basedir
	 *            basedir
	 */
	private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists()) {
			return;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/**
	 * 压缩单个文件
	 * 
	 * @designer 张超超 2013-11-7
	 * @developer
	 * @modified by
	 * @param file
	 *            文件
	 * @param out
	 *            zip输出流
	 * @param basedir
	 *            basedir
	 */
	private void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		try {
			File newFile = new File("d:/s.txt");
			compress("d:/123.zip", newFile);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
