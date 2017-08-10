package top.yuyufeng.solr.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import top.yuyufeng.entity.User;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by yuyufeng on 2017/8/9.
 */

@SolrDocument(solrCoreName = "yuyufeng_blog")
public class BlogCore {
    @Id
    @Indexed
    public String blogId;
    @Indexed
    public String blogTitle;
    @Indexed
    public String blogBrief;
    @Indexed
    public String blogContent;
    @Indexed
    public String blogImage;
    @Indexed
    public Date updateTime;
    @Indexed
    public String blogUserName;
    @Indexed
    public String keywords;


    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
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

    @Override
    public String toString() {
        return "BlogCore{" +
                "blogId='" + blogId + '\'' +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogBrief='" + blogBrief + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", blogImage='" + blogImage + '\'' +
                ", updateTime=" + updateTime +
                ", blogUserName='" + blogUserName + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
