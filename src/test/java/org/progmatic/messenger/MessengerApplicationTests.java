package org.progmatic.messenger;

import org.junit.jupiter.api.Test;
import org.progmatic.messenger.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MessengerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void messagesShouldShowAllMessages() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/messages"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("showMessages"));
    }

    @Test
    void newMessagePostMethodShouldRedirectToMessages() throws Exception {
        Message testMessage = new Message("hahó", "Béla");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/newMessage").with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.model().attribute("newMssg", testMessage))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/messages"));
    }
/*
	@Test
		public void testCreateMessage() throws Exception {
			System.out.println("createMessage");
			mockMvc.perform(
					MockMvcRequestBuilders.post("/message/create")
							.param("text", "uzenet szoveg")
							.param("author", "peti"))
					.andExpect(
							MockMvcResultMatchers.redirectedUrl("/message/show"));
		}

	@Test
	void homeShouldReturnGreeting() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/home"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("greeting"))
				.andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Üdv újra itt!")));
	}
*/


}
