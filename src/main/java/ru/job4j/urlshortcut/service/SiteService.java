package ru.job4j.urlshortcut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteDbStore;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteDbStore siteDbStore;

    public Optional<Site> save(Site site) {
        return Optional.of(siteDbStore.save(site));
    }

    public List<Site> findAll() {
        return siteDbStore.findAll();
    }

    public Site findByLogin(String login) {
        return siteDbStore.findByLogin(login);
    }

    public Site findByName(String name) {
        return siteDbStore.findByName(name);
    }
}
