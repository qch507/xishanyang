package com.xishanyang.contoller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.daoshun.common.CommonUtils;

public class SessionInterceptorAdapter extends HandlerInterceptorAdapter {

	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws IOException {

		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

		// 如果有允许通过的url 则直接通过
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		}

		long admin_id = CommonUtils.parseInt(String.valueOf(request.getSession().getAttribute("admin_id")), 0);
		if (admin_id == 0) {
			response.sendRedirect("./login.jsp");
			return false;
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

}
