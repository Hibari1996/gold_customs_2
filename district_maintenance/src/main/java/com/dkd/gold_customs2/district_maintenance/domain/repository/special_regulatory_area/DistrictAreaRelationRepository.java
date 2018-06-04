package com.dkd.gold_customs2.district_maintenance.domain.repository.special_regulatory_area;

import com.dkd.gold_customs2.district_maintenance.domain.entity.special_regulatory_area.DistrictArea;
import com.dkd.gold_customs2.district_maintenance.domain.entity.special_regulatory_area.DistrictAreaRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/7  14:14
 */
@Repository
public interface DistrictAreaRelationRepository extends JpaRepository<DistrictAreaRelation,Long> {

    /**
     * 根据 人员部门id 人员id 查询人员对应所在关区区域
     * @param id
     * @param deptId
     * @return
     */
    @Query("select DISTINCT(relation.districtArea) from DistrictAreaRelation relation " +
            "where relation.delFlag = 0 and relation.districtArea.status = 0 and relation.districtArea.delFlag = 0 " +
            "and(( relation.departmentId = :deptId and relation.relationType = 1 ) " +
            "or ( relation.userId = :id and relation.relationType = 2 ) ) ")
    List<DistrictArea> queryDistrictAreaByUser(@Param("id") String id, @Param("deptId") String deptId);

    /**
     * 根据 人员部门id 人员code 查询人员对应所在关区区域
     * @param code
     * @param deptId
     * @return
     */
    @Query("select DISTINCT(relation.districtArea) from DistrictAreaRelation relation " +
            "where relation.delFlag = 0 and relation.districtArea.status = 0 and relation.districtArea.delFlag = 0 " +
            "and(( relation.departmentId = :deptId and relation.relationType = 1 ) " +
            "or ( relation.userCode = :code and relation.relationType = 2 ) ) ")
    List<DistrictArea> queryDistrictAreaByCode(@Param("code") String code, @Param("deptId") String deptId);

    @Modifying
    @Query("update DistrictAreaRelation relation " +
            "set relation.delFlag = 1 where relation.id in :ids ")
    int deleteByIds(@Param("ids") Iterable<Long> ids);

    /**
     * 查询有效的关联部门ID
     * @return
     */
    @Query("select relation.departmentId from DistrictAreaRelation relation " +
            " where relation.delFlag = 0 and relation.relationType = 1" +
            " and relation.districtArea.delFlag = 0 and relation.districtArea.status = 0 ")
    List<String> queryDepartmentId();


    /**
     * 查询有效的关联人员ID
     * @return
     */
    @Query("select relation.userId from DistrictAreaRelation relation " +
            " where relation.delFlag = 0 and relation.relationType = 2 " +
            " and relation.districtArea.delFlag = 0 and relation.districtArea.status = 0 ")
    List<String> queryUserId();
}
