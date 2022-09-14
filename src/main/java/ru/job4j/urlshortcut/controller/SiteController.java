package ru.job4j.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Message;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.util.PasswordGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SiteController {
    private final SiteService service;
    private final BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    @PostMapping("/registration")
    public ResponseEntity<Message> signUp(@RequestBody Site site) {
        site.setLogin(PasswordGenerator.generateLogin(6));
        site.setPassword(PasswordGenerator.generatePassword(8));
        String password = site.getPassword();
        site.setPassword(encoder.encode(password));
        Optional<Site> saveSite = service.save(site);
        Site getSite = saveSite.orElse(null);
        assert getSite != null;
        Message message = new Message(saveSite.isPresent(), getSite.getLogin(), password);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Site> findAll() {
        return service.findAll();
    }

    @ExceptionHandler(value = { Exception.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        log.error(e.getLocalizedMessage());
    }
}
