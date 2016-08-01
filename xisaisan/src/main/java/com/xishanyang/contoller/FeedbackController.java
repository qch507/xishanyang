package com.xishanyang.contoller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daoshun.common.CommonUtils;
import com.daoshun.common.QueryResult;
import com.xishanyang.pojo.FeedBack;
import com.xishanyang.service.FeedbackService;

@Controller
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;

	@RequestMapping("/feedback")
	public ModelAndView index(Integer pageIndex) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		QueryResult<FeedBack> fResult = feedbackService.pagedFeedbackShowed(pageIndex);
		mav.addObject("feedback", fResult.getDataList());
		mav.addObject("total", fResult.getTotal());
		mav.addObject("pageCount", CommonUtils.pageCount(fResult.getTotal(), 10));
		mav.addObject("pageIndex", pageIndex);
		mav.setViewName("/bbs");
		return mav;
	}

	@RequestMapping("/publishfeedback")
	public String index(String name, String phone, String address, String content) {
		feedbackService.feedback(name, phone, address, content);
		return "redirect:/feedback";
	}

}
