package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.UrlModel;
@Repository
public interface UrlDbStore extends CrudRepository<UrlModel, Integer> {
    UrlModel findByCode(String code);
}
