package project.altynbaev;

import project.altynbaev.dto.Data;
import project.altynbaev.dto.DataDto;
import project.altynbaev.model.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listCountryTest() throws Exception {

        ResponseEntity<DataDto<List<Country>>> response = restTemplate.exchange("/countries",
                HttpMethod.GET, null, new ParameterizedTypeReference<DataDto<List<Country>>>() {
                });
        DataDto<List<Country>> data = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(data.getData().get(0).getId(), notNullValue());
        assertThat(data.getData().get(0).getCountryCode(), notNullValue());
        assertThat(data.getData().get(0).getCountryName(), notNullValue());
    }
}
