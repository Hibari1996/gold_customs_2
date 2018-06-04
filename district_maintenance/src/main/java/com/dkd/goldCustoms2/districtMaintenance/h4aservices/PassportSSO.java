package com.dkd.goldCustoms2.districtMaintenance.h4aservices;

import java.util.List;
import java.util.Map;

public interface PassportSSO {

    /**
     * 获取当前人员信息
     * */
    public String queryUserInfo(String id);

    /**
     * 展示用户权限按钮
     * 查询指定人员，在指定应用系统中指定视角下，指定功能下所拥有的业务服务范围
     * */
    public Map<String,Object> queryBusinessScopesByUserFunctions(String userId);

    /**
     * 部门结构根
     * */
    public List<TreeBean> queryOrganizationRootById(String rootId);

    /**
     * 部门结构子节点
     * */
    public List<TreeBean>  queryOrganizationChildrenById(String childId);

    /**
     * 部门人员结构根
     * */
    public List<TreeBean> queryOrgUserRootById(String rootId);

    /**
     * 部门人员结构子节点
     * */
    public List<TreeBean> queryOrgUserChildrenById(String childId);


}
