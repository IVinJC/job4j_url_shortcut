package ru.job4j.urlshortcut.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.UrlModelDTO;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.UrlModel;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UrlService;
import ru.job4j.urlshortcut.util.PasswordGenerator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    private final SiteService siteService;

    @PostMapping("/convert")
    public ResponseEntity<UrlModelDTO> convert(@RequestBody UrlModel urlModel) throws MalformedURLException {
        String urlString = urlModel.getUrl();
        URL url = new URL(urlString);
        String loginSite = url.getHost();
        Site site = siteService.findByName(loginSite);
        if (site == null) {
            throw new UsernameNotFoundException(loginSite);
        }
        urlModel.setCode(PasswordGenerator.generatePassword(12));
        urlService.save(urlModel);
        UrlModelDTO urlModelDTO = new UrlModelDTO();
        urlModelDTO.setCode(urlModel.getCode());
        return new ResponseEntity<>(urlModelDTO, HttpStatus.OK);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable("code") String code) {
        UrlModel urlModel = urlService.findByCode(code);
        if (urlModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                new MultiValueMapAdapter<>(
                        Map.of("HTTP CODE", List.of("302 REDIRECT " + urlModel.getUrl()))), HttpStatus.FOUND);
    }

    @GetMapping("/statistic")
    public List<ResponseEntity<HashMap<String, Object>>> statistic() {
        List<ResponseEntity<HashMap<String, Object>>> list = new ArrayList<>();
        List<UrlModel> listUrl = urlService.findAll();
        ResponseEntity<HashMap<String, Object>> response;
        for (UrlModel urlModel : listUrl) {
            list.add(ResponseEntity.status(HttpStatus.OK).body(new HashMap<>() {{
                put("total", urlModel.getStatistic());
                put("url", urlModel.getUrl());
            }}));
        }
        return list;
    }
}
