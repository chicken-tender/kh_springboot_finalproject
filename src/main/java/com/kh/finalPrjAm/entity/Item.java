package com.kh.finalPrjAm.entity;
import com.kh.finalPrjAm.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // ☘️JPA에 Entity 클래스임을 알려줌, Entity 클래스는 반드시 ✨Primary Key✨가 있어야 합니다.
@Getter @Setter @ToString
public class Item {
    @Id // ☘️해당 키가 Primary Key임을 지정하는 어노테이션
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // ☘️JPA 구현체의 생성 전략을 따름
    private Long id;                       // Primary Key

    @Column(nullable = false, length = 50) // ☘️NOT NULL, 글자 수 제한
    private String itemName;               // 상품 코드

    @Column(nullable = false)
    private int price;                     // 상품 가격

    @Column(nullable = false)
    private int stockNum;                  // 재고 수량

    @Lob // ☘️기존 문자열 보다 더 긴 문자열 사용할 때 추가
    @Column(nullable = false)
    private String itemDetail;             // 상품 상세 설명

    @Enumerated(EnumType.STRING) // ☘️Enum 타입이 문자열임을 알려줌
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    private LocalDateTime regTime;         // 등록 시간
    private LocalDateTime updateTime;      // 수정 시간
}
