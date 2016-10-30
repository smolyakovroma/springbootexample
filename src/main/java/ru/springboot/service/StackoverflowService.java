package ru.springboot.service;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.springboot.model.SiteDto;
import ru.springboot.model.StackoverflowWebsite;
import ru.springboot.persistence.StackoverflowWebsiteRepository;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class StackoverflowService {

    @Autowired
    StackoverflowWebsiteRepository repository;

    @Autowired
    StackExchangeClient stackExchangeClient;
//    private static List<StackoverflowWebsite> items = new ArrayList<StackoverflowWebsite>();
//
//    static {
//        items.add(new StackoverflowWebsite("1","www.mail.ru","","test1","weqweqwe"));
//        items.add(new StackoverflowWebsite("2", "www.google.com", "", "test2", "23sdqwe"));
////        items.add(new StackoverflowWebsite("3", "www.yandex.com", "", "test3", "fdf werw erwer "));
//    }

    //    @PostConstruct
//    public void init(){
//        for (StackoverflowWebsite item : items) {
//            repository.save(item);
//        }
//    }
    public List<StackoverflowWebsite> findAll() {
        return stackExchangeClient.getSities().stream()
                .map(this::toStackoverflowWebsite)
                .filter(this::ignoreMeta)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private boolean ignoreMeta(@NonNull StackoverflowWebsite stackoverflowWebsite){
        return !stackoverflowWebsite.getId().startsWith("meta.");
    }

    private StackoverflowWebsite toStackoverflowWebsite(@NonNull SiteDto input) {
        return new StackoverflowWebsite(input.getSite_url().substring("http://".length(), input.getSite_url().length() - ".com".length())
                , input.getSite_url()
                , input.getFavicon_url()
                , input.getName()
                , input.getAudience());
    }
}
