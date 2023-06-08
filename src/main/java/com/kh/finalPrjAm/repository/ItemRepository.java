package com.kh.finalPrjAm.repository;
import com.kh.finalPrjAm.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// JpaRepository는 2개의 제네릭 타입을 사용. 첫번째는 엔티티 타입 클래스, 두번째는 기본 키
// 기본적인 CRUD 및 페이징 처리를 위한 메소드는 정의되어 있음.
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemName(String itemName);
    // or 조건 처리하기
    List<Item> findByItemNameOrItemDetail(String itemName, String ItemDetail);
    // LessThan : 매개변수로 전달 받은 값 보다 작은 상품 조회
    List<Item> findByPriceLessThan(Integer price);
    // OrderBy로 정렬 : OrderBy + 속성명 + Asc / Desc
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
}
