package project.altynbaev.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.altynbaev.user.view.UserListReqView;
import project.altynbaev.user.view.UserSaveReqView;
import project.altynbaev.user.view.UserUpdateReqView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование UserController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /**
     * Тестирование получения пользователей по различным параметрам
     * @throws Exception
     */
    @Test
    public void getList() throws Exception {
        // получение офисов организации по одному параметру
        UserListReqView testReq = new UserListReqView();
        testReq.officeId = 1L;
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].firstName").value("Регина"))
                .andExpect(jsonPath("$.data[1].firstName").value("Сергей"));
    }

    /**
     * Тестирование получения пользователя по id
     * @throws Exception
     */
    @Test
    public void getUserById() throws Exception {
        // получение существующего офиса по id
        mockMvc.perform(get("/api/user/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.firstName").value("Валерия"))
                .andExpect(jsonPath("$.data.secondName").value("Иванова"))
                .andExpect(jsonPath("$.data.middleName").value("Климовна"))
                .andExpect(jsonPath("$.data.position").value("Менеджер"))
                .andExpect(jsonPath("$.data.isIdentified").value("false"));

        // получение несуществующего офиса по id
        mockMvc.perform(get("/api/user/15"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Пользователь с таким id не найден"));
    }

    /**
     * Тестирование обновления пользователя
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception {
        // обновление пользователя
        UserUpdateReqView testReq = new UserUpdateReqView();
        testReq.id = 1L;
        testReq.firstName = "Регина";
        testReq.position = "Продавец-консультант";
        testReq.docName = "Свидетельство о рождении";
        testReq.citizenshipCode = 643;
        mockMvc.perform(post("/api/user/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующего пользователя по id
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.firstName").value("Регина"))
                .andExpect(jsonPath("$.data.secondName").value("Антипина"))
                .andExpect(jsonPath("$.data.middleName").value("Вячеславовна"))
                .andExpect(jsonPath("$.data.position").value("Продавец-консультант"))
                .andExpect(jsonPath("$.data.phone").value("89174587589"))
                .andExpect(jsonPath("$.data.docName").value("Свидетельство о рождении"))
                .andExpect(jsonPath("$.data.docNumber").value("789632"))
                .andExpect(jsonPath("$.data.docDate").value("01.03.1995"))
                .andExpect(jsonPath("$.data.citizenshipName").value("Россия"))
                .andExpect(jsonPath("$.data.citizenshipCode").value("643"))
                .andExpect(jsonPath("$.data.isIdentified").value("true"));
    }

    /**
     * Тестирование создания пользователя
     * @throws Exception
     */
    @Test
    public void saveUser() throws Exception {
        // создание пользователя
        UserSaveReqView testReq = new UserSaveReqView();
        testReq.officeId = 2L;
        testReq.firstName = "Иван";
        testReq.position = "Продавец-консультант";
        testReq.docCode = 7;
        testReq.citizenshipCode = 112;
        mockMvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующего пользователя по id
        mockMvc.perform(get("/api/user/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(9))
                .andExpect(jsonPath("$.data.firstName").value("Иван"))
                .andExpect(jsonPath("$.data.secondName").isEmpty())
                .andExpect(jsonPath("$.data.middleName").isEmpty())
                .andExpect(jsonPath("$.data.position").value("Продавец-консультант"))
                .andExpect(jsonPath("$.data.phone").isEmpty())
                .andExpect(jsonPath("$.data.docName").value("Военный билет"))
                .andExpect(jsonPath("$.data.docNumber").isEmpty())
                .andExpect(jsonPath("$.data.docDate").isEmpty())
                .andExpect(jsonPath("$.data.citizenshipName").value("Беларусь"))
                .andExpect(jsonPath("$.data.citizenshipCode").value("112"))
                .andExpect(jsonPath("$.data.isIdentified").isEmpty());
    }

    /**
     * Тестирование неудачного создания пользователя
     * @throws Exception
     */
    @Test
    public void saveUserFail() throws Exception {
        // создание пользователя
        UserSaveReqView testReq = new UserSaveReqView();
        testReq.officeId = 2L;
        testReq.firstName = "Иван";
        testReq.position = "Продавец-консультант";
        testReq.docCode = 55; // несуществующий код документа
        testReq.citizenshipCode = 112;
        mockMvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Документ с таким кодом не найден"));
    }
}
