package cpoo_project.game_backend.service;

import cpoo_project.game_backend.model.GameReplay;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.FileSystems;

@Service
@Singleton
public class GameReplayStorage extends AbstractStorage<GameReplay> {
  @Inject
  public GameReplayStorage(final GameMapStorage mapStorage) throws IOException {
    super(FileSystems.getDefault().getPath("replays"), GameReplay.class);

    this.entities.forEach((key, value) -> mapStorage.get(key.split("/")[0]).ifPresent(map -> map.registerReplay(value)));
  }

  public void put(final GameReplay replay) throws IOException {
    this.put(replay.getMap().getName() + "/" + replay.getPlayerName(), replay);
  }
}
