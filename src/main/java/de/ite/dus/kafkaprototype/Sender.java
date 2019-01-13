package de.ite.dus.kafkaprototype;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private Producer<String, String> quotesProducer;

    public Sender(Producer<String, String> quotesProducer) {
        this.quotesProducer = quotesProducer;
    }

    public void send(String topic, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);

        try {

            final ProducerRecord<String, String> record = new ProducerRecord<>(topic, null, payload);

            RecordMetadata metadata = quotesProducer.send(record).get();

            LOGGER.info("sent record(key={} value={}) meta(partition={}, offset={})\n",
                        record.key(), record.value(), metadata.partition(), metadata.offset());
        } catch(Exception e){
            LOGGER.error("error while sending record with payload "+ payload + "to topic " +topic);
        }
    }
}
