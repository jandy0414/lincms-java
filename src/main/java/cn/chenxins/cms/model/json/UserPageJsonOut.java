package cn.chenxins.cms.model.json;

import java.util.List;

public class UserPageJsonOut {

    private Integer total_nums;

    private List<UserJsonOut> collection;

    public UserPageJsonOut(Integer total_nums, List<UserJsonOut> collection) {
        this.total_nums = total_nums;
        this.collection = collection;
    }

    public Integer getTotal_nums() {
        return total_nums;
    }

    public void setTotal_nums(Integer total_nums) {
        this.total_nums = total_nums;
    }

    public List<UserJsonOut> getCollection() {
        return collection;
    }

    public void setCollection(List<UserJsonOut> collection) {
        this.collection = collection;
    }
}
