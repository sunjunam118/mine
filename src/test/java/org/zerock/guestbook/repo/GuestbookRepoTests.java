package org.zerock.guestbook.repo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.entity.GuestBook;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepoTests {

    @Autowired
    private GuestbookRepo guestbookRepo;

    @Test
    public void updateTest() {
        Optional<GuestBook> result = guestbookRepo.findById(259L);

        if(result.isPresent()) {
            GuestBook guestBook = result.get();

            guestBook.changeContent("Change Title...");
            guestBook.changeContent("Change Content...");

            guestbookRepo.save(guestBook);
        }
    }


    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1,300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .title("Title...."+i)
                    .content("Content..." +i)
                    .writer("user" + (i%10))
                    .build();
            System.out.println(guestbookRepo.save(guestBook));

        });

    }
}
