package cn.stores.entity;

public class RegulatorInfo {
    private String regulatorid;

    private String regulatorname;

    private String password;

    private String regulator_role_id;

    public String getRegulatorid() {
        return regulatorid;
    }

    public void setRegulatorid(String regulatorid) {
        this.regulatorid = regulatorid == null ? null : regulatorid.trim();
    }

    public String getRegulatorname() {
        return regulatorname;
    }

    public void setRegulatorname(String regulatorname) {
        this.regulatorname = regulatorname == null ? null : regulatorname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRegulator_role_id() {
        return regulator_role_id;
    }

    public void setRegulator_role_id(String regulator_role_id) {
        this.regulator_role_id = regulator_role_id == null ? null : regulator_role_id.trim();
    }
}