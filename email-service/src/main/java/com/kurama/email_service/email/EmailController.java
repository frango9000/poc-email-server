package com.kurama.email_service.email;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    @NonNull
    private final EmailFacade facade;

    @GetMapping()
    public ResponseEntity<Page<EmailDTO>> findAll(@PageableDefault() Pageable pageable) {
        return ok().body(facade.findAll(pageable));
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<EmailDTO> findById(@PathVariable @NonNull Long emailId) {
        return ok().body(facade.findById(emailId));
    }

    @PostMapping()
    public ResponseEntity<List<EmailDTO>> create(@RequestBody List<EmailDTO> emailDTOs) {
        return ok().body(facade.create(emailDTOs));
    }

    @DeleteMapping()
    public ResponseEntity<List<EmailDTO>> deleteAllById(@RequestBody List<Long> emailIds) {
        return ok().body(facade.deleteAllById(emailIds));
    }

    @DeleteMapping("/{emailId}")
    public ResponseEntity<EmailDTO> delete(@PathVariable @NonNull Long emailId) {
        return ok().body(facade.delete(emailId));
    }

    @PatchMapping("/{emailId}")
    public ResponseEntity<EmailDTO> update(@PathVariable @NonNull Long emailId, @RequestBody EmailDTO emailDTO) {
        return ok().body(facade.update(emailId, emailDTO));
    }

}
