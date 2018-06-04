package com.dkd.goldCustoms2.customsClient.h4aservices;

import cn.gov.customs.casp.config.ConfigReader;
import cn.gov.customs.casp.sdk.h4a.BeanReaderHelper;
import cn.gov.customs.casp.sdk.h4a.accredit.ws.UserCategory;
import cn.gov.customs.casp.sdk.h4a.entity.FunctionsOfUser;
import cn.gov.customs.casp.sdk.h4a.entity.ObjectsDetail;
import cn.gov.customs.casp.sdk.h4a.enumdefines.AccreditCategory;
import cn.gov.customs.casp.sdk.h4a.enumdefines.DelegationCategories;
import cn.gov.customs.casp.sdk.h4a.enumdefines.FunctionCategories;
import cn.gov.customs.casp.sdk.h4a.enumdefines.ViewCategory;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.ObjectCategory;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.OrganizationCategory;
import cn.gov.customs.casp.sdk.h4a.passport.IAccreditBeanReader;
import cn.gov.customs.casp.sdk.h4a.passport.IOguBeanReader;
import cn.gov.customs.casp.sdk.h4a.sso.IPassportSSO;
import cn.gov.customs.casp.sdk.h4a.sso.passport.Ticket;
import cn.gov.customs.casp.sdk.h4a.sso.passport.util.CookieCenter;
import cn.gov.customs.casp.sdk.h4a.util.H4aDefaultConstants;
import com.dkd.goldCustoms2.customsClient.entity.user.UserInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SamplePassportSSO implements IPassportSSO {


    @Override
    public void Login(Ticket ticket, HttpServletRequest request, HttpServletResponse response, String s) {
        HttpSession session = request.getSession(true);
        StringBuffer userId = new StringBuffer();
        userId.append(ticket.getLn());
        userId.append("&");
        userId.append(ticket.getAbm());
        String objectDetailsLastParam = ConfigReader.getConfigValue("h4a-config.xml", "object_param", "parameter_obj", "getBeanObjectsDetail_lastParam");
        String organizationLastParam = ConfigReader.getConfigValue("h4a-config.xml", "object_param", "parameter_obj", "organizationCategory_lastParam");
        if(null == objectDetailsLastParam){
            objectDetailsLastParam="";
        }
        if(null == organizationLastParam){
            organizationLastParam="";
        }
        IOguBeanReader oguBeanReader = BeanReaderHelper.getIOguBeanReader();
        try{
            //获取指定对象的详细属性数据（对象包括人员、机构、人员组）
            ObjectsDetail[] objectDetails = oguBeanReader.getBeanObjectsDetail(H4aDefaultConstants.DEFAULT_BASE_VIEW, ViewCategory.ViewCode,
                    userId.toString(), ObjectCategory.USER_IDENTITY,"", OrganizationCategory.NONE,objectDetailsLastParam);
            //获取组织结构信息
            ObjectsDetail[] objectDetails1 = oguBeanReader.getBeanObjectsDetail(H4aDefaultConstants.DEFAULT_BASE_VIEW, ViewCategory.ViewCode,
                    objectDetails[0].getParent_guid() , ObjectCategory.ORG_GUID, "", OrganizationCategory.NONE, objectDetailsLastParam);

            if(null != objectDetails && objectDetails.length>0){
                ObjectsDetail objectDeatil = objectDetails[0];
                UserInfo userInfo = new UserInfo();
                userInfo.setId(objectDeatil.getUser_guid());
                userInfo.setCode(objectDeatil.getPerson_id());
                userInfo.setDeptId(objectDeatil.getParent_guid());
                userInfo.setName(objectDeatil.getDisplay_name());
                userInfo.setViewGuid(objectDeatil.getView_guid());
                session.setAttribute("user", userInfo);
                session.setAttribute("organization", objectDeatil);
                session.setAttribute("userIP", request.getRemoteAddr());
                //获取用户的权限信息
                IAccreditBeanReader acdit = BeanReaderHelper.getIAccreditBeanReader();
                FunctionsOfUser[] functionOfUsers = acdit.getBeanFunctionsOfUser(objectDeatil.getAll_path_name(), UserCategory.USER_ALL_PATH_NAME, "",cn.gov.customs.casp.sdk.h4a.accredit.ws.OrganizationCategory.NONE,H4aDefaultConstants.DEFAULT_APPID, AccreditCategory.Code, H4aDefaultConstants.DEFAULT_BASE_VIEW,AccreditCategory.Code, FunctionCategories.All, DelegationCategories.All, "");
                session.setAttribute("functionOfUsers", functionOfUsers);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void LogOut(HttpServletRequest request, HttpServletResponse response) {
        CookieCenter cc = new CookieCenter(request, response);
        HttpSession session = request.getSession(true);
        session.setAttribute("TICKET", null);
        cc.clearCookie();
    }
}
