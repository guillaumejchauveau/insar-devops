package cpoo_project.game_backend;

import cpoo_project.game_backend.service.GameMapStorage;
import cpoo_project.game_backend.service.GameReplayStorage;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationBinder extends AbstractBinder {
  @Override
  protected void configure() {
    bind(GameMapStorage.class).to(GameMapStorage.class);
    bind(GameReplayStorage.class).to(GameReplayStorage.class);
  }
}
