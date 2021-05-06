package project.altynbaev.office;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.altynbaev.office.view.OfficeListReqView;
import project.altynbaev.office.view.OfficeSaveReqView;
import project.altynbaev.office.view.OfficeUpdateReqView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование OfficeController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfficeControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /**
     * Тестирование получения офисов по различным параметрам
     * @throws Exception
     */
    @Test
    public void getList() throws Exception {
        // получение офисов организации по одному параметру
        OfficeListReqView testReq = new OfficeListReqView();
        testReq.orgId = 1L;
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Салон-магазин МТС на Шафиева"))
                .andExpect(jsonPath("$.data[1].name").value("Салон-магазин МТС в ТРЦ Июнь"));
    }

    /**
     * Тестирование получения офисов по id
     * @throws Exception
     */
    @Test
    public void getOfficeById() throws Exception {
        // получение существующего офиса по id
        mockMvc.perform(get("/api/office/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Салон-магазин Билайн на Айской"))
                .andExpect(jsonPath("$.data.isActive").value(false));

        // получение несуществующего офиса по id
        mockMvc.perform(get("/api/office/9"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Офис с таким id не найден"));
    }

    /**
     * Тестирование обновления офиса
     * @throws Exception
     */
    @Test
    public void updateOffice() throws Exception {
        // обновление офиса
        OfficeUpdateReqView testReq = new OfficeUpdateReqView();
        testReq.id = 2L;
        testReq.name = "Салон-магазин МТС в ТРЦ Июнь";
        testReq.address = "г. Уфа....";
        mockMvc.perform(post("/api/office/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующего офиса по id
        mockMvc.perform(get("/api/office/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(2L))
                .andExpect(jsonPath("$.data.name").value("Салон-магазин МТС в ТРЦ Июнь"))
                .andExpect(jsonPath("$.data.address").value("г. Уфа...."))
                .andExpect(jsonPath("$.data.isActive").value(true));
    }

    /**
     * Тестирование создания офиса
     * @throws Exception
     */
    @Test
    public void saveOrganization() throws Exception {
        // создание офиса
        OfficeSaveReqView testReq = new OfficeSaveReqView();
        testReq.orgId = 2L;
        testReq.name = "Салон Билайн на Проспекте";
        mockMvc.perform(post("/api/office/save").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующего офиса по id
        mockMvc.perform(get("/api/office/8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(8))
                .andExpect(jsonPath("$.data.name").value("Салон Билайн на Проспекте"))
                .andExpect(jsonPath("$.data.address").isEmpty())
                .andExpect(jsonPath("$.data.phone").isEmpty())
                .andExpect(jsonPath("$.data.isActive").isEmpty());
    }
}
