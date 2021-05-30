package project.altynbaev;

import project.altynbaev.dto.Data;
import project.altynbaev.dto.DataDto;
import project.altynbaev.model.Doc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listDocTest() throws Exception {

        ResponseEntity<DataDto<List<Doc>>> response = restTemplate.exchange("/docs",
                HttpMethod.GET, null, new ParameterizedTypeReference<DataDto<List<Doc>>>() {
                });
        DataDto<List<Doc>> data = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(data.getData().get(0).getId(), notNullValue());
        assertThat(data.getData().get(0).getDocCode(), notNullValue());
        assertThat(data.getData().get(0).getDocName(), notNullValue());

    }
}
