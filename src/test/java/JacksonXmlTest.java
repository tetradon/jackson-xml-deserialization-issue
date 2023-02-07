import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tetradon.Base;
import com.tetradon.Item;
import com.tetradon.Wrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Tests are passing with Jackson 2.11.0 and failing with all subsequent versions including the latest 2.14.1
 */
class JacksonXmlTest {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    @Test
    void when_3itemsInXml_expect_3itemsInDeserializedObject() throws JsonProcessingException {
        String xmlString = """
                <?xml version="1.0" encoding="UTF-8"?>
                <wrapper type="wrapper">
                    <item><id>1</id></item>
                    <item><id>2</id></item>
                    <item><id>3</id></item>
                </wrapper>
                """;
        Base base = XML_MAPPER.readValue(xmlString, Base.class);
        Assertions.assertEquals(3, ((Wrapper)base).getItems().size());
    }

    @Test
    void when_2itemsInObject_expect_2itemsInObjectAfterRoundTripDeserializationToBaseClass() throws JsonProcessingException {
        Wrapper wrapper = new Wrapper();
        Item item1 = new Item("1");
        Item item2 = new Item("2");
        wrapper.setItems(Arrays.asList(item1, item2));

        String writeValueAsString = XML_MAPPER.writeValueAsString(wrapper);
        Base base = XML_MAPPER.readValue(writeValueAsString, Base.class);

        Assertions.assertEquals(2, ((Wrapper)base).getItems().size());
    }
}

