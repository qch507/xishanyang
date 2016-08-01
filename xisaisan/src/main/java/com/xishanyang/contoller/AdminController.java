package com.xishanyang.contoller;

import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.daoshun.common.CommonUtils;
import com.daoshun.common.QueryResult;
import com.xishanyang.pojo.FeedBack;
import com.xishanyang.pojo.ImgInfo;
import com.xishanyang.pojo.NewsInfo;
import com.xishanyang.pojo.ProductInfo;
import com.xishanyang.service.FeedbackService;
import com.xishanyang.service.ImgService;
import com.xishanyang.service.NewsService;
import com.xishanyang.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private FeedbackService feedbackService;

	@Resource
	private NewsService newsService;

	@Resource
	private ImgService imgService;

	@Resource
	private ProductService productService;

	@RequestMapping("/login")
	public ModelAndView index(String user_name, String password, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if ("admin".equals(user_name) && "123456".equals(password)) {
			mav.setViewName("redirect:./newslist");
		} else {
			mav.addObject("err", "用户名密码有误");
			mav.setViewName("/admin/login");
		}
		session.setAttribute("admin_id", 384985859l);
		return mav;
	}

	@RequestMapping("/newslist")
	public ModelAndView userinfo(Integer pageIndex) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		QueryResult<NewsInfo> nResult = newsService.pagedAllNews(pageIndex);
		mav.addObject("newslist", nResult.getDataList());
		mav.addObject("total", CommonUtils.pageCount(nResult.getTotal(), 10));
		mav.addObject("pageIndex", pageIndex);
		mav.setViewName("/admin/zixun");
		return mav;
	}

	@RequestMapping("/newsdetail")
	public ModelAndView newsdetail(Long newsid) {
		ModelAndView mav = new ModelAndView();
		if (newsid != null) {
			mav.addObject("news", newsService.getNews(newsid));
		} else {
			mav.addObject("news", new NewsInfo());
		}
		mav.setViewName("/admin/newsdetail");
		return mav;
	}

	@RequestMapping("/editnews")
	public ModelAndView editnews(NewsInfo newsinfo, String publishtime) {
		ModelAndView mav = new ModelAndView();
		Date ctime = CommonUtils.getDateFormat(publishtime, "yyyy-MM-dd HH:mm");
		if (ctime == null) {
			ctime = new Date();
		}
		String content = newsinfo.getContent();
		Matcher m = Pattern.compile("<img\\s*([^>]*)>").matcher(content);
		while (m.find()) {
			String strBefore = m.group();
			String strAfter = strBefore.replace("/>", " style='width:98%; margin-left:1%'/>");
			content = content.replace(m.group(), strAfter);
		}
		newsinfo.setContent(content);
		newsinfo.setCreate_time(ctime);
		newsService.editNews(newsinfo);
		mav.setViewName("redirect:/admin/newslist");
		return mav;
	}

	@RequestMapping("/delnews")
	public String delnews(Long newsid) {
		if (newsid != null) {
			newsService.delNews(newsid);
		}
		return "redirect:./newslist";
	}

	@RequestMapping("/feedbacklist")
	public ModelAndView feedbacklist(Integer pageIndex) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		QueryResult<FeedBack> fResult = feedbackService.pagedFeedback(pageIndex);
		mav.addObject("feedback", fResult.getDataList());
		mav.addObject("total", CommonUtils.pageCount(fResult.getTotal(), 10));
		mav.addObject("pageIndex", pageIndex);
		mav.setViewName("/admin/fankui");
		return mav;
	}

	@RequestMapping("/feedbackreply")
	public String feedbackreply(Long fid, String reply) {
		if (fid != null) {
			feedbackService.feedbackReply(fid, reply);
		}
		return "redirect:./feedbacklist";
	}

	@RequestMapping("/feedbackshow")
	public String feedbackreply(Long fid) {
		if (fid != null) {
			feedbackService.feedbackShow(fid);
		}
		return "redirect:./feedbacklist";
	}

	@RequestMapping("/feedbackdel")
	public String feedbackdel(Long fid) {
		if (fid != null) {
			feedbackService.delFeedback(fid);
		}
		return "redirect:./feedbacklist";
	}

	@RequestMapping("/imgthemelist")
	public ModelAndView themelist(Integer pageIndex) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		mav.addObject("themelist", imgService.themeList(pageIndex));
		mav.addObject("total", CommonUtils.pageCount(imgService.themecount(), 9));
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("path", CommonUtils.getFileRootUrl());
		mav.setViewName("/admin/imgtheme");
		return mav;
	}

	@RequestMapping("/deltheme")
	public String imgtheme(String theme) {
		imgService.delTheme(theme);
		return "redirect:/admin/imgthemelist";
	}

	@RequestMapping("/uploadimg")
	public ModelAndView uploadimg(String theme, String name, @RequestParam("imgfile") MultipartFile imgfile) {
		ModelAndView mav = new ModelAndView();
		ImgInfo info = new ImgInfo();
		info.setCreate_time(new Date());
		info.setName(name);
		info.setTheme(theme);
		info.setUrl(saveFile(imgfile));
		imgService.uploadImg(info);
		mav.setViewName("redirect:./imgthemelist");
		return mav;
	}

	@RequestMapping("/productlist")
	public ModelAndView productlist(Integer pageIndex, String type) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		mav.addObject("plist", productService.productList(pageIndex, type));
		mav.addObject("total", CommonUtils.pageCount(productService.productListCount(type), 9));
		mav.addObject("pageIndex", pageIndex);
		mav.addObject("path", CommonUtils.getFileRootUrl());
		mav.setViewName("/admin/product");
		return mav;
	}

	@RequestMapping("/editproduct")
	public String editproduct(ProductInfo product, @RequestParam("imgfile") MultipartFile imgfile) {
		if (product != null) {
			if (imgfile != null) {
				product.setPic(saveFile(imgfile));
			}
			productService.editProduce(product);
		}
		return "redirect:./productlist";
	}

	@RequestMapping("/delproduct")
	public String delproduct(Long pid) {
		productService.delProduce(pid);
		return "redirect:./productlist";
	}

	@RequestMapping("/orderlist")
	public ModelAndView orderlist(Integer pageIndex) {
		ModelAndView mav = new ModelAndView();
		if (pageIndex == null || pageIndex == 0) {
			pageIndex = 1;
		}
		mav.addObject("orderlist", productService.orderList(pageIndex));
		mav.addObject("total", CommonUtils.pageCount(productService.orderCount(), 10));
		mav.setViewName("/admin/order");
		return mav;
	}

	@RequestMapping("/editorder")
	public String editorder(Long oid, Integer status) {
		if (oid != null && status != null) {
			if (status == 0) {
				status = 1;
			} else {
				status = 0;
			}
			productService.editOrder(oid, status);
		}
		return "redirect:./orderlist";
	}

	@ResponseBody
	@RequestMapping("/uploadimgjson")
	public String uploadimgjson(@RequestParam MultipartFile imgFile) {

		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", CommonUtils.getFileRootUrl() + saveFile(imgFile));
		return obj.toJSONString();
	}

	public String saveFile(MultipartFile file) {
		String filePath = "";
		try {
			if (file != null) {
				// 取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (!CommonUtils.isEmptyString(myFileName)) {
					Date now = new Date();
					String savePath = CommonUtils.getTimeFormat(now, "yyyyMMdd") + File.separator;
					String savedFilePath = CommonUtils.getFilePath() + savePath;
					// 定义上传路径
					CommonUtils.checkPath(savedFilePath);
					String extension = myFileName.substring(myFileName.lastIndexOf(".")).toLowerCase();
					String filename = CommonUtils.getTimeFormat(now, "yyyyMMddhhmmssSSS") + "_" + (int) (Math.random() * 100) + extension;
					File localOriginFile = new File(savedFilePath + filename);
					file.transferTo(localOriginFile);
					filePath = (savePath + filename).replace(File.separator, "/");
				}
			}
		} catch (Exception e) {
			return "";
		}
		return filePath;
	}

}
