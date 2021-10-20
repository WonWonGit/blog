package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자를 생성
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터에 사용된다
    //섬머노트 라이브러리(html)섞여서 디자인이 됨
    private  String content;

    private int count;

    //DB는 오브젝트를 저장할수 없다. 자바는 오브젝트를 저장할 수 있다.
    @ManyToOne(fetch = FetchType.EAGER) //many=board, one=user
    @JoinColumn(name="userId")
    //FK user테이블의 id를 참조
    private User user;

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //연관관계의 주인이 아니다 DB에 컬럼 생성 안함
    @JsonIgnoreProperties({"board"}) //무한 참조 방지
    @OrderBy("id desc")
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createdate;

}
