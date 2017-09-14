package top.yuyufeng.dto;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by yuyufeng on 2017/8/3.
 */
public class CataLogDto implements Serializable{
    private Long catalogId;

    private String catalogName;

    private String catalogBrief;

    private Integer blogsSize;

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

    public Integer getBlogsSize() {
        return blogsSize;
    }

    public void setBlogsSize(Integer blogsSize) {
        this.blogsSize = blogsSize;
    }

    @Override
    public String toString() {
        return "CataLogDto{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", catalogBrief='" + catalogBrief + '\'' +
                ", blogsSize=" + blogsSize +
                '}';
    }
}
