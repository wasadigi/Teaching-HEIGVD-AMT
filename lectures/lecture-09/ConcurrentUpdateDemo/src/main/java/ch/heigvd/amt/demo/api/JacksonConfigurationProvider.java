package ch.heigvd.amt.demo.api;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Olivier Liechti
 */
@Provider
public class JacksonConfigurationProvider implements ContextResolver<ObjectMapper> {

  private ObjectMapper mapper = new ObjectMapper();

  public JacksonConfigurationProvider() {
    SerializationConfig serConfig = mapper.getSerializationConfig();
    DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }

}
