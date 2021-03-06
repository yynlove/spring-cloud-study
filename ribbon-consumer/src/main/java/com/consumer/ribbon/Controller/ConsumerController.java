package com.consumer.ribbon.Controller;

import com.consumer.ribbon.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/consumer")
    public String helloConsumer(){
        return restTemplate.getForObject("http://HELLO-SERVICE/hello",String.class);

    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String user(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/user?user={1}", String.class, "yuan");
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/name",method= RequestMethod.GET)
    public String name(@RequestParam String name){
        Map<String, String> map = new HashMap<>();
        map.put("name",name);
       return restTemplate.getForEntity("http://HELLO-SERVICE/name?name={name}",String.class,map).getBody();
    }

    @RequestMapping(value = "/getName",method = RequestMethod.GET)
    public String getName(){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/name?name={name}").build().expand("yenan").encode();
        URI uri = uriComponents.toUri();
        return restTemplate.getForEntity(uri,String.class).getBody();
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public String getUser(){
        User user = restTemplate.getForObject("http://HELLO-SERVICE/getUser", User.class);
        return user.toString();

    }

    @RequestMapping(value = "/insertOne1")
    public User insertOne(){
        User user = new User();
        user.setName("yuanyenan");
        return restTemplate.postForEntity("http://HELLO-SERVICE/insertOne",user,User.class).getBody();
    }

    @RequestMapping(value = "/insertOne2",method = RequestMethod.POST)
    public User insertOne2(){
        User user = new User();
        user.setName("yuanyenan_2");
        return restTemplate.postForObject("http://HELLO-SERVICE/insertOne",user,User.class);

    }

    @RequestMapping(value = "/putOne")
    public void putOne(){
        String id = "123";
        User user = new User();
        user.setName("yuanyenan");
        restTemplate.put("http://HELLO-SERVICE/updateOne/{id}",user,id);
    }

    @RequestMapping(value = "/deleteOne")
    public void deleteOne(){
        restTemplate.delete("http://HELLO-SERVICE/delete/{id}",90);
    }

}
