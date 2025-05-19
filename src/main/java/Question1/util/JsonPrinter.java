package Question1.util;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UncheckedIOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonPrinter {
    private final ObjectMapper mapper;

    public String toJson(Object object) {
        try {
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            DefaultIndenter indenter = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
            prettyPrinter.indentArraysWith(indenter);
            prettyPrinter.indentObjectsWith(indenter);

            return mapper.writer(prettyPrinter).writeValueAsString(object);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
