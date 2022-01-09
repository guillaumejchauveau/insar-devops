package cpoo_project.game_backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

class RandomGameMapNameSupplierTest {
  @Test
  void test() {
    Assertions.assertDoesNotThrow(() -> {
      final var supplier = new RandomGameMapNameSupplier(candidate -> true);
      Assertions.assertNotNull(supplier.get());
    });
    Assertions.assertDoesNotThrow(() -> {
      AtomicBoolean state = new AtomicBoolean(true);
      var uniquenessPredicate = Mockito.spy(new Predicate<String>() {
        @Override
        public boolean test(String s) {
          state.set(!state.get());
          return state.get();
        }
      });
      final var supplier = new RandomGameMapNameSupplier(uniquenessPredicate);
      Assertions.assertNotNull(supplier.get());
      Mockito.verify(uniquenessPredicate, Mockito.times(2)).test(Mockito.any());
    });
  }
}
