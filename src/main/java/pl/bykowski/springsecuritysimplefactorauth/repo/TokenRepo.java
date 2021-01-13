package pl.bykowski.springsecuritysimplefactorauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bykowski.springsecuritysimplefactorauth.model.Token;

public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByValue(String value);
}
