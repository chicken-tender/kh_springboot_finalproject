package com.kh.finalPrjAm.service;
import com.kh.finalPrjAm.constant.ItemSellStatus;
import com.kh.finalPrjAm.dto.ItemDto;
import com.kh.finalPrjAm.entity.Item;
import com.kh.finalPrjAm.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor // 매개변수가 있는 생성자를 자동으로 만들어주겠다.
public class ItemService {
    // 의존성을 통해 빈에 등록된 필드는 불변성이 있어야 하므로 final 선언을 해야 함.
    private final ItemRepository itemRepository;
    // 상품 생성
    public boolean createItem(String name, int price, String detail, ItemSellStatus status, int stock) {
        Item item = new Item();
        item.setItemName(name);
        item.setPrice(price);
        item.setItemDetail(detail);
        item.setItemSellStatus(status);
        item.setStockNum(stock);
        Item saveItem = itemRepository.save(item);
        log.info("저장된 상품 이름 : " + saveItem.getItemName());
        return true;
    }

    // 상품 조회
    public List<ItemDto> getItemList() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = new ArrayList<>();
        for(Item item : items) {
            ItemDto itemDto = new ItemDto();
            itemDto.setName(item.getItemName());
            itemDto.setPrice(item.getPrice());
            itemDto.setDetail(item.getItemDetail());
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }
}
