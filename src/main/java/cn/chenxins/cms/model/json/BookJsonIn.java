package cn.chenxins.cms.model.json;

import cn.chenxins.exception.ParamValueException;
import cn.chenxins.utils.StringUtil;

public class BookJsonIn {
    private String title;

    private String author;

    private String summary;

    private String image;


    public static void ValidRequired(BookJsonIn tmp) throws ParamValueException {

        if (!StringUtil.isNotBlank(tmp.getTitle())) {
            throw new ParamValueException("必须传入图书名");
        }
        if (!StringUtil.isNotBlank(tmp.getAuthor())) {
            throw new ParamValueException("必须传入图书作者");
        }
        if (!StringUtil.isNotBlank(tmp.getSummary())) {
            throw new ParamValueException("必须传入图书综述");
        }
        if (!StringUtil.isNotBlank(tmp.getImage())) {
            throw new ParamValueException("必须传入图书插图");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
