package org.huha.cboardspringbootstarter.db.engine;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.huha.cboardspringbootstarter.db.starter.CboardProperties;
import org.huha.cboardspringbootstarter.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author WangKun
 * @create 2018-12-06
 * @desc
 **/
public class DbSchemaCreate {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbSchemaCreate.class);
    @Autowired
    protected SqlSessionFactory sqlSessionFactory;
    private CboardProperties cboardProperties = new CboardProperties();

    public static final String MYSQL = "mysql";
    public static final String ORACLE = "oracle";

    public DbSchemaCreate(CboardProperties cboardProperties) {
        this.cboardProperties = cboardProperties;
    }

    /**
     * @auther: WangKun
     * @date: 2018-12-08 16:44
     * @description: initialize method
     */
    public void create() throws SQLException, IOException {
        if (cboardProperties.getDatabaseSchemaUpdate()) {
            SqlSession sqlSession = null;
            Connection connection = null;
            try {
                sqlSession = sqlSessionFactory.openSession();
                connection = sqlSession.getConnection();
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                String DBName = databaseMetaData.getDatabaseProductName();
                LOGGER.debug("-------------------DBType = {}", DBName);
                Statement jdbcStatement = connection.createStatement();

                switch (DBName.toLowerCase()) {
                    case MYSQL:
                        executeMySqlSQL(jdbcStatement);
                        break;
                    case ORACLE:
                        break;
                    default:

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (null != sqlSession) {
                    sqlSession.close();
                }
                if (null != connection) {
                    connection.close();
                }
            }
        }
    }

    protected void executeMySqlSQL(Statement jdbcStatement) throws IOException, SQLException {
        try {
            InputStream inputStream = new FileInputStream("E:/git_repository/Spring-Boot/transaction/src/test/java/com/example/transaction/activiti.mssql.create.engine.sql");
            byte[] bytes = IOUtils.readInputStream(inputStream, "DBSQL");
            String ddlStatements = new String(bytes);

            BufferedReader reader = new BufferedReader(new StringReader(ddlStatements));
            String line = this.readNextTrimmedLine(reader);
            String sqlStatement = null;
            while (line != null) {
                if (line.length() > 0) {
                    if (line.endsWith(";")) {
                        sqlStatement = addSqlStatementBlock(sqlStatement, line);
                        LOGGER.debug("SQL::{}", sqlStatement);
                        LOGGER.debug("---------------------------------------------------------");
                        try {
                            boolean execute = jdbcStatement.execute(sqlStatement);
                            LOGGER.debug("execute sql result::{}", execute);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            sqlStatement = null;
                        }
                    } else {
                        sqlStatement = addSqlStatementBlock(sqlStatement, line);
                    }
                }
                line = readNextTrimmedLine(reader);
                LOGGER.debug("readNextTrimmedLine::{}", line);
            }
        } finally {
            jdbcStatement.close();
        }
    }

    protected String readNextTrimmedLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null) {
            line = line.trim();
        }
        return line;
    }

    protected String addSqlStatementBlock(String sqlStatement, String line) {
        if (sqlStatement == null) {
            return line;
        }
        return sqlStatement + " \n" + line;
    }


}
