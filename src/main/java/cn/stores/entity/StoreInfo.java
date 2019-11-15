package cn.stores.entity;

public class StoreInfo {
    private String storeid;

    private String shorename;

    private String regulatorid;

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid == null ? null : storeid.trim();
    }

    public String getShorename() {
        return shorename;
    }

    public void setShorename(String shorename) {
        this.shorename = shorename == null ? null : shorename.trim();
    }

    public String getRegulatorid() {
        return regulatorid;
    }

    public void setRegulatorid(String regulatorid) {
        this.regulatorid = regulatorid == null ? null : regulatorid.trim();
    }
}