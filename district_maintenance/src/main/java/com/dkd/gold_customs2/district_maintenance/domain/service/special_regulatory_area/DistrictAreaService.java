package com.dkd.gold_customs2.district_maintenance.domain.service.special_regulatory_area;


import com.dkd.gold_customs2.district_maintenance.domain.service.BaseService;

import java.util.List;

/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/8  12:57
 */
public interface DistrictAreaService<DistrictArea,Long> extends BaseService<DistrictArea,Long> {

    /**
     * 启用
     * @param ids
     * @param status
     * @return
     */
    int using(Iterable<Long> ids, Integer status);

    /**
     *
     * @return
     */
    boolean onlyValidation(DistrictArea districtArea);
    boolean onlyAreaNameValidation(DistrictArea districtArea);
    String onlyAllValidation(DistrictArea districtArea);

    /**
     * 根据人和科室获取相关关区列表
     */
    List<DistrictArea> findByUserIdAndDeptId(String userId, String deptId);

}
