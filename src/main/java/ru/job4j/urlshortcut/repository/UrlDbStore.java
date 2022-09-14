package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.UrlModel;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UrlDbStore extends CrudRepository<UrlModel, Integer> {
    UrlModel findByCode(String code);
    List<UrlModel> findAll();
}
