package org.zerock.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.service.GuestbookService;


@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor

public class GuestbookController {

    private final GuestbookService guestbookService;

    // List 보기
    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("result", guestbookService.getList(pageRequestDto));
    }


    //등록하기 controller

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String registerPost(GuestbookDto guestbookDto, RedirectAttributes redirectAttributes) {
        Long gno = guestbookService.register(guestbookDto);
        redirectAttributes.addFlashAttribute("msg",gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        GuestbookDto dto = guestbookService.read(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes) {

        guestbookService.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestbookDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto, RedirectAttributes redirectAttributes) {

        guestbookService.modify(dto);

        redirectAttributes.addFlashAttribute("page", requestDto.getPage());
        redirectAttributes.addFlashAttribute("gno",dto.getGno());

        return "redirect:/guestbook/read";
    }

}
