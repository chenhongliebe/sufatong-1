/**
 * 
 * Copyright From 2015, 微贷（杭州）金融信息服务有限公司. All rights reserved.
 * 
 * FirstTb.java
 * 
 */
package com.legalups.sufatong.model;

import java.util.Date;

/**
 * <p>
 * 表 : first_tb的 Model 类
 * 
 * @author 	wangyz
 * @date 	2017年06月18日
 */
public class FirstTb {
    /** 字段:ID，主键ID */
    private Long id;

    /** 字段:real_name，真是姓名 */
    private String realName;

    /** 字段:user_name，登录名 */
    private String userName;

    /** 字段:state，0-注销，1-启用 */
    private String state;

    /** 字段:create_id，创建人 */
    private String createId;

    /** 字段:create_date，创建时间 */
    private Date createDate;

    /** 字段:update_id，修改人 */
    private String updateId;

    /** 字段:update_date，修改时间 */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}