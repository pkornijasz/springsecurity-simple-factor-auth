package pl.bykowski.springsecuritysimplefactorauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bykowski.springsecuritysimplefactorauth.model.AppUser;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
