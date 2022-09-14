package ru.job4j.urlshortcut.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Message {
    private final boolean registration;
    private final String login;
    private final String password;
}
