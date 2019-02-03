package cn.chenxins.cms.model.json;

import cn.chenxins.exception.ParamValueException;
import cn.chenxins.utils.StringUtil;

import java.util.List;

public class AuthJsonIn {

    private Integer group_id;

    private String auth;

    private List<String> auths;

    public static void ValidRequiredPatch(AuthJsonIn tmp) throws ParamValueException {
        if (tmp.getGroup_id()==null) {
            throw new ParamValueException("分组号是必填选");
        }
        if (!StringUtil.isNotBlank(tmp.getAuth())) {
            throw new ParamValueException("auth字段");
        }
    }

    public static void ValidRequiredPatchs(AuthJsonIn tmp) throws ParamValueException {
        if (tmp.getGroup_id()==null) {
            throw new ParamValueException("分组号是必填选");
        }
        if (tmp.getAuths()==null || tmp.getAuths().size()==0){
            throw new ParamValueException("auths字段信息不能为空");
        }
    }


    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }
}
