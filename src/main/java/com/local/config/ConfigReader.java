package com.local.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    public static final BaseApiConfig baseApiConfig = ConfigFactory.create(BaseApiConfig.class);
}
