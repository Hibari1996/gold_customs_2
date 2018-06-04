package com.dkd.goldCustoms2.districtMaintenance.domain.service.special_regulatory_area;


import com.dkd.goldCustoms2.districtMaintenance.domain.entity.special_regulatory_area.DistrictArea;
import com.dkd.goldCustoms2.districtMaintenance.domain.service.BaseService;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.DepartmentInfo;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.UserInfo;

import java.util.List;

/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/8  16:55
 */
public interface DistrictAreaRelationService<DistrictAreaRelation,Long> extends BaseService<DistrictAreaRelation,Long> {

    /**
     * 根据部门创建映射
     * @return
     */
    DistrictAreaRelation createDeptRelation(DistrictArea districtArea, DepartmentInfo dept, UserInfo optInfo);

    /**
     * 根据用户信息创建映射
     * @param districtArea
     * @param userInfo
     * @return
     */
    DistrictAreaRelation createUserRelation(DistrictArea districtArea, UserInfo userInfo, UserInfo optInfo);

    /**
     * 根据用户信息获取用户所属区域
     * @param UserId
     * @param deptId
     * @return
     */
    List<DistrictArea> queryDistrictAreaByUserId(String UserId, String deptId);

    /**
     * 根据用户信息获取用户所属区域
     * @param code
     * @param deptId
     * @return
     */
    List<DistrictArea> queryDistrictAreaByUserCode(String code, String deptId);

    /**
     * 加载三统一部门信息
     * @return
     */
    List<String> loadDepartmentInfo();


    /**
     * 加载三统一人员信息
     * @return
     */
    List<String> loadUserInfo();
    /**
     * 科室删除
     * @return
     */
    boolean  deptListDetele(Long id);
}
