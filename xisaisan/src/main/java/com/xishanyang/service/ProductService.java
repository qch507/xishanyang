package com.xishanyang.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daoshun.common.CommonUtils;
import com.xishanyang.pojo.OrderInfo;
import com.xishanyang.pojo.ProductInfo;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ProductService extends BaseService {

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addProduce(ProductInfo productInfo) {
		dataDao.addObject(productInfo);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void editProduce(ProductInfo productInfo) {
		dataDao.saveOrUpdateObject(productInfo);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delProduce(Long pid) {
		dataDao.deleteObjectById(ProductInfo.class, pid);
	}

	public List<ProductInfo> productList(Integer pageIndex, String type) {
		if (CommonUtils.isEmptyString(type)) {
			return (List<ProductInfo>) dataDao.pageQueryViaParam("from ProductInfo order by create_time desc", 9, pageIndex, null);
		} else {
			return (List<ProductInfo>) dataDao.pageQueryViaParam("from ProductInfo where type = :type order by create_time desc", 9, pageIndex, new String[] { "type" }, type);
		}
	}

	public long productListCount(String type) {
		if (CommonUtils.isEmptyString(type)) {
			return dataDao.getCount("select count(id) from ProductInfo");
		} else {
			return dataDao.getCount("select count(id) from ProductInfo where type = ?", type);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String addOrder(String name, String phone, String comment, Long pid) {
		String code = "";
		Random ra = new Random();
		String[] letters = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		code = code + letters[ra.nextInt(25)];
		code = code + letters[ra.nextInt(25)];
		code = code + letters[ra.nextInt(25)];
		code = code + CommonUtils.getTimeFormat(new Date(), "yyMMddHHmmss");
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCode(code);
		orderInfo.setComment(comment);
		orderInfo.setName(name);
		orderInfo.setOrder_time(new Date());
		orderInfo.setPhone(phone);
		orderInfo.setProductInfo(dataDao.getObjectById(ProductInfo.class, pid));
		dataDao.addObject(orderInfo);
		return code;
	}

	public String checkstatus(String code) {
		String hql = "select status from OrderInfo where code = :code";
		Integer status = (Integer) dataDao.getFirstObjectViaParam(hql, new String[] { "code" }, code);
		if (status != null) {
			if (status == 0) {
				return "已经受理，我们会尽快联系您！";
			} else {
				return "已经完成，如有问题请联系客服！";
			}
		} else {
			return "不存在，请确认后重试！";
		}
	}

	public List<OrderInfo> orderList(Integer pageIndex) {
		return (List<OrderInfo>) dataDao.pageQueryViaParam("from OrderInfo order by order_time desc", 10, pageIndex, null);
	}

	public long orderCount() {
		return dataDao.getCount("select count(id) from OrderInfo");
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void editOrder(Long oid, Integer status) {
		OrderInfo orderInfo = dataDao.getObjectById(OrderInfo.class, oid);
		orderInfo.setStatus(status);
		dataDao.updateObject(orderInfo);
	}

}
