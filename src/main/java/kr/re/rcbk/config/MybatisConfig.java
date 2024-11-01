package kr.re.rcbk.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class MybatisConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setDataSource(dataSource);
        //bean.setMapperLocations(applicationContext.getResources("classpath:mappers/oracle/mapper/**/*.xml")); //oracle mapper
        //bean.setMapperLocations(applicationContext.getResources("classpath:mappers/maria/mapper/**/*.xml"));  //maria db mapper
        //bean.setMapperLocations(applicationContext.getResources("classpath:mappers/mysql/mapper/**/*.xml"));  //mysql mapper
        bean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml")); //h2 db mapper
        bean.setConfigLocation(applicationContext.getResource("classpath:mapper-config.xml"));
        bean.setTypeAliasesPackage("kr.re.rcbk");

        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
