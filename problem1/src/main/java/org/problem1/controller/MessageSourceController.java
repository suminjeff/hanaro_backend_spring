package org.problem1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class MessageSourceController {

    private final MessageSource messageSource;

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        log.info("현재 Locale 정보 : {}", LocaleContextHolder.getLocale());
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}