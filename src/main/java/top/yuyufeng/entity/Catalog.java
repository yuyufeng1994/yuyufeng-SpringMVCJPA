package top.yuyufeng.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 分类
 * Created by yuyufeng on 2017/8/1.
 */
@Entity
@Table(name = "catalog_info")
public class Catalog {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.AUTO) // 自动增长类型
    private Long catalogId;
    private String catalogName;
    private String catalogBrief;
    @Transient
    private String checked = "";

    @OrderBy("blogId desc")
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "catalogs")
    private Set<Blog> blogs = new HashSet<>();


    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogBrief() {
        return catalogBrief;
    }

    public void setCatalogBrief(String catalogBrief) {
        this.catalogBrief = catalogBrief;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", catalogBrief='" + catalogBrief + '\'' +
                '}';
    }
}
