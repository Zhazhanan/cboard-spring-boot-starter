package org.huha.cboardspringbootstarter;

import org.huha.cboardspringbootstarter.util.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CboardSpringBootStarterApplicationTests {

    @Test
    public void contextLoads() throws ClassNotFoundException, IOException {
        ResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        DefaultResourceLoader classLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource("org/huha/cboardspringbootstarter/db/create/cboard.mysql.create.sql");
        classLoader.get

        byte[] bytes = IOUtils.readInputStream(resource.getInputStream(), "DBSQL");
        String ddlStatements = new String(bytes);
        System.out.println(ddlStatements);
    }

}
