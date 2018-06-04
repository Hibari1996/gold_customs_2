package com.dkd.goldCustoms2.districtMaintenance.util;


import com.dkd.goldCustoms2.districtMaintenance.domain.vo.DepartmentInfo;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.MyTreeNode;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.UserInfo;
import com.dkd.goldCustoms2.districtMaintenance.h4aservices.TreeBean;

import java.util.List;

/**
 * @Description:
 * @Author: zhengxudong
 * @Date: 2018/5/10  17:35
 */
public class ConvertUtil {

    private static String KEY =  "|";

    /**
     * viewGuid; //view_guid 视图ID
     * id;//user_guid  登陆的ID
     * name;//display_name  人员的姓名
     * code;//person_id   人员的CODE
     * deptId;//parent_guid   部门的ID
     * deptName;//all_path_name  天津海关\天津物流园海关\张妍
     * @param treeBean
     * @return
     */
    public static UserInfo convertUserInfo(TreeBean treeBean) {
        if(treeBean != null) {
            String id = treeBean.getId();
            String name = treeBean.getName();
            String code = treeBean.getCode();
            String deptId = treeBean.getParent();
            String deptName = treeBean.getPath();
            return new UserInfo(id, name, code, deptId, deptName);
        }
        return null;
    }

    public static List<UserInfo> convertUserInfos(List<TreeBean> treeBeans, List<UserInfo> userInfos){
        treeBeans.stream().forEach(treeBean -> {
            userInfos.add(ConvertUtil.convertUserInfo(treeBean));
        });
        return userInfos;
    }

    public static DepartmentInfo convertDepartmentInfo(TreeBean treeBean) {
        if( treeBean != null ){
            String id = treeBean.getId();//org_guid
            String name = treeBean.getName();//display_name
            String parentId = treeBean.getParent();//parent_guid 上级部门ID
            String OrgPath = treeBean.getPath();//all_path_name
            return new DepartmentInfo(id,name,parentId,OrgPath);
        }
        return null;
    }

    public static List<DepartmentInfo> convertDepartmentInfos(List<TreeBean> treeBeans,
                                                              List<DepartmentInfo> departmentInfos) {
        treeBeans.stream().forEach(treeBean -> {
            departmentInfos.add(ConvertUtil.convertDepartmentInfo(treeBean));
        });
        return departmentInfos;
    }

    public static MyTreeNode convertMyTreeNode(TreeBean treeBean,List<String> keys){
        MyTreeNode node = new MyTreeNode();
        String infoId = treeBean.getId()+KEY+treeBean.getName();
        try{
            //压入工具类
            LoadTreeBean.put(infoId,treeBean);
            //0部门 1人员
            if(treeBean.getType()>0){
                String id = treeBean.getId();
                String name = treeBean.getName();
                String code = treeBean.getCode();
                String deptName = treeBean.getPath();
                node.setInfoId(infoId);
                node.setId(id);
                node.setName(name);
                node.setChecked(keys.contains(infoId));
            }else{
                String id = treeBean.getId();
                String name = treeBean.getName();//display_name
                String parentId = treeBean.getParent();//parent_guid 上级部门ID
                String OrgPath = treeBean.getPath();//all_path_name
                node.setInfoId(infoId);
                node.setId(id);
                node.setName(name);
                node.setIsParent("true");
                node.setChecked(keys.contains(infoId));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }


    public static List<MyTreeNode> convertMyTreeNodes(
            List<TreeBean> treeBeans,List<MyTreeNode> myTreeNodes,List<String> keys) {
        treeBeans.stream().forEach(treeBean ->  myTreeNodes.add(ConvertUtil.convertMyTreeNode(treeBean,keys)));
        return myTreeNodes;
    }
}
