package cn.chenxins.cms.model.entity.mapper;

import cn.chenxins.cms.model.entity.LinLog;
import cn.chenxins.utils.MyMapper;

import java.util.List;

public interface LinLogMapper extends MyMapper<LinLog> {

    List<String> getUsersUsingGroupBy();

}