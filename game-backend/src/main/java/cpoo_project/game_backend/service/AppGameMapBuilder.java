package cpoo_project.game_backend.service;

import cpoo_project.game_backend.model.GameMapBuilder;
import cpoo_project.game_backend.utils.RandomEllipticalPatchGameMapStartTilesSupplier;
import cpoo_project.game_backend.utils.RandomGameMapNameSupplier;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class AppGameMapBuilder extends GameMapBuilder {
  @Inject
  public AppGameMapBuilder(final GameMapStorage mapStorage) {
    this
      .withNameSupplier(new RandomGameMapNameSupplier(candidate -> !mapStorage.contains(candidate)))
      .withStartTilesSupplier(new RandomEllipticalPatchGameMapStartTilesSupplier())
      .withDimensions(10, 10);
  }
}
