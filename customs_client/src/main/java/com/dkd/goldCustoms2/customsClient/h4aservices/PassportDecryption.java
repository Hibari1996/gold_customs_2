package com.dkd.goldCustoms2.customsClient.h4aservices;

import cn.gov.customs.casp.sdk.h4a.sso.IPassportDecryption;
import org.springframework.stereotype.Component;

@Component
public class PassportDecryption implements IPassportDecryption {

	@Override
	public String DecryptionUrl(String url) {
		//截取全量url的http://头
		String returnUrl = url.replace("http://", "");
		//截取IP地址及端口号及/
		returnUrl = returnUrl.substring(returnUrl.indexOf("/")+1);
		//截取Application Name，如没有Application Name，上一个截取删除。
		returnUrl = returnUrl.substring(returnUrl.indexOf("/"));
		//返回相对路径，因H4A使用forward隐藏路径跳转，需把全路径截取成相对路径。
		return returnUrl;
	}
}
