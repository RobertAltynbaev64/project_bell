package project.altynbaev.organization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import project.altynbaev.organization.service.OrganizationService;
import project.altynbaev.organization.view.OrganizationListReqView;
import project.altynbaev.organization.view.OrganizationListRespView;
import project.altynbaev.organization.view.OrganizationUpdateReqView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование OrganizationController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrganizationControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrganizationService organizationService;

    /**
     * Тестирование получение организации по одному параметру
     * @throws Exception
     */
    @Test
    public void getListByOneParameter() throws Exception {
        OrganizationListReqView testReq = new OrganizationListReqView();
        testReq.name = "Билайн";
        ResultActions resultActions = mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)));
        resultActions.andExpect(status().isOk());

        // возвращаемый результат сервис-слоем
        List<OrganizationListRespView> testResp = new ArrayList<>();
        OrganizationListRespView item = new OrganizationListRespView();
        item.id = 2L;
        item.name = "Билайн";
        item.isActive = true;
        testResp.add(item);

        // эмулируем сервис-слой с помощью mock объекта
        when(organizationService.getOrganizationByFilter(testReq)).thenReturn(testResp);
        organizationService.getOrganizationByFilter(testReq);
        mapper.writeValueAsString(organizationService.getOrganizationByFilter(testReq));

        resultActions.andExpect(jsonPath("$.data[0].id").value(testResp.get(0).id));
        resultActions.andExpect(jsonPath("$.data[0].name").value(testResp.get(0).name));
        resultActions.andExpect(jsonPath("$.data[0].isActive").value(testResp.get(0).isActive));
    }

    /**
     * Получение организации по нескольким параметрам
     * @throws Exception
     */
    @Test
    public void getListBySomeParameter() throws Exception {
        OrganizationListReqView testReq2 = new OrganizationListReqView();
        testReq2.name = "МТС";
        testReq2.isActive = false;
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("МТС"))
                .andExpect(jsonPath("$.data[0].isActive").value(false));
    }

    /**
     * Получение организации - пустой результат
     * @throws Exception
     */
    @Test
    public void getListEmptyResult() throws Exception {
        OrganizationListReqView testReq = new OrganizationListReqView();
        testReq.name = "МТС55";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    /**
     * Некорректный запрос, без обязательных парметров
     * @throws Exception
     */
    @Test
    public void getListWithoutRequiredParameters() throws Exception {
        OrganizationListReqView testReq = new OrganizationListReqView();
        testReq.isActive = true;
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(containsString("name cannot be null")));
    }

    /**
     * Некорректный запрос, не соблюдены условия валидации
     * @throws Exception
     */
    @Test
    public void getListBadRequest() throws Exception {
        OrganizationListReqView testReq = new OrganizationListReqView();
        testReq.name = "МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС_МТС";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(containsString("size must be between 2 and 50")));
    }

    /**
     * Тестирование получения организаций по id
     * @throws Exception
     */
    @Test
    public void getOrganizationById() throws Exception {
        // получение существующей организации по id
        mockMvc.perform(get("/api/organization/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Мегафон"))
                .andExpect(jsonPath("$.data.inn").value("7812014560"));

        // получение несуществующей организации по id
        mockMvc.perform(get("/api/organization/9"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Организация с таким id не найдена"));
    }

    /**
     * Тестирование обновления организации
     * @throws Exception
     */
    @Test
    public void updateOrganization() throws Exception {
        // обновление организации
        OrganizationUpdateReqView testReq = new OrganizationUpdateReqView();
        testReq.id = 1L;
        testReq.name = "МТС";
        testReq.fullName = "Публичное акционерное общество МТС";
        testReq.inn = "1458794763";
        testReq.kpp = "879542163";
        testReq.address = "г. Москва....";
        mockMvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующей организации по id
        mockMvc.perform(get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("МТС"))
                .andExpect(jsonPath("$.data.fullName").value("Публичное акционерное общество МТС"))
                .andExpect(jsonPath("$.data.inn").value("1458794763"))
                .andExpect(jsonPath("$.data.kpp").value("879542163"))
                .andExpect(jsonPath("$.data.address").value("г. Москва...."))
                .andExpect(jsonPath("$.data.isActive").value(true));
    }

    /**
     * Тестирование создания организации
     * @throws Exception
     */
    @Test
    public void saveOrganization() throws Exception {
        // добавление организации
        OrganizationUpdateReqView testReq = new OrganizationUpdateReqView();
        testReq.name = "SMART";
        testReq.fullName = "ПАО SMART";
        testReq.inn = "1458794763";
        testReq.kpp = "879542163";
        testReq.address = "г. Москва....";
        testReq.isActive = false;
        mockMvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующей организации по id
        mockMvc.perform(get("/api/organization/6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("SMART"))
                .andExpect(jsonPath("$.data.fullName").value("ПАО SMART"))
                .andExpect(jsonPath("$.data.inn").value("1458794763"))
                .andExpect(jsonPath("$.data.kpp").value("879542163"))
                .andExpect(jsonPath("$.data.address").value("г. Москва...."))
                .andExpect(jsonPath("$.data.phone").isEmpty())
                .andExpect(jsonPath("$.data.isActive").value(false));
    }
}
