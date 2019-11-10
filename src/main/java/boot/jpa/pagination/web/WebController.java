package boot.jpa.pagination.web;

import boot.jpa.pagination.service.HeroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private HeroService heroService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("list", heroService.HeroFindAllResponse());
        return "index";
    }
}
