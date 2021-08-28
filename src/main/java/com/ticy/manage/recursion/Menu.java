package com.ticy.manage.recursion;

import java.util.List;

/**
 * @Author tkk
 * @Time 2021/8/17 15:07
 * @Description todo
 */
public class Menu {

    private String parentId;

    private String id;

    private String name;

    private String description;

    private List<Menu> chirdMenu;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Menu> getChirdMenu() {
        return chirdMenu;
    }

    public void setChirdMenu(List<Menu> chirdMenu) {
        this.chirdMenu = chirdMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "parentId='" + parentId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", chirdMenu=" + chirdMenu +
                '}';
    }
}
