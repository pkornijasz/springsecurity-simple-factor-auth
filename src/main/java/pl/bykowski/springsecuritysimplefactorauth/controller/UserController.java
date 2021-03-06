package pl.bykowski.springsecuritysimplefactorauth.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.springsecuritysimplefactorauth.model.AppUser;
import pl.bykowski.springsecuritysimplefactorauth.model.Token;
import pl.bykowski.springsecuritysimplefactorauth.repo.AppUserRepo;
import pl.bykowski.springsecuritysimplefactorauth.repo.TokenRepo;
import pl.bykowski.springsecuritysimplefactorauth.service.UserService;

import java.security.Principal;
import java.util.Collection;

//@RestController - mapuje do wartości zwacanej
@Controller //mapuje do pliku!
public class UserController {

    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;

    public UserController(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

//    for REST value returned
//    @GetMapping("/hello")
//    @ResponseBody //dla @Controler przełącza mapowanie do wartości zwracanej
//    public String hello() {
//        return "hello";
//    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "hello";
    }

//    @GetMapping("/for-user")
//    public String forUser() {
//        return "hello";
//    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new AppUser());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "sign-up";
    }

    @GetMapping("/token")
    public String signup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value).get();
        AppUser appUser = byValue.getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
        return "sign-up";
    }

}
