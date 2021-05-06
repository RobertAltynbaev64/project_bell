package project.altynbaev.dictionary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование DocController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DocControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Тестирование получения списка документов из справочника
     *
     * @throws Exception
     */
    @Test
    public void getList() throws Exception {
        mockMvc.perform(get("/api/docs/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Паспорт гражданина Российской Федерации"))
                .andExpect(jsonPath("$.data[1].name").value("Свидетельство о рождении"))
                .andExpect(jsonPath("$.data[2].name").value("Военный билет"));
    }
}
