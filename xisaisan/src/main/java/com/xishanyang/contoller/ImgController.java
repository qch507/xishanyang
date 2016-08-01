package com.xishanyang.contoller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.daoshun.common.CommonUtils;
import com.xishanyang.service.ImgService;

@Controller
public class ImgController {
	@Resource
	private ImgService imgService;

	@RequestMapping("/themelist")
	public ModelAndView themelist(Integer pageIndex) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		long total = imgService.themecount();
		mav.addObject("themelist", imgService.themeList(pageIndex));
		mav.addObject("total", total);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageCount", CommonUtils.pageCount(total, 9));
		mav.addObject("path", CommonUtils.getFileRootUrl());
		mav.setViewName("/djqfg");
		return mav;
	}

	@RequestMapping("/imglist")
	public ModelAndView imglist(String theme) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	@RequestMapping("/uploadimg")
	public ModelAndView imglist(String theme, String name, @RequestParam("imgfile") MultipartFile imgfile) {
		ModelAndView mav = new ModelAndView();

		return mav;
	}

}
