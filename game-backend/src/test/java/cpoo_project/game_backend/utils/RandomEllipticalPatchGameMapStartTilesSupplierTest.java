package cpoo_project.game_backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomEllipticalPatchGameMapStartTilesSupplierTest {

  @Test
  void test() {
    Assertions.assertDoesNotThrow(() -> {
      var supplier = new RandomEllipticalPatchGameMapStartTilesSupplier();
      var tiles = supplier.apply(10, 10);
      Assertions.assertNotNull(tiles);
      Assertions.assertEquals(100, tiles.size());
    });
  }
}
