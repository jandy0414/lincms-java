package cn.chenxins.cms.model.json;

import cn.chenxins.exception.ParamValueException;

public class UserJsonIn {

    private String nickname;

    private String password;

    private String old_password;

    private String new_password;

    private String confirm_password;

    private String email;

    private Integer group_id;


    public static void ValidRequiredLogin(UserJsonIn tmp) throws ParamValueException {
        if (tmp.getNickname()==null || "".equals(tmp.getNickname().trim())){
            throw new ParamValueException("操作用户名是必填项，不能为空");
        }
        if (tmp.getPassword()==null || "".equals(tmp.getPassword().trim())){
            throw new ParamValueException("密码是必填项，不能为空");
        }
    }

    public static void ValidRequiredRegister(UserJsonIn tmp) throws ParamValueException {
        if (tmp.getNickname()==null || "".equals(tmp.getNickname().trim())){
            throw new ParamValueException("操作用户名是必填项，不能为空");
        }
        if (tmp.getPassword()==null || "".equals(tmp.getPassword().trim())){
            throw new ParamValueException("密码是必填项，不能为空");
        }
        if (!tmp.getPassword().equals(tmp.getConfirm_password())) {
            throw new ParamValueException("两次输入的密码不一致，请输入相同的密码");
        }
        if (tmp.getGroup_id()== null || tmp.getGroup_id()==0) {
            throw new ParamValueException("分组ID是必填项");
        }
        if (tmp.getEmail()==null || "".equals(tmp.getEmail().trim())) {
            throw new ParamValueException("电子邮箱不符合规范，请输入正确的邮箱");
        }

    }
    public static void ValidRequiredUserUpd(UserJsonIn tmp) throws ParamValueException {
        if (tmp.getEmail()==null || "".equals(tmp.getEmail().trim())) {
            throw new ParamValueException("电子邮箱不符合规范，请输入正确的邮箱");
        }

    }
    public static void ValidRequiredAdminUpd(UserJsonIn tmp) throws ParamValueException {
        if (tmp.getEmail()==null || "".equals(tmp.getEmail().trim())) {
            throw new ParamValueException("电子邮箱不符合规范，请输入正确的邮箱");
        }
        if (tmp.getGroup_id()== null || tmp.getGroup_id()==0) {
            throw new ParamValueException("分组ID是必填项");
        }

    }
    public static  void ValidRequiredChangeUpd(UserJsonIn tmp) throws ParamValueException {
        if (tmp.getOld_password() == null || "".equals(tmp.getOld_password().trim())) {
            throw new ParamValueException("原始密码是必填项，不能为空！");
        }
        if (tmp.getNew_password() == null || "".equals(tmp.getNew_password().trim())) {
            throw new ParamValueException("新始密码是必填项，不能为空！");
        }
        if (tmp.getConfirm_password() == null || "".equals(tmp.getConfirm_password().trim())) {
            throw new ParamValueException("确认密码是必填项，不能为空！");
        }
        if (!tmp.getNew_password().equals(tmp.getConfirm_password())) {
            throw new ParamValueException("两次输入的密码不一致，请输入相同的密码");
        }
    }

    public static  void ValidRequiredChangeApwd(UserJsonIn tmp) throws ParamValueException {

        if (tmp.getNew_password() == null || "".equals(tmp.getNew_password().trim())) {
            throw new ParamValueException("新始密码是必填项，不能为空！");
        }
        if (tmp.getConfirm_password() == null || "".equals(tmp.getConfirm_password().trim())) {
            throw new ParamValueException("确认密码是必填项，不能为空！");
        }
        if (!tmp.getNew_password().equals(tmp.getConfirm_password())) {
            throw new ParamValueException("两次输入的密码不一致，请输入相同的密码");
        }
    }



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
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

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
