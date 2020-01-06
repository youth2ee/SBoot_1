package com.naver.b1;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test {

	@Autowired private DataSource dataSource;
	
	@org.junit.jupiter.api.Test
	void test() throws SQLException {
		assertNotNull(dataSource.getConnection());
	}

}
