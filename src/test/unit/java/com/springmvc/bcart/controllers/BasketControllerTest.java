
package unit.java.com.springmvc.bcart.controllers;


import com.springmvc.bcart.controllers.BasketController;
import com.springmvc.bcart.model.Basket;
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


public class BasketControllerTest {

    private static final String BASKET = "{'BasketId' : 'book1', 'count' : 42}";

    @InjectMocks
    private BasketController controller = new BasketController();

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
    public void testGetBasketDoesExist() throws Exception {
        Basket basket = new Basket("book1", 42.);

        Strategy BasketStrategy = beanFactory.getBean("Basket", Strategy.class);
        Mockito.when(BasketStrategy.findById("book1")).thenReturn(basket);

        mockMvc.perform(get("/Basket/book1"))
                .andExpect(status().isOk())
                .andExpect(content().string(BASKET));
    }

}
