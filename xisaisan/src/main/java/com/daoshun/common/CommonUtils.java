/**
 * 
 */
package com.daoshun.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.daoshun.exception.NullParameterException;

/**
 * @author qiuch
 * 
 */
public class CommonUtils {

	/**
	 * 读取配置文件
	 */
	public static Properties properties = new Properties();
	static {
		try {
			String path = "config.properties";
			InputStream inStream = CommonUtils.class.getClassLoader().getResourceAsStream(path);
			if (inStream == null) {
				inStream = CommonUtils.class.getClassLoader().getResourceAsStream("/" + path);
			}
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 文件访问路径
	public static String getFileRootUrl() {
		return properties.getProperty("fileRootUrl");
	}

	public static String getFilePath() {
		return properties.getProperty("filePath");
	}

	public static String getWebRootUrl() {
		return properties.getProperty("webRootUrl");
	}

	/**
	 * 检查路径是否存在，不存在则创建路径
	 * 
	 * @param path
	 */
	public static void checkPath(String path) {
		String[] paths = null;
		if (path.contains("/")) {
			paths = path.split(File.separator);
		} else {
			paths = path.split(File.separator + File.separator);
		}
		if (paths == null || paths.length == 0) {
			return;
		}
		String pathdir = "";
		for (String string : paths) {
			pathdir = pathdir + string + File.separator;
			File file = new File(pathdir);
			if (!file.exists()) {
				file.mkdir();
			}
		}
	}

	/**
	 * 判断String是否为空
	 * 
	 */
	public static boolean isEmptyString(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * String -> int
	 * 
	 * @param String
	 * @return int
	 */
	public static int parseInt(String string, int def) {
		if (isEmptyString(string))
			return def;
		int num = def;
		try {
			num = Integer.parseInt(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> long
	 * 
	 * @param String
	 * @return long
	 */
	public static long parseLong(String string, long def) {
		if (isEmptyString(string))
			return def;
		long num;
		try {
			num = Long.parseLong(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> double
	 * 
	 * @param String
	 * @return long
	 */
	public static double parseDouble(String string, double def) {
		if (isEmptyString(string))
			return def;
		double num;
		try {
			num = Double.parseDouble(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def, int digit) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		if (digit > 0 && num != def) {
			BigDecimal bigDecimal = new BigDecimal(num);
			float twoDecimalP = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			return twoDecimalP;
		} else {
			return num;
		}
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String getTimeFormat(Date date, String string) {
		SimpleDateFormat sdFormat;
		if (isEmptyString(string)) {
			sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			sdFormat = new SimpleDateFormat(string);
		}
		try {
			return sdFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * MD5加密,32位小写
	 * 
	 * @param 要加密的String
	 * @return 加密后String
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str).toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化时间- String转换Date "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param 格式化前的时间
	 * @return 格式化后的时间
	 */
	public static Date getDateFormat(String date, String format) {
		if (isEmptyString(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断参数是否为空
	 * 
	 */
	public static void validateEmpty(String value) throws NullParameterException {
		if (value == null || value.length() == 0) {
			throw new NullParameterException();
		}
	}

	public static String clearHtml(String detail) {
		if (!isEmptyString(detail)) {
			Pattern p = Pattern.compile("<[^>]*>");
			Matcher m = p.matcher(detail);
			while (m.find()) {
				detail = detail.replace(m.group(0), "").trim();
			}
		}
		return detail;
	}

	public static String cleanXSS(String value) {
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}

	public static long pageCount(long total, int pageNum) {
		long pageCount = 0;
		if (total != 0 && pageNum != 0) {
			if (total % pageNum == 0) {
				pageCount = total / pageNum;
			} else {
				pageCount = total / pageNum + 1;
			}
		}
		return pageCount;
	}
}
