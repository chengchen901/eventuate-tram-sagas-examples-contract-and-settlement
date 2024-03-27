package com.shdatalink.settlement;


import io.eventuate.tram.spring.flyway.EventuateTramFlywayMigrationConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(EventuateTramFlywayMigrationConfiguration.class)
@MapperScan("com.shdatalink.settlement.mapper")
@SpringBootApplication
public class SettlementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SettlementApplication.class, args);
    }
}
