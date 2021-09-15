package game.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestMarshalling {
	<T> void marshall(final T obj, final Class<T> type) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonMap = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

		System.out.println(jsonMap);

		final T o = mapper.readValue(jsonMap, type);
		System.out.println(o);
	}

	<T> void marshallList(final List<T> obj, final Class<T> type) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonMap = mapper
			.writerWithDefaultPrettyPrinter()
			.writeValueAsString(obj);

		System.out.println(jsonMap);

		final List<T> os = mapper.readValue(jsonMap, mapper.getTypeFactory().constructCollectionType(List.class, type));
		System.out.println(os);
	}


	@Test
	void testScoreMarshalling() { // throws JsonProcessingException {
//		final var foo = new Foo(1000, "foo", "map1");
//		marshall(foo, Foo.class);
	}

	@Test
	void testListFoo() { // throws JsonProcessingException {
//		final List<Foo> scores = List.of(
//			new Foo(1000, "foo", "map1"),
//			new Foo(2000, "bar", "map2")
//		);
//		marshallList(scores, Foo.class);
	}
}
