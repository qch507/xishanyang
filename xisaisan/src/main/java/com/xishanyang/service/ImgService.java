package com.xishanyang.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xishanyang.pojo.ImgInfo;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ImgService extends BaseService {

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void uploadImg(ImgInfo imgInfo) {
		dataDao.addObject(imgInfo);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delTheme(String theme) {
		dataDao.deleteObjectsViaParam("delete from ImgInfo where theme = :theme", new String[] { "theme" }, theme);
	}

	public List<ImgInfo> themeList(Integer pageIndex) {
		String hql = "from ImgInfo group by theme";
		String hqllist = "select url from ImgInfo where theme = :theme";
		String[] hqlParam = new String[] { "theme" };
		List<ImgInfo> imgList = (List<ImgInfo>) dataDao.pageQueryViaParam(hql, 9, pageIndex, null);
		for (ImgInfo imgInfo : imgList) {
			imgInfo.setImgs((List<String>) dataDao.getObjectsViaParam(hqllist, hqlParam, imgInfo.getTheme()));
		}

		return imgList;
	}

	public long themecount() {
		return dataDao.getCount("select count(distinct theme ) from ImgInfo");
		// return 1l;
	}
}
