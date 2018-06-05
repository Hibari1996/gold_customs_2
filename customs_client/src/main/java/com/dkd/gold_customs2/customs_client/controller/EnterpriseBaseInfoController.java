package com.dkd.gold_customs2.customs_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 企业基本信息控制类
 *
 * @author Harry
 * @date 2018/6/5
 */
@RestController(value = "/enterpriseBaseInfo")
public class EnterpriseBaseInfoController {

	@Value("${cst.enterprise.address}")
	private String address;
	@Value("${cst.enterprise.port}")
	private String port;
	@Value("${cst.enterprise.cheaktoken}")
	private String cheaktoken;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(name = "/getById")
	public Object getById(
			@RequestParam(value = "id", required = false) String id) {
		String url = "http://" + address + ":" + port + "/enterpriseBaseInfo/getById";
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		paramMap.set("id", id);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, paramMap, String.class);
		return responseEntity;
	}
}
