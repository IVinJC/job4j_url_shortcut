package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.UrlModel;

import java.util.List;

@Repository
public interface UrlDbStore extends CrudRepository<UrlModel, Integer> {

    UrlModel findByCode(String code);

   /* @Modifying(flushAutomatically = true)
    @Query("UPDATE urls u SET u.url_id = u.url_id + 1 where u.id = :fId")
    UrlModel query(@Param("fId") int fId);*/
    List<UrlModel> findAll();
}
