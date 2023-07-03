package com.bullish.controller.admin;

import com.bullish.model.BYGXPromotion;
import com.bullish.model.Product;
import com.bullish.model.Promotion;
import com.bullish.service.ProductService;
import com.bullish.service.PromotionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PromotionController.class)
class PromotionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PromotionService promotionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addPromotion() throws Exception {
        doNothing().when(promotionService).addPromotion(any());
        mockMvc.perform(post("/admin/promotions/add")
                       .content(objectMapper.writeValueAsString(new BYGXPromotion()))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }
}