package cn.chenxins.cms.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lin_log")
public class LinLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    private Date time;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "status_code")
    private Integer statusCode;

    private String method;

    private String path;

    private String authority;

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
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return status_code
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "LinLog{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", statusCode=" + statusCode +
                ", method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}