package cpoo_project.game_backend.service;

import cpoo_project.game_backend.model.CityTile;
import cpoo_project.game_backend.model.GameMove;
import cpoo_project.game_backend.model.GameReplay;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

@Service
@Singleton
public class GameReplayStorage extends AbstractStorage<GameReplay> {
  @Inject
  public GameReplayStorage(final GameMapStorage mapStorage) throws JAXBException, IOException {
    super(FileSystems.getDefault().getPath("replays"), List.of(GameReplay.class, GameMove.class, CityTile.class));

    this.entities.forEach((key, value) -> mapStorage.get(key.split("/")[0]).ifPresent(map -> map.registerReplay(value)));
  }

  public void put(final GameReplay replay) throws JAXBException, IOException {
    this.put(replay.getMap().getName() + "/" + replay.getPlayerName(), replay);
  }
}
