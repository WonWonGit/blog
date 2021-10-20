package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자를 생성
@Builder
@Entity // 테이블화
//@DynamicInsert//인서트시 널인값은 디폴트 값으로 들어간다
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB의 넘버링 전략을 따라간다
    private int id; //시퀀스

    @Column(nullable = false,length = 100, unique = true) //notnull
    private String username; //아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("user")
    //DB에는 enum타입이 없기 때문에 타입 명시를 해 줘야 한다
    @Enumerated(EnumType.STRING)
    private  RoleType role; //ex) Admin, user, manager

    private String oauth; //카카오 로그인 사용자

    @CreationTimestamp
    private Timestamp createDate;

}

