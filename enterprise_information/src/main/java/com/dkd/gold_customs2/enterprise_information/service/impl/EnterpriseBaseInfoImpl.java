package com.dkd.gold_customs2.enterprise_information.service.impl;

import cn.gov.customs.tj.CallH2010WS;
import com.dkd.gold_customs2.enterprise_information.service.EnterpriseBaseInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 企业基本信息业务层实现类
 *
 * @author Harry
 * @date 2018/6/5
 */
@Service
public class EnterpriseBaseInfoImpl implements EnterpriseBaseInfo {
	@Override
	public Map<String, Object> getById(String id) {
		CallH2010WS callH2010WS = new CallH2010WS();
		Map<String, Object> returnMap = callH2010WS.getReturnMap(id);
		return returnMap;
	}
}
