package ru.job4j.urlshortcut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.UrlModel;
import ru.job4j.urlshortcut.repository.UrlDbStore;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlDbStore urlDbStore;
    private final JdbcTemplate jdbcTemplate;

    public UrlModel save(UrlModel urlModel) {
        return urlDbStore.save(urlModel);
    }

    public UrlModel findByCode(String code) {
        UrlModel url = urlDbStore.findByCode(code);
        /*UrlModel query = urlDbStore.query(url.getId());*/
        if (url != null) {
            jdbcTemplate.batchUpdate(
                    "UPDATE urls SET url_id = url_id + 1 where id =" + url.getId());
        }
        return urlDbStore.findByCode(code);
    }

    public List<UrlModel> findAll() {
        return urlDbStore.findAll();
    }
}
