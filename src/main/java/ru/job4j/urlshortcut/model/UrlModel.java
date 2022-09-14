package ru.job4j.urlshortcut.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "urls")
public class UrlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String code;
    @Column(name = "url_id")
    private int statistic;
}
