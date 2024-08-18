package com.programmingtechie.productservice.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ConfigPropertyBean {
    private String serviceEnv;

    public ConfigPropertyBean(@Value("${service.env}") String serviceEnv) {
        this.serviceEnv = serviceEnv;
    }

    public String getServiceEnv() {
        return serviceEnv;
    }

    public void setServiceEnv(String serviceEnv) {
        this.serviceEnv = serviceEnv;
    }
}
