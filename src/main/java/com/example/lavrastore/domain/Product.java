package com.example.lavrastore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {

  /* Private Fields */

  private int productId;
  private int categoryId;
  private String name;

  /* JavaBeans Properties */

  public int getProductId() { return productId; }
  public void setProductId(int productId) { this.productId = productId; }

  public int getCategoryId() { return categoryId; }
  public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }


  /* Public Methods*/

  public String toString() {
    return getName();
  }
}
