package cn.chenxins.cms.model.json;

import cn.chenxins.cms.model.entity.LinUser;

import java.util.*;

public class UserJsonOut {

    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private String nickname;

    private Short isSuper;

    private Short active;

    private String email;

    private Integer group_id;

    private String group_name;

    private List<Map.Entry<String, List>> auths=new ArrayList<>();



    public UserJsonOut(LinUser user) {
        this.createTime=user.getCreateTime();
        this.updateTime=user.getUpdateTime();
        this.deleteTime=user.getDeleteTime();
        this.active=user.getActive();
        this.email=user.getEmail();
        this.group_id=user.getGroupId();
        this.nickname=user.getNickname();
        this.isSuper=user.getIsSuper();
        this.id=user.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Short getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Short isSuper) {
        this.isSuper = isSuper;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<Map.Entry<String, List>> getAuths() {
        return auths;
    }

    public void setAuths(HashMap<String,List> map) {
        if(this.auths==null){
            this.auths=new ArrayList<>();
        }
        for(Map.Entry<String, List> entry:map.entrySet()){
            this.auths.add(entry);
        }
    }

}


