package com.kh.finalPrjAm.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private int orderPrice;
    private int count;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    @ManyToOne // 하나의 상품은 여러 주문 상품으로 들어갈 수 있으므로 다대일 매핑 설정
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne // 한번의 주문에 여러개의 상품을 주문할 수 있으므로 다대일 매핑 설정
    @JoinColumn(name = "order_id")
    private Order order;
}
