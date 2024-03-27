package com.shdatalink.contract;

import io.eventuate.tram.spring.flyway.EventuateTramFlywayMigrationConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(EventuateTramFlywayMigrationConfiguration.class)
@MapperScan("com.shdatalink.contract.mapper")
@SpringBootApplication
public class ContractApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContractApplication.class, args);
    }
}
