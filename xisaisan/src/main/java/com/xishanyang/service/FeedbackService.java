package com.xishanyang.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daoshun.common.QueryResult;
import com.xishanyang.pojo.FeedBack;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class FeedbackService extends BaseService {
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void feedback(String name, String phone, String address, String content) {
		FeedBack feedBack = new FeedBack();
		feedBack.setAddress(address);
		feedBack.setContent(content);
		feedBack.setCreate_time(new Date());
		feedBack.setName(name);
		feedBack.setPhone(phone);
		feedBack.setStatus(0);
		dataDao.addObject(feedBack);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void feedbackReply(Long fid, String reply) {
		FeedBack feedBack = dataDao.getObjectById(FeedBack.class, fid);
		feedBack.setReply(reply);
		dataDao.updateObject(feedBack);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void feedbackShow(Long fid) {
		FeedBack feedBack = dataDao.getObjectById(FeedBack.class, fid);
		if(feedBack.getStatus()==0){
			feedBack.setStatus(1);
		} else {
			feedBack.setStatus(0);
		}
		dataDao.updateObject(feedBack);
	}

	public List<FeedBack> getThreefeedbacks() {
		return (List<FeedBack>) dataDao.pageQueryViaParam("from FeedBack where status = 1 order by create_time desc", 2, 1, null);

	}

	public QueryResult<FeedBack> pagedFeedback(Integer pageIndex) {
		QueryResult<FeedBack> fResult = new QueryResult<FeedBack>(null, 0);
		List<FeedBack> fList = (List<FeedBack>) dataDao.pageQueryViaParam("from FeedBack order by create_time desc", 10, pageIndex, null);
		fResult.setDataList(fList);
		fResult.setTotal(dataDao.getCount("select count(id) from FeedBack"));
		return fResult;
	}
	
	public QueryResult<FeedBack> pagedFeedbackShowed(Integer pageIndex) {
		QueryResult<FeedBack> fResult = new QueryResult<FeedBack>(null, 0);
		List<FeedBack> fList = (List<FeedBack>) dataDao.pageQueryViaParam("from FeedBack where status = 1 order by create_time desc", 10, pageIndex, null);
		fResult.setDataList(fList);
		fResult.setTotal(dataDao.getCount("select count(id) from FeedBack"));
		return fResult;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delFeedback(Long fid) {
		dataDao.deleteObjectById(FeedBack.class, fid);
	}
}
