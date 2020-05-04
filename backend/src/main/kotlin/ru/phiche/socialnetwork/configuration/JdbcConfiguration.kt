package ru.phiche.socialnetwork.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class JdbcConfiguration {

    @Configuration
    @ConfigurationProperties(prefix = "vzaperti.datasource")
    class JdbcProperties : HikariConfig()

    @Bean
    @Qualifier("vzapertiDataSource")
    fun siebelDataSource(jdbcProperties: JdbcProperties) =
            HikariDataSource(jdbcProperties)

    @Bean
    @Qualifier("vzapertiJdbcTemplate")
    fun jdbcTemplate(@Qualifier("vzapertiDataSource") vzapertiDataSource: DataSource) =
            JdbcTemplate(vzapertiDataSource)
}