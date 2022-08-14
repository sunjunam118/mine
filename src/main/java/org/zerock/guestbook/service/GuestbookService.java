package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.dto.PageResultDto;
import org.zerock.guestbook.entity.GuestBook;

public interface GuestbookService {
    Long register(GuestbookDto dto);

    PageResultDto<GuestbookDto, GuestBook> getList(PageRequestDto requestDto);

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
