package com.kh.finalPrjAm.controller;
import com.kh.finalPrjAm.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api") // GET 요청을 받기 위한 어노테이션
public class TestController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello, Spring Boot !!!!! ☘️";
    }
    @GetMapping("/variable1/{variable}") // 매개 변수를 전달 받을 때 사용
    public String getVariable(@PathVariable String variable) {
        return variable;
    }
    @GetMapping(value = "request1")
    public String getRequestParam(@RequestParam String user,
                                  @RequestParam String name,
                                  @RequestParam String email) {
        return user + " " + name + " " + email;
    }
    @GetMapping("/members")
    public List<Map<String, Object>> memberList() {
        List<Map<String, Object>> members = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            Map<String, Object> member = new HashMap<>();
            member.put("id", i);
            member.put("name", i + "번 개발자");
            member.put("age", 10 + i);
            members.add(member);
        }
        return members;
    }
    // @PostMapping("/member-reg")
    @RequestMapping(value = "/member-reg", method = RequestMethod.POST)
    public boolean memberReg(@RequestBody Map<String, String> data) {
        String id = data.get("user");
        String pwd = data.get("pwd");
        String name = data.get("name");
        String mail = data.get("mail");
        return true;
    }
    @GetMapping("/members2")
    public ResponseEntity<List<MemberDto>> memberList2() {
        List<MemberDto> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            MemberDto dto = new MemberDto();
            dto.setUser("user" + i);
            dto.setName("홍길동" + i);
            dto.setPwd("1234" + i);
            dto.setEmail(i+"test@naver.com");
            list.add(dto);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
