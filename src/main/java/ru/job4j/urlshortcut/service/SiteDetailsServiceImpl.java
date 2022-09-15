package ru.job4j.urlshortcut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class SiteDetailsServiceImpl implements UserDetailsService {
    private final SiteService siteService;

    @Override
    public UserDetails loadUserByUsername(String siteName) throws UsernameNotFoundException {
        Site site = siteService.findByName(siteName);
        if (site == null) {
            throw new UsernameNotFoundException(siteName);
        }
        return new User(site.getLogin(), site.getPassword(), emptyList());
    }
}
