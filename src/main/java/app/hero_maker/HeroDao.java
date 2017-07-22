package app.hero_maker;

import org.jooq.DSLContext;

import static org.jooq.util.maven.example.tables.Heroes.HEROES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HeroDao {

    @Autowired
    private DSLContext dslContext;

    public List<Hero> getHeroes() {
        return dslContext.selectFrom(HEROES).fetchInto(Hero.class);
    }

    public void addHero(Hero hero) {
        dslContext.insertInto(HEROES, HEROES.NAME, HEROES.STRENGTH, HEROES.AGILITY, HEROES.INTELLIGENCE)
                .values(hero.getName(), hero.getStrength(), hero.getAgility(), hero.getIntelligence())
                .execute();
    }


    public void deleteHero(long id) {
        dslContext.deleteFrom(HEROES)
                .where(HEROES.ID.eq(id))
                .execute();
    }

    public void updateHero(long id, Hero hero) {
        dslContext.update(HEROES)
                .set(HEROES.NAME, hero.getName())
                .set(HEROES.STRENGTH, hero.getStrength())
                .set(HEROES.AGILITY, hero.getAgility())
                .set(HEROES.INTELLIGENCE, hero.getIntelligence())
                .where(HEROES.ID.eq(id))
                .execute();
    }
}


