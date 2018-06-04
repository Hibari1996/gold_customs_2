package com.dkd.gold_customs2.district_maintenance.domain.service.special_regulatory_area.impl;

import com.dkd.gold_customs2.district_maintenance.domain.entity.BaseEntity;
import com.dkd.gold_customs2.district_maintenance.domain.entity.special_regulatory_area.DistrictArea;
import com.dkd.gold_customs2.district_maintenance.domain.entity.special_regulatory_area.DistrictAreaRelation;
import com.dkd.gold_customs2.district_maintenance.domain.repository.special_regulatory_area.DistrictAreaRelationRepository;
import com.dkd.gold_customs2.district_maintenance.domain.repository.special_regulatory_area.DistrictAreaRepository;
import com.dkd.gold_customs2.district_maintenance.domain.service.special_regulatory_area.DistrictAreaRelationService;
import com.dkd.gold_customs2.district_maintenance.domain.vo.DepartmentInfo;
import com.dkd.gold_customs2.district_maintenance.domain.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictAreaRelationServiceImpl implements DistrictAreaRelationService<DistrictAreaRelation,Long> {

    @Autowired
    private DistrictAreaRelationRepository districtAreaRelationRepository;

    @Autowired
    private DistrictAreaRepository districtAreaRepository;
    /**
     * 根据部门创建映射
     *
     * @param districtArea
     * @param dept
     * @return
     */
    @Override
    @Transactional
    public DistrictAreaRelation createDeptRelation(DistrictArea districtArea,
                                                   DepartmentInfo dept,
                                                   UserInfo optInfo) {
        DistrictAreaRelation districtAreaRelation = new DistrictAreaRelation();
        districtAreaRelation.setDelFlag(DistrictAreaRelation.REGULAR_STATUS);
        districtAreaRelation.setOptInfo(optInfo);
        districtAreaRelation.setDistrictArea(districtArea);
        districtAreaRelation.setDepartmentInfo(dept);

        DistrictAreaRelation relation = new DistrictAreaRelation();
        relation.setDistrictArea(districtArea);
        relation.setDepartmentId(dept.getId());
        relation.setOrgPath(dept.getOrgPath());
        relation.setDelFlag(DistrictAreaRelation.REGULAR_STATUS);
        Example<DistrictAreaRelation> example = Example.of(districtAreaRelation);

        if(districtAreaRelationRepository.exists(example)) {
            return null;
        }
        return districtAreaRelationRepository.save(districtAreaRelation);
    }

    /**
     * 根据用户信息创建映射
     *
     * @param districtArea
     * @param userInfo
     * @return
     */
    @Override
    @Transactional
    public DistrictAreaRelation createUserRelation(DistrictArea districtArea,
                                                   UserInfo userInfo,
                                                   UserInfo optInfo) {
        DistrictAreaRelation districtAreaRelation = new DistrictAreaRelation();
        districtAreaRelation.setDelFlag(BaseEntity.REGULAR_STATUS);
        districtAreaRelation.setOptInfo(optInfo);
        districtAreaRelation.setDistrictArea(districtArea);
        districtAreaRelation.setDeptAndUserInfo(userInfo);

        DistrictAreaRelation relation = new DistrictAreaRelation();
        relation.setDistrictArea(districtArea);
        relation.setUserId(userInfo.getId());
        relation.setDepartmentId(userInfo.getDeptId());
        relation.setDelFlag(DistrictAreaRelation.REGULAR_STATUS);
        Example<DistrictAreaRelation> example = Example.of(districtAreaRelation);

        if(districtAreaRelationRepository.exists(example)) {
            return null;
        }

        return districtAreaRelationRepository.save(districtAreaRelation);
    }

    /**
     * 根据用户信息获取用户所属区域
     * @param userId
     * @param deptId
     * @return
     */
    @Override
    public List<DistrictArea> queryDistrictAreaByUserId(String userId,String deptId) {
        return districtAreaRelationRepository.queryDistrictAreaByUser(userId,deptId);
    }

    /**
     * 根据用户信息获取用户所属区域
     * @param code
     * @param deptId
     * @return
     */
    @Override
    public List<DistrictArea> queryDistrictAreaByUserCode(String code,String deptId) {
        return districtAreaRelationRepository.queryDistrictAreaByCode(code,deptId);
    }

    /**
     * 加载三统一部门信息
     *
     * @return
     */
    @Override
    public List<String> loadDepartmentInfo() {
        return districtAreaRelationRepository.queryDepartmentId();
    }

    /**
     * 加载三统一人员信息
     *
     * @return
     */
    @Override
    public List<String> loadUserInfo() {
        return districtAreaRelationRepository.queryUserId();
    }

    /**
     * 科室删除
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean deptListDetele(Long id) {
         districtAreaRelationRepository.deleteById(id);
         return true;
    }



    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public <S extends DistrictAreaRelation> S save(S entity) {
        return districtAreaRelationRepository.save(entity);
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public <S extends DistrictAreaRelation> S update(S entity) {
        return districtAreaRelationRepository.save(entity);
    }

    /**
     * 逻辑删除
     *
     * @param longs
     * @return
     */
    @Override
    @Transactional
    public int delete(Iterable<Long> longs) {
        return districtAreaRelationRepository.deleteByIds(longs);
    }

    /**
     * 删除
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public <S extends DistrictAreaRelation> void remove(S entity) {
        districtAreaRelationRepository.delete(entity);
    }

    /**
     * 查询信息
     *
     * @param id
     * @return
     */
    @Override
    public DistrictAreaRelation getOne(Long id) {
        DistrictAreaRelation districtAreaRelation = new DistrictAreaRelation();
        districtAreaRelation.setId(id);
        Example<DistrictAreaRelation> example = Example.of(districtAreaRelation);
        Optional<DistrictAreaRelation> o = districtAreaRelationRepository.findOne(example);
        return o.isPresent()?o.get():null;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<DistrictAreaRelation> findAll() {
        return districtAreaRelationRepository.findAll();
    }

    /**
     * 查询所有
     *
     * @param entity
     * @return
     */
    @Override
    public <S extends DistrictAreaRelation> List<S> findAll(S entity) {
        Example<S> example = Example.of(entity);
        return districtAreaRelationRepository.findAll(example);
    }

    /**
     * 查询所有
     *
     * @param entity
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<DistrictAreaRelation> findAll(DistrictAreaRelation entity, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return findAll(entity,pageable);
    }

    /**
     * 查询所有
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<DistrictAreaRelation> findAll(Pageable pageable) {
        return districtAreaRelationRepository.findAll(pageable);
    }

    /**
     * 查询所有
     *
     * @param entity
     * @param pageable
     * @return
     */
    @Override
    public Page<DistrictAreaRelation> findAll(DistrictAreaRelation entity, Pageable pageable) {
        Example<DistrictAreaRelation> example = Example.of(entity);
        return districtAreaRelationRepository.findAll(example,pageable);
    }

    /**
     * 查询所有
     *
     * @param example
     * @return
     */
    @Override
    public List<DistrictAreaRelation> findAll(Example<DistrictAreaRelation> example) {
        return districtAreaRelationRepository.findAll(example);
    }

    /**
     * 查询所有
     *
     * @param example
     * @param pageable
     * @return
     */
    @Override
    public Page<DistrictAreaRelation> findAll(Example<DistrictAreaRelation> example, Pageable pageable) {
        return districtAreaRelationRepository.findAll(example,pageable);
    }

    /**
     * 查询所有
     *
     * @param example
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<DistrictAreaRelation> findAll(Example<DistrictAreaRelation> example, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return findAll(example,pageable);
    }

    public DistrictAreaRelationRepository getDistrictAreaRelationRepository() {
        return districtAreaRelationRepository;
    }

    public void setDistrictAreaRelationRepository(DistrictAreaRelationRepository districtAreaRelationRepository) {
        this.districtAreaRelationRepository = districtAreaRelationRepository;
    }
}
