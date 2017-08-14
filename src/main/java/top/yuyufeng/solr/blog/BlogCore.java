package top.yuyufeng.solr.blog;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * Created by yuyufeng on 2017/8/9.
 */

public class BlogCore {
    @Field
    public Long blogId;
    @Field
    public String blogTitle;
    @Field
    public String blogBrief;
    @Field
    public String blogContent;
    @Field
    public String blogImage;
    @Field
    public Date updateTime;
    @Field
    public String blogUserName;
    @Field
    public Long blogUserId;
    @Field
    public String blogCatalogNames;
    @Field
    public String blogCatalogIds;
    @Field
    public String keywords;


    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogBrief() {
        return blogBrief;
    }

    public void setBlogBrief(String blogBrief) {
        this.blogBrief = blogBrief;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBlogUserName() {
        return blogUserName;
    }

    public void setBlogUserName(String blogUserName) {
        this.blogUserName = blogUserName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Long getBlogUserId() {
        return blogUserId;
    }

    public void setBlogUserId(Long blogUserId) {
        this.blogUserId = blogUserId;
    }

    public String getBlogCatalogNames() {
        return blogCatalogNames;
    }

    public void setBlogCatalogNames(String blogCatalogNames) {
        this.blogCatalogNames = blogCatalogNames;
    }

    public String getBlogCatalogIds() {
        return blogCatalogIds;
    }

    public void setBlogCatalogIds(String blogCatalogIds) {
        this.blogCatalogIds = blogCatalogIds;
    }
}
