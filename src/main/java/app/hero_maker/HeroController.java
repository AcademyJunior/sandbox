package app.hero_maker;

import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@Controller
public class HeroController {

    @Autowired
    private DataValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }

    @Autowired
    private HeroDao heroDao;

    @GetMapping("/heroes")
    public @ResponseBody
    List<Hero> getHeroes() {
        return heroDao.getHeroes();
    }

    @PostMapping("/heroes/add")
    public void addHero(@RequestBody @Valid Hero hero) {
        heroDao.addHero(hero);
    }

    @PostMapping("/heroes/delete")
    public void deleteHero(@RequestParam long id) {
        heroDao.deleteHero(id);
    }

    @PostMapping("heroes/update")
    public void updateHero(@RequestParam long id, @RequestBody @Valid Hero hero) {
        heroDao.updateHero(id, hero);
    }
}
