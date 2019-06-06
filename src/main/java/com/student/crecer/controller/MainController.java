package com.student.crecer.controller;

import com.student.crecer.domain.Article;
import com.student.crecer.domain.User;
import com.student.crecer.repositories.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private ArticleRepo articleRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Article> articles;

        if (filter != null && !filter.isEmpty()) {
            articles = articleRepo.findByTag(filter);
        } else  {
            articles = articleRepo.findAll();
        }

        model.addAttribute("articles", articles);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Article article,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        article.setAvtor(user);

        if (bindingResult.hasErrors()) {
            Map<String,String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("article", article);
        } else {
            saveFile(article, file);

            model.addAttribute("article", null);
            articleRepo.save(article);
        }
        Iterable<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);

        return "main";
    }

    private void saveFile(@Valid Article article, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadFolder = new File(uploadPath);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resFileName));

            article.setFilename(resFileName);
        }
    }

    @GetMapping("/user-articles/{user}")
    public String userArticles(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Article article
    ) {
        Set<Article> articles = user.getArticles();

        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount",user.getSubscriptions().size());
        model.addAttribute("subscribersCount",user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("articles", articles);
        model.addAttribute("article",article);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userArticles";
    }

    @PostMapping("/user-articles/{user}")
    public String updateArticle(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Article article,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (article.getAvtor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                article.setText(text);
            }
            if (!StringUtils.isEmpty(tag)) {
                article.setTag(tag);
            }
            saveFile(article, file);
            articleRepo.save(article);
        }

        return "redirect:/user-articles/" + user;
    }

}
