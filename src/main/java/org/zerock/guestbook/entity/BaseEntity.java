package org.zerock.guestbook.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(value = {AuditingEntityListener.class }) //엔티티의 객체가 생성.변경을 감지하는 역할, 활성화 필요
//@EnableJpaAuditing 를 Entity 에 반영해줌

abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate.", updatable = false) // regdate는 변경되지 않도록 설정
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
