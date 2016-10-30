package ru.springboot.web;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ru.springboot.Application;
import ru.springboot.model.StackoverflowWebsite;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class StackoverflowControllerT {

    RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetListOfProviders() throws Exception {
        ResponseEntity<List<StackoverflowWebsite>> responseEntity = restTemplate.exchange("http://localhost:8080/api/stackoverflow", HttpMethod.GET, null, new ParameterizedTypeReference<List<StackoverflowWebsite>>() {
        });
        List<StackoverflowWebsite> actualList = responseEntity.getBody();
        assertThat(actualList.size(), is(2));
        ImmutableList<String> actualIds = actualList.stream()
                .map(stackoverflowWebsite -> stackoverflowWebsite.getId())
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));

        assertThat(actualIds, containsInAnyOrder("1","2"));
    }
}