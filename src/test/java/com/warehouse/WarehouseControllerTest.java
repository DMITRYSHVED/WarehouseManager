package com.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStorageController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/storage")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAdminController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUserController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeliveryController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/deliveries")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testProductOrderController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/productOrder")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testProductController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/productBase")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testProviderController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/provider/providers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
