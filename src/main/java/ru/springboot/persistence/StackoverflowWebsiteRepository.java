package ru.springboot.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.springboot.model.StackoverflowWebsite;

import java.util.List;

@Repository
public interface StackoverflowWebsiteRepository  extends MongoRepository<StackoverflowWebsite, String>{

    List<StackoverflowWebsite> findByWebsite(String website);
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public List<StackoverflowWebsite> findAll() {
//        return mongoTemplate.findAll(StackoverflowWebsite.class);
//    }
}
