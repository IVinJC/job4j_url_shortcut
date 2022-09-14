package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;

@Repository
public interface SiteDbStore extends CrudRepository<Site, Integer> {
    Site findByName(String name);
    Site findByLogin(String login);

    List<Site> findAll();
}
