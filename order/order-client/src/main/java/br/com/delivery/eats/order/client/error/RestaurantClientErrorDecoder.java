package br.com.delivery.eats.order.client.error;

import br.com.delivery.eats.order.client.dto.RestaurantClientErrorResponseDTO;
import br.com.delivery.eats.order.client.exception.GenericBusinessException;
import br.com.delivery.eats.order.client.exception.RestaurantClientException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;

public class RestaurantClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey,final Response response) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String responseString = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            final RestaurantClientErrorResponseDTO restaurantClientErrorResponseDTO = mapper.readValue(responseString, RestaurantClientErrorResponseDTO.class);

            return switch (HttpStatus.valueOf(restaurantClientErrorResponseDTO.code())) {
                case NOT_FOUND, BAD_REQUEST -> new GenericBusinessException(restaurantClientErrorResponseDTO.message());
                default -> new RestaurantClientException("Restaurant Client error " + restaurantClientErrorResponseDTO);
            };

        } catch (Exception e) {
            return new RestaurantClientException(String.format("Restaurant Client error %s", FeignException.errorStatus(methodKey, response)));
        }
    }
}
