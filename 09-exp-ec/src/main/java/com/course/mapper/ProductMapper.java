package com.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.course.dto.ProductDto;
import com.course.vo.ProductQueryParam;
import com.course.vo.ProductVo;

//@Mapper
public interface ProductMapper {
	
	@Select("select * from product p left join product_price pr on p.id = pr.product_id")
	List<ProductDto> findAll();
	
	@Select("select * from product_review re where re.product_id = #{id}")
	List<ProductDto> findReviewById(Long id);
	
	@Select("SELECT * FROM　PRODUCT_REVIEW")
	List<ProductDto> findAllReview();
	
	@Select("SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL")
	Long getProductSeq();
	
	@Insert("INSERT INTO PRODUCT (ID, CODE, NAME) VALUES (#{id}, #{code}, #{name})")
	void insertProduct(ProductVo vo);
	
	@Insert("INSERT INTO PRODUCT_PRICE (ID, PRODUCT_ID, LIST_PRICE, SALES_PRICE) VALUES (PRODUCT_PRICE_SEQ.NEXTVAL, #{id}, #{listPrice}, #{salesPrice})")
	void insertProductPrice(ProductVo vo);
	
	ProductDto findById(Long id);
	
	List<ProductDto> findByCondition(ProductQueryParam queryParam);
	
	List<ProductDto> findByCategories(List<String> categories);
	
	void updatePrice(ProductVo vo);
	
}
