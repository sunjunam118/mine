package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.dto.PageResultDto;
import org.zerock.guestbook.entity.GuestBook;

public interface GuestbookService {
    Long register(GuestbookDto dto);

    GuestbookDto read(Long gno);

    PageResultDto<GuestbookDto, GuestBook> getList(PageRequestDto requestDto);

    void remove(Long gno);

    void modify(GuestbookDto dto);


    // 이렇게 해야 되는 이유를 잘 모르겟음
    default GuestBook dtoToEntity(GuestbookDto dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDto entityToDto(GuestBook entity) {
        GuestbookDto dto = GuestbookDto.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())

                .build();

        return dto;
    }
}
