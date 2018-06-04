package com.dkd.gold_customs2.customs_client.entity.special_regulatory_area;

import com.dkd.gold_customs2.customs_client.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class DistrictArea extends BaseEntity {


        private Long id;


        /**
         * 关区代码
         */
        @NotNull(message = "area_save_02")
        private String areaCode;


        /**
         * 关区名称(验证非重复)
         */
        @NotNull(message = "area_save_05")
        private String areaName;

        /**
         * 监管区域代码( 关区 + 区域  唯一)
         */
        @NotNull(message = "area_save_07")
        private String regionCode;

        /**
         * 监管区域名称( 关区 + 区域  唯一 )
         */
        @NotNull(message = "area_save_08")
        private String regionName;

        /**
         * 使用状态
         */
        @NotNull(message = "area_save_09")
        private int status;


        /**
         * 录入日期
         */
        @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd")
        protected Date createDate;

        /**
         * 最后修改时间
         */
        @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy/MM/dd")
        protected Date optDate;


        private Set<DistrictAreaRelation> regions = new HashSet<DistrictAreaRelation>();

        private String token;
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

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public String getToken() {
                return token;
        }

        public void setToken(String token) {
                this.token = token;
        }

        public Set<DistrictAreaRelation> getRegions() {
                return regions;
        }

        public void setRegions(Set<DistrictAreaRelation> regions) {
                this.regions = regions;
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
