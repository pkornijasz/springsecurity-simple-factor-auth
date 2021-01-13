package pl.bykowski.springsecuritysimplefactorauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bykowski.springsecuritysimplefactorauth.model.Token;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    Optional<Token> findByValue(String value);
}
