package unit.java.com.springmvc.bcart.controllers;


import com.springmvc.bcart.controllers.ProductController;
import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.Strategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;


public class ProductControllerTest {

    private static final String PRODUCT = "{'productId' : 'book1', 'name' : 'Book1', 'detail' : 'Lorem ipsum dolor sit.', 'size' : 'M', 'colour' : 'Blue', 'price' : 42.17, 'categoryId' : 'books'}";

    @InjectMocks
    private ProductController controller = new ProductController();

    @Mock
    @Autowired(required = true)
    BeanFactory beanFactory;

    MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetProductDoesNotExist() throws Exception {

        mockMvc.perform(get("/product/book1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testGetProductDoesExist() throws Exception {
        Product product = new Product("book1", "Book1", 42.17, "Lorem ipsum dolor sit.", "M", "Blue", "books");

        Strategy productStrategy = beanFactory.getBean("product", Strategy.class);
        Mockito.when(productStrategy.findById("book1")).thenReturn(product);

        mockMvc.perform(get("/product/book1"))
                .andExpect(status().isOk())
                .andExpect(content().string(PRODUCT));
    }

}
