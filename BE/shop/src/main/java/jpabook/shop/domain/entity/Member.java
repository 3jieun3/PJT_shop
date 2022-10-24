package jpabook.shop.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

//    private String password;

    private String nickname;

//    private String name;

//    @Embedded
//    private Address address;

    @Builder
    public Member(String email, String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}
