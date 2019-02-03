package cn.chenxins.cms.service;


import cn.chenxins.cms.model.entity.Book;
import cn.chenxins.cms.model.entity.LinLog;
import cn.chenxins.cms.model.entity.mapper.BookMapper;
import cn.chenxins.cms.model.entity.mapper.LinAuthMapper;
import cn.chenxins.cms.model.entity.mapper.LinLogMapper;
import cn.chenxins.cms.model.json.BookJsonIn;
import cn.chenxins.cms.model.json.LogPageJsonOut;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.utils.JdateUtils;
import cn.chenxins.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Book getBookDetail(Integer id)throws BussinessErrorException,Exception {
        Book model=bookMapper.selectByPrimaryKey(id);
        if (model==null || model.getDeleteTime()!=null) {
            throw new BussinessErrorException("没有找到相关的信息");
        }
        return model;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Book> getListPageS(String keyword) {

        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();

//        criteria.andEqualTo("deleteTime",null);
        criteria.andIsNull("deleteTime");
        if(StringUtil.isNotBlank(keyword)){
            criteria.andLike("title", "%" + keyword.trim() + "%");
        }
        example.orderBy("id").desc();

        List<Book> alist=bookMapper.selectByExample(example);

        return alist;

    }







    @Transactional(propagation = Propagation.REQUIRED)
    public void addmodelS(BookJsonIn json) throws BussinessErrorException,Exception{
        Book model=new Book();
        model.setTitle(json.getTitle());
        model.setAuthor(json.getAuthor());
        model.setSummary(json.getSummary());
        model.setImage(json.getImage());

        int exists=bookMapper.selectCount(model);
        if (exists==0){
            model.setCreateTime(JdateUtils.getCurrentDate());
            bookMapper.insert(model);
        } else {
            throw new BussinessErrorException("此信息已经存在,请不要重复创建");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updModelS(Integer id,BookJsonIn json) throws Exception{
        Book model=bookMapper.selectByPrimaryKey(id);
        if (model==null) {
            throw new BussinessErrorException("您要更新的信息不存在");
        }
        model.setTitle(json.getTitle());
        model.setAuthor(json.getAuthor());
        model.setSummary(json.getSummary());
        model.setImage(json.getImage());
        model.setUpdateTime(JdateUtils.getCurrentDate());

        bookMapper.updateByPrimaryKey(model);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delModelS(Integer id) throws BussinessErrorException,Exception{
        Book book=bookMapper.selectByPrimaryKey(id);
        if (book==null){
            throw new BussinessErrorException("此信息不存在");
        }
        if (book.getDeleteTime()==null){
            book.setDeleteTime(JdateUtils.getCurrentDate());
            bookMapper.updateByPrimaryKey(book);
        } else {
            throw new BussinessErrorException("此信息已经不存在,不需要重复删除");
        }
//        authUserMapper.deleteByPrimaryKey(UserId);
    }


}
