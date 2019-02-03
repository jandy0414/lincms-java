package cn.chenxins.cms.service;


import cn.chenxins.cms.model.entity.LinLog;
import cn.chenxins.cms.model.entity.mapper.LinAuthMapper;
import cn.chenxins.cms.model.entity.mapper.LinLogMapper;
import cn.chenxins.cms.model.json.LogPageJsonOut;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class LogService {


    @Autowired
    private LinLogMapper linLogMapper;

    @Autowired
    private LinAuthMapper linAuthMapper;



    @Transactional(propagation = Propagation.SUPPORTS)
    public LogPageJsonOut getAllLog(String name, Date start, Date end,Integer page,Integer count) throws BussinessErrorException,Exception {
        // 开始分页
        PageHelper.startPage(page, count);
        Example example = new Example(LinLog.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotBlank(name))
        {
            criteria.andLike("userName","%"+name+"%");
        }
        if (start!=null && end!=null){
            criteria.andBetween("time",start,end);
        }
        example.orderBy("time").desc();

        List<LinLog> alist = linLogMapper.selectByExample(example);
        int total = linLogMapper.selectCountByExample(example);
        if (total==0){
            throw new BussinessErrorException("没有找到相关日志");
        }

        return new LogPageJsonOut(total,alist);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public LogPageJsonOut getAllLogByKey(String keyword, String name, String start, String end, Integer page, Integer count) throws BussinessErrorException,Exception {
        // 开始分页
        PageHelper.startPage(page, count);
        Example example = new Example(LinLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("message","%"+keyword+"%");

        if (StringUtil.isNotBlank(name))
        {
            criteria.andLike("userName","%"+name+"%");
        }
        if (start!=null && end!=null){
            criteria.andBetween("time",start,end);
        }
        example.orderBy("time").desc();

        List<LinLog> alist = linLogMapper.selectByExample(example);
        int total = linLogMapper.selectCountByExample(example);
        if (total==0){
            throw new BussinessErrorException("没有找到相关日志");
        }

        return new LogPageJsonOut(total,alist);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> getAllLogUser() throws BussinessErrorException,Exception {

        return linLogMapper.getUsersUsingGroupBy();
    }

    public void saveLog(LinLog linLog) throws Exception {
        linLogMapper.insert(linLog);
    }




}
