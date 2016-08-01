package com.xishanyang.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daoshun.common.QueryResult;
import com.xishanyang.pojo.NewsInfo;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class NewsService extends BaseService {

	public List<NewsInfo> getNotice() {

		return (List<NewsInfo>) dataDao.getObjectsViaParam("from NewsInfo where type = 1 order by create_time desc", null);
	}

	public List<NewsInfo> getThreeNews() {

		return (List<NewsInfo>) dataDao.pageQueryViaParam("from NewsInfo where type = 0 order by create_time desc", 3, 1, null);
	}

	public QueryResult<NewsInfo> pagedNews(Integer pageIndex) {
		QueryResult<NewsInfo> nResult = new QueryResult<>(null, 0);
		List<NewsInfo> newsInfos = (List<NewsInfo>) dataDao.pageQueryViaParam("from NewsInfo order by create_time desc", 10, pageIndex, null);
		nResult.setDataList(newsInfos);
		nResult.setTotal(dataDao.getCount("select count(id) from NewsInfo"));
		return nResult;
	}

	public QueryResult<NewsInfo> pagedAllNews(Integer pageIndex) {
		QueryResult<NewsInfo> nResult = new QueryResult<NewsInfo>(null, 0);
		List<NewsInfo> newsInfos = (List<NewsInfo>) dataDao.pageQueryViaParam("from NewsInfo order by create_time desc", 10, pageIndex, null);
		nResult.setDataList(newsInfos);
		nResult.setTotal(dataDao.getCount("select count(id) from NewsInfo"));
		return nResult;
	}

	public NewsInfo getNews(long newsid) {
		return dataDao.getObjectById(NewsInfo.class, newsid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delNews(long newsid) {
		dataDao.deleteObjectById(NewsInfo.class, newsid);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void editNews(NewsInfo newsInfo) {
		dataDao.saveOrUpdateObject(newsInfo);
	}

}
