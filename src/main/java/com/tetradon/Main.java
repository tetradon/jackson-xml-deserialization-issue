package com.tetradon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        String xmlString = """
                <?xml version="1.0" encoding="UTF-8"?>
                <wrapper type="wrapper">
                    <item><id>1</id></item>
                    <item><id>2</id></item>
                    <item><id>3</id></item>
                </wrapper>
                """;
        Base base = new XmlMapper().readValue(xmlString, Base.class);
        System.out.println(base);
        System.out.println(((Wrapper)base).getItems().size()); //in Jackson version 2.11.1+ has 2 items instead of 3
    }
}
