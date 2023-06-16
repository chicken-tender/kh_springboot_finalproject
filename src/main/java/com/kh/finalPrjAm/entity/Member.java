package com.kh.finalPrjAm.entity;
import com.kh.finalPrjAm.constant.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // JPA에 Entity 클래스 임을 알려줌, ✴️DB 테이블로 만들어 져야 할 클래스
@Table(name = "member") // DB는 🐍Snake 표기법이고 Java는 🐫Camel 표기법이기 때문에 다를 수 있기 때문에 테이블 이름을 '명시적'으로 정해줌
                        // 왜? 자바는 대소문자를 구분하고 db는 안하기 때문에
@Getter @Setter @ToString
public class Member {
    @Id // 해당 필드가 primary key 임을 지정
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId; // user_id로 변환됨
    private String name;
    private String password;
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String user, String email, String password, String name, Authority authority) {
        this.userId = user;
        this.email = email;
        this.password = password;
        this.name = name;
        this.authority = authority;
    }

    public Member() {

    }
}
