package cz.jalasoft.runner.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.jalasoft.myhealth.endpoint.UserResource;

import java.io.IOException;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/25/15.
 */
public final class Util {

    static byte[] serialize(UserResource resource) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] result = mapper.writeValueAsBytes(resource);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return result;
    }


    static String serializeToString(UserResource resource) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(resource);
        return result;
    }

    static <T> T deserialize(byte[] data, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, type);
    }
}
