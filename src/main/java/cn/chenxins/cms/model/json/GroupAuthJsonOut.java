package cn.chenxins.cms.model.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAuthJsonOut {

    private Integer id;

    private String info;

    private String name;

    private List<Map.Entry<String, List>> auths;

    public GroupAuthJsonOut(Integer id, String info, String name, List<Map.Entry<String, List>> auths) {
        this.id = id;
        this.info = info;
        this.name = name;
        this.auths = auths;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map.Entry<String, List>> getAuths() {
        return auths;
    }

    public void setAuths(List<Map.Entry<String, List>> auths) {
        this.auths = auths;
    }
}
