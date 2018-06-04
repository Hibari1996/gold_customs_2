package com.dkd.gold_customs2.district_maintenance.domain.service.special_regulatory_area.impl;

import com.dkd.gold_customs2.district_maintenance.domain.entity.special_regulatory_area.DistrictArea;
import com.dkd.gold_customs2.district_maintenance.domain.repository.special_regulatory_area.DistrictAreaRepository;
import com.dkd.gold_customs2.district_maintenance.domain.service.special_regulatory_area.DistrictAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/8  12:58
 */
@Service
public class DistrictAreaServiceImpl implements DistrictAreaService<DistrictArea,Long> {

    @Autowired
    private DistrictAreaRepository districtAreaRepository;

    /**
     * 启用
     *
     * @param ids
     * @param status
     * @return
     */
    @Override
    @Transactional
    public int using(Iterable<Long> ids, Integer status) {
        return districtAreaRepository.updateStatus(ids,status);
    }

    /**
     * @param districtArea
     * @return
     */
    @Override
    public boolean onlyValidation(DistrictArea districtArea) {
        if(districtArea.getAreaCode() != null && !StringUtils.isEmpty(districtArea.getAreaCode())
                && districtArea.getRegionCode() != null && !StringUtils.isEmpty(districtArea.getRegionCode())){
            //忽略其他属性
            DistrictArea area = new DistrictArea();
            area.setAreaCode(districtArea.getAreaCode());
            area.setRegionCode(districtArea.getRegionCode());
            area.setStatus(DistrictArea.USED);
            Example<DistrictArea> example = Example.of(area);
            return districtAreaRepository.exists(example);
        }
        return true;
    }

    @Override
    public boolean onlyAreaNameValidation(DistrictArea districtArea) {
        if(districtArea.getAreaName() != null && !StringUtils.isEmpty(districtArea.getAreaName())){
            DistrictArea re = new DistrictArea();
            re.setAreaName(districtArea.getAreaName());
            Example<DistrictArea> example = Example.of(re);
            return  districtAreaRepository.exists(example);
        }
        return true;
    }

    @Override
    public String onlyAllValidation(DistrictArea districtArea) {
        boolean flag=false;
        if(districtArea.getAreaName() != null && !StringUtils.isEmpty(districtArea.getAreaName())){
            DistrictArea re = new DistrictArea();
            re.setAreaName(districtArea.getAreaName());
            Example<DistrictArea> example = Example.of(re);
            flag  =  districtAreaRepository.exists(example);
        }
        if(flag == true){
            return "area_save_03";
        }
        if(districtArea.getAreaCode() != null && !StringUtils.isEmpty(districtArea.getAreaCode())
                && districtArea.getRegionCode() != null && !StringUtils.isEmpty(districtArea.getRegionCode())){
            //忽略其他属性
            DistrictArea area = new DistrictArea();
            area.setAreaCode(districtArea.getAreaCode());
            area.setRegionCode(districtArea.getRegionCode());
            area.setStatus(DistrictArea.USED);
            Example<DistrictArea> example = Example.of(area);
            flag = districtAreaRepository.exists(example);
        }
        if(flag == true){
            return "area_save_02";
        }
        return "area_save_01";
    }

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public <S extends DistrictArea> S save(S entity) {
        if (entity.getId() != null) {
            return null;
        }
        return districtAreaRepository.save(entity);
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public DistrictArea update(DistrictArea entity) {
        int n = districtAreaRepository.update(entity.getAreaName(),entity.getRegionName(),entity.getStatus(),
                entity.getOptCode(),entity.getOptDate(),entity.getId());
        return  districtAreaRepository.findById(entity.getId()).get() ;
    }

    /**
     * 逻辑删除
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int delete(Iterable<Long> ids) {
        return districtAreaRepository.deleteByIds(ids);
    }

    /**
     * 删除
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public <S extends DistrictArea> void remove(S entity) {
        districtAreaRepository.delete(entity);
    }

    /**
     * 查询信息
     *
     * @param id
     * @return
     */
    @Override
    public DistrictArea getOne(Long id) {
        DistrictArea area = new DistrictArea();
        area.setId(id);
        Example<DistrictArea> example = Example.of(area);
        Optional<DistrictArea> o = districtAreaRepository.findOne(example);
        return  o.isPresent()?o.get():null;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public  List<DistrictArea> findAll() {
        return districtAreaRepository.findAll();
    }

    /**
     * 查询所有
     *
     * @param entity
     * @return
     */
    @Override
    public <S extends DistrictArea> List<S> findAll(S entity) {
        Example<S> example = Example.of(entity);
        return districtAreaRepository.findAll(example);
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
    public Page<DistrictArea>  findAll(DistrictArea entity, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return findAll(entity,pageable);
    }

    /**
     * 查询所有
     * @param pageable
     * @return
     */
    @Override
    public Page<DistrictArea> findAll(Pageable pageable) {
        return districtAreaRepository.findAll(pageable);
    }

    /**
     * 查询所有
     * @param entity
     * @param pageable
     * @return
     */
    @Override
    public Page<DistrictArea> findAll(DistrictArea entity, Pageable pageable) {
        Example<DistrictArea> example = Example.of(entity);
        return districtAreaRepository.findAll(example,pageable);
    }

    /**
     * 查询所有
     *
     * @param example
     * @return
     */
    @Override
    public List<DistrictArea> findAll(Example<DistrictArea> example) {
        return districtAreaRepository.findAll(example);
    }

    /**
     * 查询所有
     * @param example
     * @param pageable
     * @return
     */
    @Override
    public Page<DistrictArea> findAll(Example<DistrictArea> example, Pageable pageable) {
        return districtAreaRepository.findAll(example,pageable);
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
    public Page<DistrictArea> findAll(Example<DistrictArea> example, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return findAll(example,pageable);
    }

    public DistrictAreaRepository getDistrictAreaRepository() {
        return districtAreaRepository;
    }

    public void setDistrictAreaRepository(DistrictAreaRepository districtAreaRepository) {
        this.districtAreaRepository = districtAreaRepository;
    }

    public List<DistrictArea> findByUserIdAndDeptId(String userId, String deptId){
        return districtAreaRepository.findByUserIdAndDeptId(userId,deptId);
    }

}
