package com.dkd.gold_customs2.enterprise_information.controller;

import com.dkd.gold_customs2.enterprise_information.service.EnterpriseBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 企业基本信息控制类
 *
 * @author Harry
 * @date 2018/6/5
 */
@RestController(value = "/enterpriseBaseInfo")
public class EnterpriseBaseInfoController {

	@Autowired
	EnterpriseBaseInfo enterpriseBaseInfo;

	@RequestMapping(name = "/getById")
	public Map<String, Object> getById(
			@RequestParam(value = "id", required = false) String id) {
		Map<String, Object> resultMap = enterpriseBaseInfo.getById(id);
		return resultMap;
	}

}
