package com.github.bmariesan.playground.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bmariesan.playground.request.PolishNotationEvaluationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DisplayName("PolishNotationResource Integration Tests")
class PolishNotationResourceIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String POLISH_NOTATIONS_SEQUENTIAL_EVALUATION_PATH = "/polish-notations/sequential-evaluation";
    private static final String POLISH_NOTATIONS_PARALLEL_EVALUATION_PATH = "/polish-notations/parallel-evaluation";

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @DisplayName("Validate integration test configuration")
    public void testSpringBootContextIsInitialized() {
        // when
        ServletContext servletContext = webApplicationContext.getServletContext();
        // then
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("polishNotationResource"));
    }

    @Test
    @DisplayName("Polish notation parallel evaluation test with valid a valid request should work")
    public void testPolishNotationParallelEvaluationShouldWorkWithAValidRequest() throws Exception {
        // given
        String json = givenAValidPolishNotationRequestJson();

        // when
        ResultActions resultActions = mockMvc.perform(
                post(POLISH_NOTATIONS_PARALLEL_EVALUATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].expression").value("+ 1 1"))
                .andExpect(jsonPath("$[0].result").value("2.00"))
                .andExpect(jsonPath("$[1].expression").value("/ 1 0"))
                .andExpect(jsonPath("$[1].result").value("error"));

    }

    @Test
    @DisplayName("Polish notation sequential evaluation test with valid a valid request should work")
    public void testPolishNotationSequentialEvaluationShouldWorkWithAValidRequest() throws Exception {
        // given
        String json = givenAValidPolishNotationRequestJson();

        // when
        ResultActions resultActions = mockMvc.perform(
                post(POLISH_NOTATIONS_SEQUENTIAL_EVALUATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].expression").value("+ 1 1"))
                .andExpect(jsonPath("$[0].result").value("2.00"))
                .andExpect(jsonPath("$[1].expression").value("/ 1 0"))
                .andExpect(jsonPath("$[1].result").value("error"));

    }

    @Test
    @DisplayName("Polish notation parallel evaluation test with empty request should return empty response")
    public void testPolishNotationParallelEvaluationShouldWorkWithEmptyValidJson() throws Exception {
        // given
        String json = "{\"expressions\":[]}";

        // when
        ResultActions resultActions = mockMvc.perform(
                post(POLISH_NOTATIONS_PARALLEL_EVALUATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content()

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

    }

    @Test
    @DisplayName("Polish notation sequential evaluation test with empty request should return empty response")
    public void testPolishNotationSequentialEvaluationShouldWorkWithEmptyValidJson() throws Exception {
        // given
        String json = "{\"expressions\":[]}";

        // when
        ResultActions resultActions = mockMvc.perform(
                post(POLISH_NOTATIONS_SEQUENTIAL_EVALUATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

    }


    private String givenAValidPolishNotationRequestJson() throws JsonProcessingException {
        PolishNotationEvaluationRequest polishNotationEvaluationRequest = new PolishNotationEvaluationRequest();
        polishNotationEvaluationRequest.setExpressions(List.of("+ 1 1", "/ 1 0"));

        return polishNotationRequestToJson(polishNotationEvaluationRequest);
    }

    private String polishNotationRequestToJson(PolishNotationEvaluationRequest polishNotationEvaluationRequest) throws JsonProcessingException {
        return objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(polishNotationEvaluationRequest);
    }
}