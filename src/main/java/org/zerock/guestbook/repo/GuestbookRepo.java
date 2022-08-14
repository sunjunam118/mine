package org.zerock.guestbook.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.guestbook.entity.GuestBook;

public interface GuestbookRepo extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {
}
