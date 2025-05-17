package Question1.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UncheckedIOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonPrinter {
    private final ObjectMapper mapper;

    public String toJson(Object o) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
