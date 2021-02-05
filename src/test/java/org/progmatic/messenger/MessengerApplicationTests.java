package org.progmatic.messenger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.progmatic.messenger.model.GercikeUser;
import org.progmatic.messenger.model.GercikeUserDetailsManager;
import org.progmatic.messenger.model.Message;
import org.progmatic.messenger.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("gercikeTest")
class MessengerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "gercike")
    void createNewTopicShouldCreatNewTopicInDB() throws Exception {
        Topic topic = new Topic();
        topic.setTopicName("új teszt-topik");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/topic")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(topic)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    void newMessagePostMethodShouldRedirectToMessages() throws Exception {
//        Message testMessage = new Message("Ez egy teszt üzenet");
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/newMessage").with(SecurityMockMvcRequestPostProcessors.csrf()))
//                .andExpect(MockMvcResultMatchers.model().attribute("newMssg", testMessage))
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/messages"));
//    }
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
