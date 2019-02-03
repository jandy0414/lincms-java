package cn.chenxins.cms.model.json;

import cn.chenxins.exception.ParamValueException;
import cn.chenxins.utils.StringUtil;

import java.util.List;
import java.util.Map;

public class GroupAuthJsonIn {

    private String info;

    private String name;

    private List<String> auths;

    public GroupAuthJsonIn(Integer id, String info, String name, List<String> auths) {
        this.info = info;
        this.name = name;
        this.auths = auths;
    }

    public static void ValidRequiredCreateGroup(GroupAuthJsonIn tmp) throws ParamValueException {
        if (!StringUtil.isNotBlank(tmp.getName())) {
            throw new ParamValueException("分组名称不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getInfo())) {
            throw new ParamValueException("分组信息不能为空");
        }
        if (tmp.getAuths()==null || tmp.getAuths().size()==0){
            throw new ParamValueException("auths字段信息不能为空");
        }

    }

    public static void ValidRequiredUpdateGroup(GroupAuthJsonIn tmp) throws ParamValueException {
        if (!StringUtil.isNotBlank(tmp.getName())) {
            throw new ParamValueException("分组名称不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getInfo())) {
            throw new ParamValueException("分组信息不能为空");
        }
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

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }


}
