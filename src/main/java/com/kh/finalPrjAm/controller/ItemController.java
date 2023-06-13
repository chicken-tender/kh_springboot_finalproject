package com.kh.finalPrjAm.controller;
import com.kh.finalPrjAm.constant.ItemSellStatus;
import com.kh.finalPrjAm.dto.ItemDto;
import com.kh.finalPrjAm.repository.ItemRepository;
import com.kh.finalPrjAm.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    // 제품 등록
    @PostMapping(value = "/new")
    public ResponseEntity<Boolean> itemCreate(@RequestBody Map<String, Object> data) {
        String name = (String) data.get("name");
        int price = (Integer) data.get("price");
        String detail = (String) data.get("detail");
        int stock = (Integer) data.get("stock");
        String status = (String) data.get("status"); // 문자열로 받으면 enum 타입과 동일한지 비교 해야함.
        boolean result = itemService.createItem(name, price, detail, ItemSellStatus.SELL, stock);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // 제품 조회
    @GetMapping(value = "/get")
    public ResponseEntity<List<ItemDto>> itemList(@RequestParam String name) {
        List<ItemDto> list = itemService.getItemList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
