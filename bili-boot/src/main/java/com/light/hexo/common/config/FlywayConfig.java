package com.light.hexo.common.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

/**
 * @ClassName: FlywayConfig
 * @ProjectName hexo-boot
 * @Description: flyway 配置
 */
@Configuration
@DependsOn("dataSourceConfig")
public class FlywayConfig {

    @Value("${spring.datasource.druid.url}")
    private String url;

    @Value("${spring.datasource.druid.username}")
    private String username;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @PostConstruct
    public void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .cleanDisabled(true)
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .locations("db/migration")
                .load();
        flyway.migrate();
    }
}
