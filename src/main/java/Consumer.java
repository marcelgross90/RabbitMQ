import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by marcelgross on 04.07.16.
 */
public class Consumer {

	public static void main(String[] args) throws Exception{

			final ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			final Connection connection = factory.newConnection();
			final Channel channel = connection.createChannel();
			//channel.exchangeDeclare("exchange1", "fanout");
			channel.exchangeDeclare("exchange2", "topic");
			final String queueName = channel.queueDeclare().getQueue();
			//channel.queueBind(queueName, "exchange1", "");
			channel.queueBind(queueName, "exchange2", "de.fhws.fiw.moudles.*.cancel");
			final com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
						throws IOException {
					final String message = new String(body, "UTF-8");
					System.out.println("Received " + message);
				}
			};

			//channel.basicConsume("HelloQueue", true, consumer);
			channel.basicConsume(queueName, true, consumer);

			System.out.println("Waiting for incomming messages");

	}
}
