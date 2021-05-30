package project.altynbaev;

import project.altynbaev.dto.Data;
import project.altynbaev.dto.DataDto;
import project.altynbaev.dto.office.OfficeFilterInDto;
import project.altynbaev.dto.office.OfficeFilterOutDto;
import project.altynbaev.dto.office.OfficeSaveDto;
import project.altynbaev.dto.office.OfficeUpdateAndGetDto;
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
public class OfficeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveOfficeTest() throws Exception {
        OfficeSaveDto officeSaveDto = new OfficeSaveDto(2, "qwer", "asdf", null, null);
        ResponseEntity<Data> response = restTemplate.postForEntity("/office/save", officeSaveDto, Data.class);
        OfficeSaveDto officeSaveDto1 = new OfficeSaveDto(2, null, null, null, null);
        ResponseEntity<Data> response1 = restTemplate.postForEntity("/office/save", officeSaveDto1, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response1.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().getData().toString(), is("{result=success}"));
    }

    @Test
    public void updateOfficeTest() throws Exception {
        OfficeUpdateAndGetDto officeUpdateAndGetDto = new OfficeUpdateAndGetDto(1, "Qwer", "asdf", null, null);
        ResponseEntity<Data> response = restTemplate.postForEntity("/office/update", officeUpdateAndGetDto, Data.class);
        OfficeUpdateAndGetDto officeUpdateAndGetDto1 = new OfficeUpdateAndGetDto(1, null, "asdf", null, null);
        ResponseEntity<Data> response1 = restTemplate.postForEntity("/office/update", officeUpdateAndGetDto1, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response1.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().getData().toString(), is("{result=success}"));
    }

    @Test
    public void getOrganizationTest() throws Exception {
        int id = 1;
        ResponseEntity<DataDto<OfficeUpdateAndGetDto>> response1 = restTemplate.exchange("/office/1",
                HttpMethod.GET, null, new ParameterizedTypeReference<DataDto<OfficeUpdateAndGetDto>>() {
                });
        DataDto<OfficeUpdateAndGetDto> data = response1.getBody();
        ResponseEntity<Data> response = restTemplate.getForEntity("/office/{id}", Data.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(data.getData().getName(), notNullValue());
        assertThat(data.getData().getId(), notNullValue());
    }

    @Test
    public void listOfficeTest() throws Exception {
        OfficeFilterInDto officeFilterInDto = new OfficeFilterInDto(1, "Ромашка", null, null);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<OfficeFilterInDto> entity = new HttpEntity<OfficeFilterInDto>(officeFilterInDto, headers);
        ResponseEntity<DataDto<List<OfficeFilterOutDto>>> response1 = restTemplate.exchange("/organization/list",
                HttpMethod.POST, entity, new ParameterizedTypeReference<DataDto<List<OfficeFilterOutDto>>>() {
                });
        DataDto<List<OfficeFilterOutDto>> data = response1.getBody();
        ResponseEntity<Data> response = restTemplate.postForEntity("/organization/list", officeFilterInDto, Data.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(data.getData().get(0).getName(), notNullValue());
        assertThat(data.getData().get(0).getId(), notNullValue());

    }
}
