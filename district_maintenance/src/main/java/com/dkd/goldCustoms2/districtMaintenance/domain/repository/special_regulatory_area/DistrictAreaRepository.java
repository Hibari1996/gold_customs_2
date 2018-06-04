package com.dkd.goldCustoms2.districtMaintenance.domain.repository.special_regulatory_area;

import com.dkd.goldCustoms2.districtMaintenance.domain.entity.special_regulatory_area.DistrictArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/7  14:14
 */
@Repository
@Transactional(readOnly = true)
public interface DistrictAreaRepository extends JpaRepository<DistrictArea,Long> {

    @Modifying
    @Query(value = "update DistrictArea area set area.areaName = :areaName," +
            "area.regionName = :regionName," +
            "area.optCode = :optCode," +
            "area.optDate = :optDate," +
            "area.status = :status " +
            "where area.id = :id")
    int update(@Param("areaName") String areaName, @Param("regionName") String regionName, @Param("status") Integer status,
               @Param("optCode") String optCode, @Param("optDate") Date optDate, @Param("id") Long id);

    @Modifying
    @Query(value = "update DistrictArea area set area.status = :status where area.id in :ids")
    int updateStatus(@Param("ids") Iterable<Long> ids, @Param("status") Integer status);

    @Modifying
    @Query(value = "update DistrictArea area set area.delFlag = 1 where area.id in :ids")
    int deleteByIds(@Param("ids") Iterable<Long> ids);

    @Query(value = "select * from M_DISTRICTAREA m where m.del_flag = '0' and m.area_id in (select t.districtarea_id from M_DISTRICTAREARELATION t where 1 = 1 and t.del_flag = '0' and (t.relation_type = '2' and t.user_id = ?1) or (t.relation_type = '1' and t.department_id = ?2)) ", nativeQuery=true)
    List<DistrictArea> findByUserIdAndDeptId(String userId, String deptId);

}
