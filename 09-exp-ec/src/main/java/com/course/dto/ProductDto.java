package com.course.dto;

import java.math.BigDecimal;

public class ProductDto {

	private Long id;

	private String code;

	private String name;

	private BigDecimal listPrice;

	private BigDecimal salesPrice;

	private String memo;

	private String cname;
	
	private Long productId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public ProductDto() {

	}

	public ProductDto(String name, BigDecimal listPrice, BigDecimal salesPrice, String memo, String cname) {
		this.name = name;
		this.listPrice = listPrice;
		this.salesPrice = salesPrice;
		this.memo = memo;
		this.cname = cname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
