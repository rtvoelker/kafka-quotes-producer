package de.ite.dus.kafkaprototype;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaPrototypeApplicationTests {

	private static String BOOT_TOPIC = "test";

	@Autowired
	private Sender sender;

//	@ClassRule
//	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, BOOT_TOPIC);

	@Test
	public void testReceive() throws Exception {
		sender.send(BOOT_TOPIC, "message 3!");
	}

}
