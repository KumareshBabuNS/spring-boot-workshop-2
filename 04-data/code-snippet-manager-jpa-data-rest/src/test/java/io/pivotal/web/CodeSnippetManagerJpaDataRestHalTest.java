package io.pivotal.web;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import io.pivotal.web.domain.Snippet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CodeSnippetManagerJpaDataRestApplication.class)
@WebIntegrationTest("server.port:0")
public class CodeSnippetManagerJpaDataRestHalTest {
	
	@Value("${local.server.port}")
    int port;
	
	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Resource<Snippet>> responseEntity =
		  restTemplate.exchange("http://localhost:" + port + "/snippets/66921076-ed1d-458b-9d7d-ce9a227d64a5", HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Snippet>>() {}, Collections.emptyMap());
		
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Resource<Snippet> userResource = responseEntity.getBody();
			Snippet snippet = userResource.getContent();
			assert snippet.getTitle().contains("Hello");
		}
	}

}
