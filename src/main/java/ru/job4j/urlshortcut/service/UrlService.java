package ru.job4j.urlshortcut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.UrlModel;
import ru.job4j.urlshortcut.repository.UrlDbStore;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlDbStore urlDbStore;

    public UrlModel save(UrlModel urlModel) {
        return urlDbStore.save(urlModel);
    }

    public UrlModel findByCode(String code) {
        return urlDbStore.findByCode(code);
    }
}
