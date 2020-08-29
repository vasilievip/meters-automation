package com.github.meters.metersautomation.smartmac;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "deviceinfo", url = "${feign.client.config.deviceinfo.url:https://dash.smart-mac.com}")
public interface DeviceInfoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api", consumes = "application/json")
    List<DeviceInfo> get(@RequestParam("devid") String deviceId, @RequestParam("apikey") String apiKey);
}
