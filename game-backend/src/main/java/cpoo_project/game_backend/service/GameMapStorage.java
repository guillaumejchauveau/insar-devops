package cpoo_project.game_backend.service;

import cpoo_project.game_backend.model.GameMap;
import cpoo_project.game_backend.model.GameReplay;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Singleton
public class GameMapStorage extends AbstractStorage<GameMap> {
  public GameMapStorage() throws IOException {
    super(FileSystems.getDefault().getPath("maps"), GameMap.class);
  }

  public Stream<GameMap> stream() {
    return this.entities.values().stream();
  }

  @Override
  public Optional<GameMap> get(final String mapName) {
    return super.get(mapName);
  }

  public Optional<Stream<GameReplay>> getReplays(final String mapName) {
    return this.get(mapName).map(map -> map.getReplays().values().stream());
  }

  public Optional<GameReplay> getReplay(final String mapName, final String playerName) {
    return this.get(mapName).flatMap(map -> Optional.ofNullable(map.getReplays().get(playerName)));
  }

  public void put(final GameMap map) throws IOException {
    this.put(map.getName(), map);
  }

  public Boolean contains(final String mapName) {
    return this.entities.containsKey(mapName);
  }
}
