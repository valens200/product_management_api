package rw.productant.prod;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import rw.productant.v1.ProdApplication;

@SpringBootTest (classes = ProdApplication.class)
@ActiveProfiles("test")
class ProdApplicationTests {

	@Test
	void contextLoads() {
	}

}
