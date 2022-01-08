package cpoo_project.game_backend.utils;

import com.github.javafaker.Faker;

import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RandomGameMapNameSupplier implements Supplier<String> {
  private final Faker faker;
  private final Random random;
  private final Predicate<String> uniquenessPredicate;

  public RandomGameMapNameSupplier(final Predicate<String> uniquenessPredicate) {
    faker = new Faker();
    random = new Random();
    this.uniquenessPredicate = uniquenessPredicate;
  }

  private static final String[] LOCATION_NAME_PATHS = {
    "dune.planets",
    "star_wars.planets",
    //"starcraft.planets",
    "stargate.planets",
    "games.elder_scrolls.region",
    "games.elder_scrolls.city",
    //"games.super_smash_bros.stage",
    "game_of_thrones.cities",
    //"games.fallout.locations",
    "games.witcher.locations",
    "games.zelda.locations",
    //"games.half_life.location",
    "harry_potter.locations",
    "one_piece.locations",
    "games.overwatch.locations",
    "twin_peaks.locations",
    "rick_and_morty.locations",
    "simpsons.locations",
    "games.pokemon.locations",
    "one_piece.islands",
    "family_guy.location",
    "hobbit.location",
    "sword_art_online.location",
    "lord_of_the_rings.location",
    "lovecraft.location",
    "league_of_legends.location",
    "star_trek.location",
    //"games.sonic_the_hedgehog.zone"
  };

  @Override
  public String get() {
    return random.ints(0, LOCATION_NAME_PATHS.length)
      .mapToObj(i -> faker.expression("#{" + LOCATION_NAME_PATHS[i] + "}"))
      .filter(uniquenessPredicate).findFirst().orElseThrow();
  }
}
