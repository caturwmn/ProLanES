package id.ac.ui.cs.advprog.eshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.ArrayList;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductServiceImpl productService;

  @Autowired
  private ObjectMapper objectMapper;

  @SuppressWarnings({ "null", "unchecked", "rawtypes" })
  @Test
  void testCreateAndList() throws Exception {
    this.mockMvc.perform(get("/product/create")).andDo(print()).andExpect(status().isOk()).
        andExpect(content().string(containsString("<!DOCTYPE html>")));

    Product product = new Product();
    Mockito.when(productService.create(ArgumentMatchers.any())).thenReturn(product);
    
    this.mockMvc.perform(post("/product/create").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(product))).andDo(print()).andExpect(status().is3xxRedirection());

    List<Product> products = new ArrayList();
    products.add(product);

    Mockito.when(productService.findAll()).thenReturn(products);

    this.mockMvc.perform(get("/product/list")).andDo(print()).andExpect(status().isOk()).
        andExpect(content().string(containsString("<!DOCTYPE html>")));
  }
}
