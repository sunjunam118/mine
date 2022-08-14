package org.zerock.guestbook.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.dto.PageResultDto;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.repo.GuestbookRepo;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor

public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepo guestbookRepo;

    @Override
    public Long register(GuestbookDto dto) {

        GuestBook entity = dtoToEntity(dto);
        guestbookRepo.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDto<GuestbookDto, GuestBook> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = guestbookRepo.findAll(pageable);
        Function<GuestBook, GuestbookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}