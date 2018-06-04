package com.dkd.goldCustoms2.districtMaintenance.h4aservices;

import cn.gov.customs.casp.config.ConfigReader;
import cn.gov.customs.casp.sdk.h4a.BeanReaderHelper;
import cn.gov.customs.casp.sdk.h4a.accredit.ws.UserCategory;
import cn.gov.customs.casp.sdk.h4a.entity.FunctionsInRoles;
import cn.gov.customs.casp.sdk.h4a.entity.OrganizationChildren;
import cn.gov.customs.casp.sdk.h4a.entity.RootDepartment;
import cn.gov.customs.casp.sdk.h4a.enumdefines.*;
import cn.gov.customs.casp.sdk.h4a.ogu.ws.OrganizationCategory;
import cn.gov.customs.casp.sdk.h4a.passport.IAccreditBeanReader;
import cn.gov.customs.casp.sdk.h4a.passport.IOguBeanReader;
import cn.gov.customs.casp.sdk.h4a.util.H4aDefaultConstants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PassportSSOImp implements PassportSSO {


    @Override
    public String queryUserInfo(String id) {
        return null;
    }

    @Override
    public Map<String,Object> queryBusinessScopesByUserFunctions(String userId) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Permission> permissionList = new ArrayList<Permission>();
        try{
            // 获取用户的权限信息
            IAccreditBeanReader beanReader = BeanReaderHelper.getIAccreditBeanReader();
            FunctionsInRoles[] functionsInRole = beanReader.getBeanFunctionsInRoles(H4aDefaultConstants.DEFAULT_APPID,AccreditCategory.Code,
                    H4aDefaultConstants.DEFAULT_BASE_VIEW,AccreditCategory.Code,H4aDefaultConstants.DEFAULT_APPLICATION_NAME,AccreditCategory.Code,"");
            for(int i=0;i<functionsInRole.length;i++){
                Permission permission = new Permission();
                permission.setAppId(functionsInRole[i].getApp_id());
                permission.setClassify(functionsInRole[i].getClassify());
                permission.setFuncId(functionsInRole[i].getFunc_id());
                permission.setFuncCode(functionsInRole[i].getCode_name());
                permission.setFuncName(functionsInRole[i].getFunc_name());
                permission.setParentId(functionsInRole[i].getParent_id());
                permission.setRoleCategory(functionsInRole[i].getRole_category());
                permission.setRoleValue(functionsInRole[i].getRole_value());
                permissionList.add(permission);
            }
            String childJson = GoodIn2JsonForPerssion(permissionList);
            map.put("permission",childJson);
            map.put("list",permissionList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<TreeBean> queryOrganizationRootById(String rootId) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<TreeBean> treeBeanList = new ArrayList<TreeBean>();
        String rootJson = "";
        try {
            IOguBeanReader oguBeanReader = BeanReaderHelper.getIOguBeanReader();
            //获取根机构
            RootDepartment[] returnObjects = oguBeanReader.getBeanRootDepartment(H4aDefaultConstants.DEFAULT_BASE_VIEW,
                    ViewCategory.ViewCode, "OBJ_NAME,GLOBLA_SORT");

            for(int i=0;i<returnObjects.length;i++){
                if(returnObjects[i].getObjectclass().equals("ORGANIZATIONS")){
                    TreeBean treeBean = new TreeBean();
                    treeBean.setCode("");
                    treeBean.setName(returnObjects[i].getDisplay_name());
                    treeBean.setId(returnObjects[i].getOrg_guid());
                    treeBean.setType(0);
                    treeBeanList.add(treeBean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return treeBeanList;
    }

    @Override
    public List<TreeBean>  queryOrganizationChildrenById(String childId) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<TreeBean> treeBeanList = new ArrayList<TreeBean>();
        String rootJson = "";
        try{
            IOguBeanReader oguBeanReader = BeanReaderHelper.getIOguBeanReader();
            OrganizationChildren[] object = oguBeanReader.getBeanOrganizationChildren(H4aDefaultConstants.DEFAULT_BASE_VIEW, ViewCategory.ViewCode,
                    childId, OrganizationCategory.ORG_GUID, ListObjectCategories.All, ObjectStatusCategories.All,1,"","","",0,"");

            for(int i=0;i<object.length;i++){
                if(object[i].getObjectclass().equals("ORGANIZATIONS")){
                    TreeBean treeBean = new TreeBean();
                    treeBean.setCode(object[i].getPerson_id());
                    treeBean.setName(object[i].getDisplay_name());
                    treeBean.setPath(object[i].getAll_path_name());
                    treeBean.setParent(object[i].getParent_guid());
                    treeBean.setId(object[i].getOrg_guid());
                    treeBean.setType(0);
                    if(childId != null && !childId.equals(object[i].getOrg_guid())){
                        treeBeanList.add(treeBean);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return treeBeanList;
    }

    @Override
    public List<TreeBean> queryOrgUserRootById(String rootId) {
        return null;
    }

    @Override
    public List<TreeBean> queryOrgUserChildrenById(String childId) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<TreeBean> treeBeanList = new ArrayList<TreeBean>();
        String rootJson = "";
        try{
            IOguBeanReader oguBeanReader = BeanReaderHelper.getIOguBeanReader();
            OrganizationChildren[] object = oguBeanReader.getBeanOrganizationChildren(H4aDefaultConstants.DEFAULT_BASE_VIEW, ViewCategory.ViewCode,
                    childId, OrganizationCategory.ORG_GUID, ListObjectCategories.All, ObjectStatusCategories.All,1,"","","",0,"");


            for(int i=0;i<object.length;i++){
                TreeBean treeBean = new TreeBean();
                treeBean.setCode(object[i].getPerson_id());
                treeBean.setName(object[i].getDisplay_name());
                treeBean.setPath(object[i].getAll_path_name());
                treeBean.setParent(object[i].getParent_guid());
                if(object[i].getObjectclass().equals("USERS")){
                    treeBean.setId(object[i].getUser_guid());
                    treeBean.setType(1);
                }else{
                    treeBean.setId(object[i].getOrg_guid());
                    treeBean.setType(0);
                }
                if(childId != null && !childId.equals(object[i].getOrg_guid())){
                    treeBeanList.add(treeBean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return treeBeanList;
    }



    /**
     * 将json数组解析出来,生成自定义数据的数组
     * @return 自定义信息的数据
     * @throws JSONException
     */
    public String GoodIn2Json(List<TreeBean> items) throws JSONException {
        if (items == null) return "";
        JSONArray array = new JSONArray();
        JSONObject jsonObject = null;
        TreeBean info = null;
        for (int i = 0; i < items.size(); i++) {
            info = items.get(i);
            jsonObject = new JSONObject();
            jsonObject.put("id", info.getId());
            jsonObject.put("name", info.getName());
            jsonObject.put("code", info.getCode());
            jsonObject.put("path", info.getPath());
            jsonObject.put("parent", info.getParent());
            array.add(jsonObject);
        }
        return array.toString();
    }

    /**
     * 将json数组解析出来,生成自定义数据的数组
     * @return 自定义信息的数据
     * @throws JSONException
     */
    public String GoodIn2JsonForPerssion(List<Permission> items) throws JSONException {
        if (items == null) return "";
        JSONArray array = new JSONArray();
        JSONObject jsonObject = null;
        Permission info = null;
        for (int i = 0; i < items.size(); i++) {
            info = items.get(i);
            jsonObject = new JSONObject();
            jsonObject.put("roleValue", info.getRoleValue());
            jsonObject.put("appId", info.getAppId());
            jsonObject.put("classify", info.getClassify());
            jsonObject.put("funcId", info.getFuncId());
            jsonObject.put("funcCode", info.getFuncCode());
            jsonObject.put("funcName", info.getFuncName());
            jsonObject.put("roleCategory", info.getRoleCategory());
            jsonObject.put("parent", info.getParentId());
            array.add(jsonObject);
        }
        return array.toString();
    }

}
