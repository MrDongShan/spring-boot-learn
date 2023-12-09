package com.mrdongshan.elasticsearch.config;

/**
 * @author CHENPrime-coder <chenbprime@outlook.com>
 */
public class HotelConstants {

    public static final String MAPPING_TEMPLATE = "{\n" +
            "\"mappings\": {\n" +
            "\"properties\": {\n" +
            "\"id\": {\n" +
            "\"type\": \"keyword\"\n" +
            "},\n" +
            "\"name\": {\n" +
            "\"type\": \"text\",\n" +
            "\"analyzer\": \"ik_max_word\",\n" +
            "\"copy_to\": \"all\"\n" +
            "},\n" +
            "\"address\": {\n" +
            "\"type\": \"keyword\",\n" +
            "\"index\": false\n" +
            "},\n" +
            "\"price\": {\n" +
            "\"type\": \"integer\"\n" +
            "},\n" +
            "\"score\": {\n" +
            "\"type\": \"integer\"\n" +
            "},\n" +
            "\"brand\": {\n" +
            "\"type\": \"keyword\",\n" +
            "\"copy_to\": \"all\"\n" +
            "},\n" +
            "\"city\": {\n" +
            "\"type\": \"keyword\",\n" +
            "\"copy_to\": \"all\"\n" +
            "},\n" +
            "\"starName\": {\n" +
            "\"type\": \"keyword\",\n" +
            "\"copy_to\": \"all\"\n" +
            "},\n" +
            "\"business\": {\n" +
            "\"type\": \"keyword\",\n" +
            "\"copy_to\": \"all\"\n" +
            "},\n" +
            "\"location\": {\n" +
            "\"type\": \"geo_point\"\n" +
            "},\n" +
            "\"pic\": {\n" +
            "\"type\": \"keyword\",\n" +
            "\"index\": false\n" +
            "},\n" +
            "\"all\": {\n" +
            "\"type\": \"text\",\n" +
            "\"analyzer\": \"ik_max_word\"\n" +
            "}\n" +
            "}\n" +
            "}\n" +
            "}";
    public static final String USER_MAPPING = "{\n" +
            "\"mappings\": {\n" +
            "\"properties\": {\n" +
            "\"info\": {\n" +
            "\"type\": \"text\",\n" +
            "\"analyzer\": \"ik_smart\"\n" +
            "},\n" +
            "\"email\": {\n" +
            "\"index\": false,\n" +
            "\"type\": \"keyword\"\n" +
            "},\n" +
            "\"name\": {\n" +
            "\"type\": \"object\",\n" +
            "\"properties\": {\n" +
            "\"firstName\": {\n" +
            "\"type\": \"keyword\"\n" +
            "},\n" +
            "\"lastName\": {\n" +
            "\"type\": \"keyword\"\n" +
            "}\n" +
            "}\n" +
            "}\n" +
            "}\n" +
            "}\n" +
            "}";
}