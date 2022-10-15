package jpabook.shop.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String username;
    private String password;


}
