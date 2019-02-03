package cn.chenxins.cms.model.entity;

import cn.chenxins.cms.model.json.UserJsonIn;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lin_user")
public class LinUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_time")
    private Date deleteTime;

    private String nickname;

    @Column(name = "super")
    private Short isSuper;

    private Short active;

    private String email;

    @Column(name = "group_id")
    private Integer groupId;

    private String password;

    public LinUser(){}

    public LinUser(UserJsonIn userJsonIn){
        this.nickname=userJsonIn.getNickname();
        this.email=userJsonIn.getEmail();
        this.groupId=userJsonIn.getGroup_id();
    }


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return delete_time
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * @param deleteTime
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return super
     */
    public Short getIsSuper() {
        return isSuper;
    }

    /**
     * @param isSuper
     */
    public void setIsSuper(Short isSuper) {
        this.isSuper = isSuper;
    }

    /**
     * @return active
     */
    public Short getActive() {
        return active;
    }

    /**
     * @param active
     */
    public void setActive(Short active) {
        this.active = active;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return group_id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}