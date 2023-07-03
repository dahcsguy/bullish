package com.bullish.controller.user;

import com.bullish.exception.CartNotFoundException;
import com.bullish.model.Cart;
import com.bullish.model.CartItem;
import com.bullish.model.Product;
import com.bullish.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {


    @MockBean
    CartService cartService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createCart() throws Exception {
        doNothing().when(cartService).createCart();
        mockMvc.perform(post("/carts")
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    void getCart_success() throws Exception {
        when(cartService.getCartById(anyInt())).thenReturn(Optional.of(new Cart()));
        mockMvc.perform(get("/carts/1")
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }
    @Test
    void getCart_fail() throws Exception {
        doNothing().when(cartService).createCart();
        mockMvc.perform(get("/carts/1")
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

    @Test
    void addItem_success() throws Exception {
        doNothing().when(cartService).addToCart(anyInt(), any());
        mockMvc.perform(post("/carts/1/add")
                       .content(objectMapper.writeValueAsString(new CartItem()))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }
    @Test
    void addItem_fail() throws Exception {
        doThrow(CartNotFoundException.class).when(cartService).addToCart(anyInt(), any());
        mockMvc.perform(post("/carts/1/add")
                       .content(objectMapper.writeValueAsString(new CartItem()))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

    @Test
    void updateItem_success() throws Exception {
    }
    @Test
    void updateItem_fail() throws Exception {
    }
    @Test
    void deleteItem_success() {
    }
    @Test
    void deleteItem_fail() {
    }
}