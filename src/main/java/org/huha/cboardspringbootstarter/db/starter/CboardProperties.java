package org.huha.cboardspringbootstarter.db.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author WangKun
 * @create 2018-12-06
 * @desc
 **/
@ConfigurationProperties(prefix = "cboard")
public class CboardProperties implements Serializable {
    private static final long serialVersionUID = -7597859252998148324L;

    private Boolean databaseSchemaUpdate = false;

    public Boolean getDatabaseSchemaUpdate() {
        return databaseSchemaUpdate;
    }

    public void setDatabaseSchemaUpdate(Boolean databaseSchemaUpdate) {
        this.databaseSchemaUpdate = databaseSchemaUpdate;
    }

    @Override
    public String toString() {
        return "CboardProperties{" +
                "databaseSchemaUpdate=" + databaseSchemaUpdate +
                '}';
    }
}
