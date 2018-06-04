package com.dkd.gold_customs2.district_maintenance.domain.entity.special_regulatory_area;

import com.dkd.gold_customs2.district_maintenance.domain.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "M_DISTRICTAREA")
public class DistrictArea extends BaseEntity {

        public static final Integer USED = 0;
        public static final Integer NOT_USED = 1;

        @Id
        @SequenceGenerator(name = "areaIdGen", sequenceName = "M_DISTRICTAREA_AREA_ID_SEQ")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "areaIdGen")
        @Column(name = "AREA_ID",length = 100)
        private Long id;


        /**
         * 关区代码
         */
        @NotNull(message = "关区代码不能为空")
        @Column(name = "AREA_CODE",nullable = false,length = 100,updatable = true)
        private String areaCode;


        /**
         * 关区名称(验证非重复)
         */
        @NotNull(message = "关区名称不能为空")
        @Column(name = "AREA_NAME",nullable = false,length = 100)
        private String areaName;

        /**
         * 监管区域代码( 关区 + 区域  唯一)
         */
        @NotNull(message = "监管区域代码不能为空")
        @Column(name = "REGION_CODE",nullable = false,length = 100,updatable = true)
        private String regionCode;

        /**
         * 监管区域名称( 关区 + 区域  唯一 )
         */
        @NotNull(message = "监管区域代码不能为空")
        @Column(name = "REGION_NAME",nullable = false,length = 100)
        private String regionName;

        /**
         * 使用状态
         */
        @NotNull(message = "使用状态")
        @Column(name = "STATUS")
        private Integer status;

        @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd")
        @Column(name = "CREATE_DATE",nullable = false,updatable = true)
        private Date createDate;

        @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd")
        @Column(name = "OPT_DATE",nullable = false)
        private Date optDate;

        @JsonIgnore
        @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
                fetch = FetchType.LAZY, mappedBy = "districtArea")
        private Set<DistrictAreaRelation> regions = new HashSet<DistrictAreaRelation>();

        /**
         * 排序
         */
        @Column(name = "AREA_ORDER")
        private String areaOrder;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getAreaCode() {
                return areaCode;
        }

        public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
        }

        public String getAreaName() {
                return areaName;
        }

        public void setAreaName(String areaName) {
                this.areaName = areaName;
        }

        public String getRegionCode() {
                return regionCode;
        }

        public void setRegionCode(String regionCode) {
                this.regionCode = regionCode;
        }

        public String getRegionName() {
                return regionName;
        }

        public void setRegionName(String regionName) {
                this.regionName = regionName;
        }

        public Integer getStatus() {
                return status;
        }

        public void setStatus(Integer status) {
                this.status = status;
        }

        public Set<DistrictAreaRelation> getRegions() {
                return regions;
        }

        public void setRegions(Set<DistrictAreaRelation> regions) {
                this.regions = regions;
        }

        public String getAreaOrder() {
                return areaOrder;
        }

        public void setAreaOrder(String areaOrder) {
                this.areaOrder = areaOrder;
        }

        @Override
        public Date getCreateDate() {
                return createDate;
        }

        @Override
        public void setCreateDate(Date createDate) {
                this.createDate = createDate;
        }

        @Override
        public Date getOptDate() {
                return optDate;
        }

        @Override
        public void setOptDate(Date optDate) {
                this.optDate = optDate;
        }
}
