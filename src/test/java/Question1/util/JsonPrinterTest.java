package Question1.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

class JsonPrinterTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonPrinter jsonPrinter = new JsonPrinter(objectMapper);

    @Test
    void 객체를_JSON_문자열로_변환한다() throws JsonProcessingException {
        Map<String, Object> data = Map.of("name", "junseok", "age", 25);

        String json = jsonPrinter.toJson(data);

        String expected = """
                {
                  "name" : "junseok",
                  "age" : 25
                }""";

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(expected), mapper.readTree(json));
    }
}
