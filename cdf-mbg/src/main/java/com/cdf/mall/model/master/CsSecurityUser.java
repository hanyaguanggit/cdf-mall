package com.cdf.mall.model.master;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

public class CsSecurityUser implements Serializable {
    @Schema(description = "后台用户Id",name = "id")
    private Integer id;

    @Schema(description = "用户名",name = "username")
    private String username;

    @Schema(description = "密码（AES)",name = "password")
    private String password;

    @Schema(description = "后台用户失效日期，空为永不过期",name = "expirydate")
    private Date expirydate;

    @Schema(description = "0未锁定，1锁定",name = "locked")
    private Boolean locked;

    @Schema(description = "后台用户信任凭证失效日期，空为永不过期",name = "credentialexpirydate")
    private Date credentialexpirydate;

    @Schema(description = "0未启用，1启动",name = "enabled")
    private Boolean enabled;

    @Schema(description = "头像",name = "avatar")
    private Integer avatar;

    @Schema(description = "登录名",name = "loginname")
    private String loginname;

    @Schema(description = "手机",name = "mobilephoneno")
    private String mobilephoneno;

    private String email;

    @Schema(description = "创建时间",name = "createdate")
    private Date createdate;

    @Schema(description = "删除状态：0未删除，1已删除",name = "deleted")
    private Short deleted;

    @Schema(description = "创建者",name = "creator")
    private Integer creator;

    @Schema(description = "最后修改者",name = "lastmodifieduser")
    private Integer lastmodifieduser;

    @Schema(description = "最后修改时间",name = "lastmodifiedtime")
    private Date lastmodifiedtime;

    @Schema(description = "店铺id",name = "shopid")
    private Integer shopid;

    @Schema(description = "最后登录IP",name = "lastloginip")
    private String lastloginip;

    @Schema(description = "最后登录时间",name = "lastlogintime")
    private Date lastlogintime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getCredentialexpirydate() {
        return credentialexpirydate;
    }

    public void setCredentialexpirydate(Date credentialexpirydate) {
        this.credentialexpirydate = credentialexpirydate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getMobilephoneno() {
        return mobilephoneno;
    }

    public void setMobilephoneno(String mobilephoneno) {
        this.mobilephoneno = mobilephoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getLastmodifieduser() {
        return lastmodifieduser;
    }

    public void setLastmodifieduser(Integer lastmodifieduser) {
        this.lastmodifieduser = lastmodifieduser;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", expirydate=").append(expirydate);
        sb.append(", locked=").append(locked);
        sb.append(", credentialexpirydate=").append(credentialexpirydate);
        sb.append(", enabled=").append(enabled);
        sb.append(", avatar=").append(avatar);
        sb.append(", loginname=").append(loginname);
        sb.append(", mobilephoneno=").append(mobilephoneno);
        sb.append(", email=").append(email);
        sb.append(", createdate=").append(createdate);
        sb.append(", deleted=").append(deleted);
        sb.append(", creator=").append(creator);
        sb.append(", lastmodifieduser=").append(lastmodifieduser);
        sb.append(", lastmodifiedtime=").append(lastmodifiedtime);
        sb.append(", shopid=").append(shopid);
        sb.append(", lastloginip=").append(lastloginip);
        sb.append(", lastlogintime=").append(lastlogintime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}