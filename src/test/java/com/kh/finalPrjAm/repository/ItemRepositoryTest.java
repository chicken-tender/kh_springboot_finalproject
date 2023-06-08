package com.kh.finalPrjAm.repository;

import com.kh.finalPrjAm.constant.ItemSellStatus;
import com.kh.finalPrjAm.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 단위 테스트 및 통합 테스트를 위해 스프링부트에서 제동하는 어노테이션
@TestPropertySource(locations = "classpath:application-test.properties") // 테스트 코드 실행시 해당 설정 파일을 우선적으로 불러옴
class ItemRepositoryTest {
    @Autowired // 의존성을 주입 받음
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemName("테스트 상품" + i);
            item.setPrice(10000 + i*1000);
            item.setItemDetail("테스트 상품의 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNum(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item); // INSERT 테스트
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNameTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemName("테스트 상품5");
        for(Item e : itemList) {
            System.out.println("✴️ " + e.toString());
        }
    }
    @Test
    @DisplayName("상품명 Or 상품 상세 설명 테스트")
    public void findByItemNameOrItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNameOrItemDetail("테스트 상품5", "테스트 상품의 상세 설명7");
        for(Item e : itemList) {
            System.out.println("결과 :️ " + e.toString());
        }
    }

    @Test
    @DisplayName("입력 받은 가격보다 작은 상품 조회 테스트")
    public void findByPriceLessThanTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(15000);
        for(Item e : itemList) {
            System.out.println("✴️ :️ " + e.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDescTest() {
        createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(15000);
        for(Item e : itemList) {
            System.out.println("✴️ :️ " + e.toString());
        }
    }
}