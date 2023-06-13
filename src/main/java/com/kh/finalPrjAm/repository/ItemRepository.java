package com.kh.finalPrjAm.repository;
import com.kh.finalPrjAm.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

// JpaRepository는 2개의 제네릭 타입을 사용. 첫번째는 엔티티 타입 클래스, 두번째는 기본 키
// 기본적인 CRUD 및 페이징 처리를 위한 메소드는 정의되어 있음.
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemName(String itemName);                                 // 조회
    List<Item> findByItemNameOrItemDetail(String itemName, String ItemDetail); // or 조건 처리하기
    List<Item> findByPriceLessThan(Integer price); // LessThan : 매개변수로 전달 받은 값 보다 작은 상품 조회
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); // OrderBy로 정렬 : OrderBy + 속성명 + Asc / Desc
    List<Item> findByPriceBetween(Integer min, Integer max); // Between : 범위에 해당하는 조건 검색
    List<Item> findByItemNameContaining(String keyword); // 전달된 부분 문자열에 대한 검색
    // 🎀JPQL(Java Persistence Query Language)
    // 쿼리 메소드의 경우 조건이 복잡한 메소드를 선언하는 것이 너무 복잡하거나 만들 수 없는 경우에 사용
    // JPQL은 실제 DB를 바라보는 것이 아닌 JPA 엔티티를 바라봅니다.(실제 DB에 접근 X)
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    // ✨실제 쿼리문 쓸 때는 변수명 🐍'스네이크 표기법'으로 바꿔줘야 함! (itemDetail -> item_detail)
    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc",
            nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}
