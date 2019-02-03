package cn.chenxins.cms.model.json;

import cn.chenxins.cms.model.entity.LinLog;

import java.util.List;

public class LogPageJsonOut {

    private Integer total_nums;

    private List<LinLog> collection;

    public LogPageJsonOut(Integer total_nums, List<LinLog> collection) {
        this.total_nums = total_nums;
        this.collection = collection;
    }

    public Integer getTotal_nums() {
        return total_nums;
    }

    public void setTotal_nums(Integer total_nums) {
        this.total_nums = total_nums;
    }

    public List<LinLog> getCollection() {
        return collection;
    }

    public void setCollection(List<LinLog> collection) {
        this.collection = collection;
    }
}
