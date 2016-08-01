package com.xishanyang.contoller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daoshun.common.CommonUtils;
import com.xishanyang.service.ProductService;

@Controller
public class ProductController {
	@Resource
	private ProductService productService;

	@RequestMapping("/productlist")
	public ModelAndView themelist(Integer pageIndex, String type) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		long total = productService.productListCount(type);
		mav.addObject("plist", productService.productList(pageIndex, type));
		mav.addObject("total", total);
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("pageCount", CommonUtils.pageCount(total, 9));
		mav.addObject("path", CommonUtils.getFileRootUrl());
		mav.addObject("type", type);
		mav.setViewName("/djqzxyd");
		return mav;
	}

	@RequestMapping("/porder")
	public ModelAndView porder(Long pid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pid", pid);
		mav.setViewName("/djqzxyd_cont");
		return mav;
	}

	@RequestMapping("/order")
	public ModelAndView order(String name, String phone, String comment, Long pid) {
		ModelAndView mav = new ModelAndView();
		if (pid != null) {
			String code = productService.addOrder(name, phone, comment, pid);
			mav.addObject("code", code);
			mav.setViewName("redirect:./orderresult");
		} else {
			mav.addObject("err", "产品信息错误，请返回重新选择");
			mav.setViewName("/djqzxyd.html");
		}
		return mav;
	}

	@RequestMapping("/orderresult")
	public ModelAndView order(String code) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("code", code);
		mav.setViewName("/djqzxyd_cont_0");
		return mav;
	}

	@RequestMapping("/checkstatus")
	public ModelAndView checkstatus(String code) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("code", code);
		mav.addObject("result", productService.checkstatus(code));
		mav.setViewName("/djqzxyd_chaxun_0");
		return mav;
	}

}
