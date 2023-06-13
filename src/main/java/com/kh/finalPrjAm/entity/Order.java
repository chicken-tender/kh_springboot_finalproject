package com.kh.finalPrjAm.entity;
import com.kh.finalPrjAm.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;     // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    // 상품 판매 상태

    private LocalDateTime regTime;      // 등록 시간

    private LocalDateTime updateTime;   // 수정 시간

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    /* 외래키(order_id)가 order_item 테이블에 있으므로 연관 관계의 주인은 OrderItem 엔티티 입니다.
    * Order 엔티티가 주인이 아니므로 "mappedBy" 속성으로 연관 관계의 주인을 설정 합니다. */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList = new ArrayList<>();
}
