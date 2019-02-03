package cn.chenxins.cms.model.json;

import java.util.List;

public class GroupPageJsonOut {

    private Integer total_nums;

    private List<GroupAuthJsonOut> collection;

    public GroupPageJsonOut(Integer total_nums, List<GroupAuthJsonOut> collection) {
        this.total_nums = total_nums;
        this.collection = collection;
    }

    public Integer getTotal_nums() {
        return total_nums;
    }

    public void setTotal_nums(Integer total_nums) {
        this.total_nums = total_nums;
    }

    public List<GroupAuthJsonOut> getCollection() {
        return collection;
    }

    public void setCollection(List<GroupAuthJsonOut> collection) {
        this.collection = collection;
    }
}
