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

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor

public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepo guestbookRepo;


    // 등록하기 서비스
    @Override
    public Long register(GuestbookDto dto) {

        GuestBook entity = dtoToEntity(dto);
        guestbookRepo.save(entity);
        return entity.getGno();
    }

    // 게시글 조회하기
    @Override
    public GuestbookDto read(Long gno) {
        Optional<GuestBook> result = guestbookRepo.findById(gno);
        return result.isPresent()? entityToDto(result.get()): null;
    }

    // 페이지블이 좀 어려움
    @Override
    public PageResultDto<GuestbookDto, GuestBook> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = guestbookRepo.findAll(pageable);
        Function<GuestBook, GuestbookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }

    @Override
    public void remove(Long gno) {
        guestbookRepo.deleteById(gno);
    }

    @Override
    public void modify(GuestbookDto dto) {
        Optional<GuestBook> result = guestbookRepo.findById(dto.getGno());
        if(result.isPresent()){
            GuestBook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            guestbookRepo.save(entity);
        }

    }
}