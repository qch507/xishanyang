package com.xishanyang.contoller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.daoshun.common.CommonUtils;
import com.daoshun.common.QueryResult;
import com.xishanyang.pojo.NewsInfo;
import com.xishanyang.service.FeedbackService;
import com.xishanyang.service.NewsService;

@Controller
public class NewsController {

	@Resource
	private NewsService newsService;

	@Resource
	private FeedbackService feedbackService;

	@RequestMapping("/main")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", newsService.getNotice());
		mav.addObject("news", newsService.getThreeNews());
		mav.addObject("feedback", feedbackService.getThreefeedbacks());
		mav.setViewName("/index");
		return mav;
	}

	@RequestMapping("/newslist")
	public ModelAndView newslist(Integer pageIndex) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		QueryResult<NewsInfo> nResult = newsService.pagedNews(pageIndex);
		mav.addObject("news", nResult.getDataList());
		mav.addObject("total", nResult.getTotal());
		mav.addObject("pageCount", CommonUtils.pageCount(nResult.getTotal(), 10));
		mav.addObject("pageIndex", pageIndex);
		mav.setViewName("/news");
		return mav;
	}

	@RequestMapping("/newsdetail")
	public ModelAndView newsdetail(Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("news", newsService.getNews(id));
		mav.setViewName("/news_cont");
		return mav;
	}

}
