package com.kurama.email_service.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {


    @NonNull
    private final EmailFacade facade;

    @GetMapping()
    public ResponseEntity<Page<EmailDTO>> getAll(@PageableDefault() Pageable pageable) {
        return ok().body(facade.getAll(pageable));
    }

}
