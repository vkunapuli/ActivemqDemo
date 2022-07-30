package com.sssystems.activemqdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.hypermedia.CloudHypermediaAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties(prefix="config")
@RefreshScope
public class ConfigClientController {
    /*@Value("${feature1}")
    private String feature1;
    @Value("${feature2}")
    private String feature2;
    @Value("${feature3.vendor1}")
    private String feature31;
    @Value("${feature3.vendor2}")
    private String feature32;
    @Value("${feature4.vendor1.group1}")
    private String feature411;
    @Value("${feature4.vendor1.group2}")
    private String feature412;
    @Value("${feature4.vendor2.group1}")
    private String feature421;
    @Value("${feature4.vendor2.group1}")
    private String feature422;*/

    @GetMapping(
            value = "/feature-toggles/{feature}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String featureToggles(@PathVariable("feature") String feature) {
        /*switch (feature) {
            case "feature1":
                return String.format("Hello! '%s' status is '%s' ...\n", feature, feature1);
            case "feature2":
                return String.format("Hello!  '%s'   status is '%s' ...\n", feature,feature2);
            case "feature3.vendor1":
                return String.format("Hello! '%s'  status is '%s' ...\n", feature,feature31);
            case "feature3.vendor2":
                return String.format("Hello! '%s'  status is '%s' ...\n", feature,feature32);
            case "feature4.vendor1.group1":
                return String.format("Hello! '%s'   status is '%s' ...\n", feature,feature411);
            case "feature4.vendor1.group2":
                return String.format("Hello! '%s'   status is '%s' ...\n", feature,feature412);
            case "feature4.vendor2.group1":
                return String.format("Hello!  '%s'  status is '%s' ...\n", feature,feature421);
            case "feature4.vendor2.group2":
                return String.format("Hello!  '%s'  status is '%s' ...\n", feature,feature422);
            default:
                return "Unknown feature, please enter valid feature";
        }*/
        return "na";
    }
}
