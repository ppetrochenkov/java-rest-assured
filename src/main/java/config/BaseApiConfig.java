package config;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:base_api.properties"
})
public interface BaseApiConfig extends Config {
    @Key("base_url")
    String baseUrl();

}
