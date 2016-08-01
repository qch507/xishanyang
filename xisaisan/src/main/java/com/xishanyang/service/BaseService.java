/**
 * 
 */
package com.xishanyang.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 * @author qiuch
 * 
 */
@Service("baseService")
public class BaseService {

	@Resource
	public DataDao dataDao;
	
	
}
