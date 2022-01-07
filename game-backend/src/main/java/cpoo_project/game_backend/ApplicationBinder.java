package cpoo_project.game_backend;

import cpoo_project.game_backend.service.GameMapStorage;
import cpoo_project.game_backend.service.GameReplayStorage;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ApplicationBinder extends AbstractBinder {
  @Override
  protected void configure() {
    try {
      bind(new GameMapStorage()).to(GameMapStorage.class);
      bind(GameReplayStorage.class).to(GameReplayStorage.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
