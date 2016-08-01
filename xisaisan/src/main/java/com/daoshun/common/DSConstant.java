/**
 * 
 */
package com.daoshun.common;

import java.util.HashMap;

/**
 * @author qiuch
 * 
 */
public class DSConstant {
	public static final String CODE = "code";
	public static final String MESSAGE = "message";
	public static final String ERR_MESSAGE = "errmessage";
	public static final int KEYCODE_SUCCESS = 1;
	public static final int PAGESIZE = 10;
	public static final String KEYMSG_SUCCESS = "操作成功。";
	public static final String EXCEPTION = "{\"message\":\"网络状况不佳，请稍后再试\",\"code\":100}";
	public static final String USERERROR = "{\"message\":\"用户名不存在\",\"code\":102}";
	public static final String PASSERROR = "{\"message\":\"密码错误\",\"code\":102}";
	public static final HashMap<String, Object> SUCCESS_RESULT_MAP = new HashMap<String, Object>() {
		private static final long serialVersionUID = 1L;

		{
			put("code", 1);
			put("message", "操作成功");
		}
	};
}
