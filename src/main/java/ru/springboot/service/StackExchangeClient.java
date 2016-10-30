package ru.springboot.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.springboot.model.SiteDto;
import ru.springboot.model.SitesDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class StackExchangeClient {

    private HttpClient httpClient = HttpClientBuilder.create().build();
    private ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

    private RestTemplate restTemplate = new RestTemplate(requestFactory);

    public List<SiteDto> getSities() {
        String url = "https://api.stackexchange.com/2.2/sites?page=1&pagesize=9999&filter=!Fn4IB7S7T4v-QOAVmFyqlc(HdV";
        SitesDto response = null;
        try {
            response = restTemplate.getForObject(new URI(url), SitesDto.class);
        } catch (URISyntaxException e) {
           throw new RuntimeException(e);
        }
        return response.getItems();
    }
}
