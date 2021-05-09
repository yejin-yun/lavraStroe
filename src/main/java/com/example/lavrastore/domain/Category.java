package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable {

  /* Private Fields */

  private int categoryId;
  private String name;

  /* JavaBeans Properties */

  public int getCategoryId() { return categoryId; }
  public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }


  /* Public Methods */

  public String toString() {
    return getName();
  }
}