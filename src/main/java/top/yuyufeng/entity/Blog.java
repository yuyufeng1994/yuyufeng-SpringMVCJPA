package top.yuyufeng.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuyufeng on 2017/8/1.
 */
@Entity
@Cacheable
@Table(name = "blog_info")
public class Blog {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.AUTO) // 自动增长类型
    @Column(length = 10)
    private Long blogId;

    @Column(length = 200)
    private String blogTitle;

    @Column(length = 200)
    private String blogBrief;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String blogContent;

    @Column(length = 200)
    private String blogImage;

    @Column(updatable=false)
    private Date createTime;

    private Date updateTime;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "userId",updatable = false)
    private User blogUser;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "blog_catalog_info", joinColumns = { @JoinColumn(name = "blogId") }, inverseJoinColumns = {
            @JoinColumn(name = "catalogId") })
    @OrderBy("catalogId desc")
    private Set<Catalog> catalogs = new HashSet<>();

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(User blogUser) {
        this.blogUser = blogUser;
    }

    public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

}
