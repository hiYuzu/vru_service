package com.tcb.vru_service.model;

import com.tcb.vru_service.base.BaseVO;

/**
 * @Author: WangLei
 * @Description: 资源VO
 * @Date: Create in 2020/07/10 15:08
 * @Modify by WangLei
 */
public class ResourceVO extends BaseVO {

    private String id;
    private String name;
    private Boolean page;
    private String prop;
    private String title;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPage() {
        return page;
    }

    public void setPage(Boolean page) {
        this.page = page;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
