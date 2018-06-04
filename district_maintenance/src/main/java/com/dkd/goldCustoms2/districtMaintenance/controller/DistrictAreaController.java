package com.dkd.goldCustoms2.districtMaintenance.controller;

import com.dkd.goldCustoms2.districtMaintenance.domain.entity.BaseEntity;
import com.dkd.goldCustoms2.districtMaintenance.domain.entity.special_regulatory_area.DistrictArea;
import com.dkd.goldCustoms2.districtMaintenance.domain.service.special_regulatory_area.DistrictAreaService;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DistrictAreaController {

    @Autowired
    private DistrictAreaService districtAreaService;

    public DistrictArea setOptInfo(DistrictArea districtArea){
        UserInfo u = new UserInfo();
        u.setId("admin");
        u.setName("adminName");
        u.setDeptId("department_id");
        u.setCode("admin");
        u.setDeptName("department");
        districtArea.setOptInfo(u);
        districtArea.setDelFlag(BaseEntity.REGULAR_STATUS);
        return districtArea;
    }

    @RequestMapping("/core/area/save")
    public ResponseEntity<DistrictArea> save(@RequestBody DistrictArea districtArea,
                                             BindingResult result){
        if (!(result.hasErrors() && result.getErrorCount() > 0) ) {
            try{
                if(districtAreaService.onlyValidation(districtArea)){
                    return new ResponseEntity("关区区域组合重复",HttpStatus.OK);
                }
                if(districtAreaService.onlyAreaNameValidation(districtArea)){
                    return new ResponseEntity("区域名称重复",HttpStatus.OK);
                }
                return new ResponseEntity(districtAreaService.save(setOptInfo(districtArea)),HttpStatus.OK);

            }catch (Exception e) {
                return new ResponseEntity(e,HttpStatus.OK);
            }
        }
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @RequestMapping("/core/area/update")
    @ResponseBody
    public ResponseEntity<DistrictArea> update(@RequestBody DistrictArea districtArea,
                                               BindingResult result){
        if (!(result.hasErrors() && result.getErrorCount() > 0) ) {
            try{
                    return new ResponseEntity(
                            districtAreaService.update(setOptInfo(districtArea)),HttpStatus.OK);
            }catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity(e,HttpStatus.OK);
            }
        }
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @RequestMapping("/core/area/edit/{id}")
    @ResponseBody
    public ResponseEntity<DistrictArea> edit(@PathVariable("id") Long id){
        try{
            DistrictArea d = (DistrictArea)districtAreaService.getOne(id);
            return new ResponseEntity(d,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.OK);
        }
    }

    @RequestMapping("/core/area/valid")
    @ResponseBody
    public ResponseEntity<DistrictArea> valid(@RequestBody DistrictArea districtArea){
        try{
            String flag = districtAreaService.onlyAllValidation(districtArea);
            return new ResponseEntity(flag,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.OK);
        }
    }

    @RequestMapping(value="/core/relation/area/list")
    @ResponseBody
    public ResponseEntity<DistrictArea> arealist(){
        return new ResponseEntity(districtAreaService.findAll(),HttpStatus.OK);
    }

    @RequestMapping(value="/core/area/list")
    @ResponseBody
    public ResponseEntity<DistrictArea> list(
            @RequestBody DistrictArea districtArea,
            @RequestParam(value="page",required=false) Integer page,
            @RequestParam(value="size",required=false) Integer size){
        try{
            if (StringUtils.isEmpty(districtArea.getAreaCode()) ) {
                districtArea.setAreaCode(null);
            }
            if (StringUtils.isEmpty(districtArea.getAreaName())) {
                districtArea.setAreaName(null);
            }
            if (StringUtils.isEmpty(districtArea.getRegionCode())) {
                districtArea.setRegionCode(null);
            }
            if (StringUtils.isEmpty(districtArea.getRegionName())) {
                districtArea.setRegionName(null);
            }
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("areaCode",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("areaName",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("regionCode",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("regionName",ExampleMatcher.GenericPropertyMatchers.contains());
            Example<DistrictArea> example = Example.of(districtArea,matcher);
            if(page != null && size != null ){
                return new ResponseEntity(districtAreaService.findAll(example,page,size),HttpStatus.OK);
            }else{
                return new ResponseEntity(districtAreaService.findAll(example),HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.OK);
        }
    }

    @RequestMapping("/core/area/findArea/{userId}/{deptId}")
    @ResponseBody
    public ResponseEntity<DistrictArea> findByUserIdAndDeptId(@PathVariable("userId") String userId, @PathVariable("deptId") String deptId){
        List<DistrictArea> list = districtAreaService.findByUserIdAndDeptId(userId,deptId);
        return new ResponseEntity(list,HttpStatus.OK);

    }

}
