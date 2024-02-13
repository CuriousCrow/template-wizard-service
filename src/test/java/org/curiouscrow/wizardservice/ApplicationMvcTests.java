package org.curiouscrow.wizardservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void requestExistingTemplateForm() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/templates/form/{templateName}", "simple_maven"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<h1>Simple Java Maven Project</h1>")))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        Assertions.assertNotNull(modelAndView);
        Assertions.assertEquals("templateForm", modelAndView.getViewName());
        Assertions.assertTrue(modelAndView.getModel().containsKey("info"));
    }

    @Test
    void requestNonExistingTemplateForm() throws Exception {
        mockMvc.perform(get("/templates/form/{templateName}", "bla-bla-bla"))
//                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
