package top.yuyufeng.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用户实体
 * Created by yuyufeng on 2017/4/20.
 */
@Entity
@Table(name = "user_info")
public class User implements Serializable{
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.AUTO) // 自动增长类型
    @Column(length = 10)
    private Long userId;

    @Column(length = 10, unique = true)
    private String userAccount;

    @Column(length = 50)
    private String userName;

    @Column(length = 20)
    private String userPassword;
    /**
     * 1 正常  / 0 锁定
     */
    @Column(length = 1)
    private String userStatus;

    private Date createTime;
    /**
     * 1 超级管理员  /  2 普通用户
     */
    @Column(length = 1)
    private String accountType;

    @Column(length = 50, unique = true)
    private String userEmail;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
