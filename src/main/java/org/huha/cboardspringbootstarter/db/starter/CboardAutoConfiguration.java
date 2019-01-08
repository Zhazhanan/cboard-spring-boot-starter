package org.huha.cboardspringbootstarter.db.starter;

import org.huha.cboardspringbootstarter.db.engine.DbSchemaCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author WangKun
 * @create 2018-12-06
 * @desc
 **/
@Configuration
@EnableConfigurationProperties(CboardProperties.class)
@ConditionalOnClass(CboardProperties.class)
public class CboardAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CboardAutoConfiguration.class);

    @Autowired
    private CboardProperties cboardProperties;

    @Autowired
    private ApplicationContext ctx;

    @Bean(initMethod = "create")
    public DbSchemaCreate dbSchemaCreate() throws IOException {
        return new DbSchemaCreate(cboardProperties);
    }

}
