package com.dkd.goldCustoms2.districtMaintenance.domain.vo;

public class MyTreeNode {

    private String infoId;

    private String id;

    private String name;

    private String isParent = "false";

    private boolean checked = false;

    public MyTreeNode(){
        super();
    }

    public MyTreeNode(String infoId, String id, String name, String isParent, boolean checked) {
        this.infoId = infoId;
        this.id = id;
        this.name = name;
        this.isParent = isParent;
        this.checked = checked;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
