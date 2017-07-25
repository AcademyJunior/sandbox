package app.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/heroes/")
@RestController
public class HeroController {

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private HeroValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping
    public List<Hero> getHeroes() {
        return heroDao.getHeroes();
    }

    @GetMapping("/{heroId}")
    public Hero getHero(@PathVariable int heroId) {
        return heroDao.getHero(heroId);
    }

    @PostMapping
    public void addHero(@RequestBody @Valid Hero hero) {
        heroDao.addHero(hero);
    }

    @DeleteMapping("/{heroId}")
    public void deleteHero(@PathVariable int heroId) {
        heroDao.deleteHero(heroId);
    }

    @PutMapping("/{heroId}")
    public void updateHero(@PathVariable long heroId, @RequestBody @Valid Hero hero) {
        heroDao.updateHero(heroId, hero);
    }
}
