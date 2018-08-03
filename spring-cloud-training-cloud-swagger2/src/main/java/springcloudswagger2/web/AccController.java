package springcloudswagger2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccController {
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/service-b")
    public String acc(){
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}