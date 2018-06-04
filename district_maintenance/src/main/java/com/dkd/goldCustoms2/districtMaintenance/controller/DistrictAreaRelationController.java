package com.dkd.goldCustoms2.districtMaintenance.controller;

import com.dkd.goldCustoms2.districtMaintenance.domain.entity.special_regulatory_area.DistrictArea;
import com.dkd.goldCustoms2.districtMaintenance.domain.entity.special_regulatory_area.DistrictAreaRelation;
import com.dkd.goldCustoms2.districtMaintenance.domain.service.special_regulatory_area.DistrictAreaRelationService;
import com.dkd.goldCustoms2.districtMaintenance.domain.service.special_regulatory_area.DistrictAreaService;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.DepartmentInfo;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.MyTreeNode;
import com.dkd.goldCustoms2.districtMaintenance.domain.vo.UserInfo;
import com.dkd.goldCustoms2.districtMaintenance.h4aservices.PassportSSO;
import com.dkd.goldCustoms2.districtMaintenance.util.ConvertUtil;
import com.dkd.goldCustoms2.districtMaintenance.util.LoadTreeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DistrictAreaRelationController {

    @Autowired
    private DistrictAreaRelationService<DistrictAreaRelation,Long> districtAreaRelationService;

    @Autowired
    private DistrictAreaService<DistrictArea,Long> districtAreaService;

    @Autowired
    private PassportSSO passportSSO;

    @RequestMapping("/core/relation/area/user/query")
    public ResponseEntity areaQueryByUser(@RequestParam(value="userId",required=true) String userId,
                                          @RequestParam(value="deptId",required=true) String deptId){
        try{
            return new ResponseEntity(
                    districtAreaRelationService.queryDistrictAreaByUserId(userId,deptId),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e,HttpStatus.OK);
        }
    }

    @RequestMapping("/core/relation/area/code/query")
    public ResponseEntity areaQueryByCode(@RequestParam(value="code",required=true) String code,
                                          @RequestParam(value="deptId",required=true) String deptId){
        try{
            return new ResponseEntity(
                    districtAreaRelationService.queryDistrictAreaByUserCode(code,deptId),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e,HttpStatus.OK);
        }
    }

    @RequestMapping("/core/relation/dept/save")
    public ResponseEntity saveDeptRelation(
            @RequestParam(value="districtAreaId",required=true) Long districtAreaId,
            @RequestParam(value="deptIds",required=true) String StrdeptIds) {
        try{
            UserInfo u = new UserInfo();
            u.setName("张三");
            u.setId("admin");
            u.setDeptId("admin");
            u.setCode("admin");
            u.setDeptName("department");
            DistrictArea area = districtAreaService.getOne(districtAreaId);
            String[] deptIds = StrdeptIds.split(",");
            List<DistrictAreaRelation> list = new ArrayList<DistrictAreaRelation>();
            Arrays.stream(deptIds).forEach(id ->{
                DepartmentInfo departmentInfo = LoadTreeBean.getDepartmentInfo(id);
                if (departmentInfo != null ){
                    if(departmentInfo.getOrgPath() != null){
                        DistrictAreaRelation relation = districtAreaRelationService.createDeptRelation(area,departmentInfo,u);
                        if (relation != null) list.add(relation);
                    }
                    }
            });
            return new ResponseEntity(list.size()>0?list:"[]",HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e,HttpStatus.OK);
        }
    }

    @RequestMapping("/core/relation/user/save")
    public ResponseEntity saveUserRelation(@RequestParam(value="districtAreaId",required=true) Long districtAreaId,
                                           @RequestParam(value="userIds",required=true) String[] userIds) {
        try{
            UserInfo u = new UserInfo();
            u.setId("admin");
            u.setName("admin");
            u.setDeptId("admin");
            u.setCode("department");
            u.setDeptName("department");
            DistrictArea area = districtAreaService.getOne(districtAreaId);
            List<DistrictAreaRelation> list = new ArrayList<DistrictAreaRelation>();
            Arrays.stream(userIds).forEach(id -> {
                UserInfo userInfo = LoadTreeBean.getUserInfo(id);
                if (userInfo != null) {
                    DistrictAreaRelation relation = districtAreaRelationService.createUserRelation(area,userInfo,u);
                    if (relation != null) list.add(relation);
                }
            });
            return new ResponseEntity(list.size()>0?list:"[]",HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e,HttpStatus.OK);
        }
    }



    @RequestMapping("/core/relation/deptTree")
    public ResponseEntity deptTree(@RequestParam(value="id",required=false) String deptId) {
        List<String> keys = districtAreaRelationService.loadDepartmentInfo();
        //没有父节点ID获取root信息，默认天津海关视角
        List<MyTreeNode> myTreeNodes =
                ConvertUtil.convertMyTreeNodes(null == deptId || StringUtils.isEmpty(deptId) ?
                        passportSSO.queryOrganizationRootById(null) :
                        passportSSO.queryOrganizationChildrenById(deptId)
                        , new ArrayList<MyTreeNode>(), keys);
        return new ResponseEntity(myTreeNodes,HttpStatus.OK);
    }


    @RequestMapping("/core/relation/user/query")
    public ResponseEntity h4aUserQuery(@RequestParam(value="id",required=false) String deptId) {
        List<String> keys = districtAreaRelationService.loadUserInfo();
        if( deptId == null){
            //没有父节点ID获取root信息，默认天津海关视角
            List<MyTreeNode> myTreeNodes =
                    ConvertUtil.convertMyTreeNodes(passportSSO.queryOrganizationRootById(null)
                            , new ArrayList<MyTreeNode>(), keys);
            return new ResponseEntity(myTreeNodes,HttpStatus.OK);
        }
        if( StringUtils.isEmpty(deptId) ){
            return new ResponseEntity("[]",HttpStatus.OK);
        }
        List<MyTreeNode> myTreeNodes =
                ConvertUtil.convertMyTreeNodes(passportSSO.queryOrgUserChildrenById(deptId),
                        new ArrayList<MyTreeNode>(),keys);
        return new ResponseEntity(myTreeNodes,HttpStatus.OK);
    }

    /**
     * 科室关联信息展示
     * @param districtAreaId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/core/relation/deptList")
    public ResponseEntity deptList(@RequestParam(value="districtAreaId",required=true) Long districtAreaId,
                                   @RequestParam(value="deptName",required=false) String deptName,
                                   @RequestParam(value="page",required=false) Integer page,
                                   @RequestParam(value="size",required=false) Integer size) {
        try {
            DistrictAreaRelation districtAreaRelation = new DistrictAreaRelation();
            if(!StringUtils.isEmpty(deptName)){
                districtAreaRelation.setOrgPath(deptName);
            }
            DistrictArea districtArea = new DistrictArea();
            districtArea.setId(districtAreaId);
            districtAreaRelation.setDistrictArea(districtArea);
            districtAreaRelation.setDelFlag(DistrictAreaRelation.REGULAR_STATUS);
            districtAreaRelation.setRelationType(DistrictAreaRelation.DEPT_RELATION);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("orgPath",ExampleMatcher.GenericPropertyMatchers.contains());
            Example<DistrictAreaRelation> example = Example.of(districtAreaRelation,matcher);

            if(page != null && size != null ) {
                return new ResponseEntity(
                        districtAreaRelationService.findAll(example,page,size), HttpStatus.OK);
            }else {
                List<DistrictAreaRelation> list = districtAreaRelationService.findAll(example);
                return new ResponseEntity(list.size()>0?list:"[]", HttpStatus.OK);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }
    }

    /**
     * 人员关联信息展示
     * @param districtAreaId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/core/relation/userList")
    public ResponseEntity userList(@RequestParam(value="districtAreaId",required=true) Long districtAreaId,
                                   @RequestParam(value="userName",required=false) String userName,
                                   @RequestParam(value="userCode",required=false) String userCode,
                                   @RequestParam(value="page",required=false) Integer page,
                                   @RequestParam(value="size",required=false) Integer size) {
        try {
            DistrictAreaRelation districtAreaRelation = new DistrictAreaRelation();
            if(!StringUtils.isEmpty(userName)){
                districtAreaRelation.setUserName(userName);
            }
            if(!StringUtils.isEmpty(userCode)){
                districtAreaRelation.setUserCode(userCode);
            }
            DistrictArea districtArea = new DistrictArea();
            districtArea.setId(districtAreaId);
            districtAreaRelation.setDistrictArea(districtArea);
            districtAreaRelation.setDelFlag(DistrictAreaRelation.REGULAR_STATUS);
            districtAreaRelation.setRelationType(DistrictAreaRelation.USER_RELATION);
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("userName",ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("userCode",ExampleMatcher.GenericPropertyMatchers.contains());
            Example<DistrictAreaRelation> example = Example.of(districtAreaRelation,matcher);
            if(page != null && size != null ) {
                return new ResponseEntity(
                        districtAreaRelationService.findAll(example,page,size), HttpStatus.OK);
            }else{
                List <DistrictAreaRelation> list = districtAreaRelationService.findAll(districtAreaRelation);
                return new ResponseEntity(
                        list.size() > 0?list:"[]", HttpStatus.OK);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }
    }
   /*科室信息删除
   *
   * */
    @RequestMapping("/core/relation/deptList/delete/{id}")
    public ResponseEntity deptListDetele(@PathVariable("id") Long id){
        boolean isDelete =districtAreaRelationService.deptListDetele(id);
        return   new ResponseEntity(isDelete, HttpStatus.OK);
    }
    /*人员信息删除
     *
     * */
    @RequestMapping("/core/relation/user/delete/{id}")
    public ResponseEntity userDetele(@PathVariable("id") Long id){
        boolean isDelete =districtAreaRelationService.deptListDetele(id);
        return   new ResponseEntity(isDelete, HttpStatus.OK);
    }
}
