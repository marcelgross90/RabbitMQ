import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by marcelgross on 04.07.16.
 */
public class Producer {

	public static void main(String[] args) throws Exception {
			final ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			final Connection connection = factory.newConnection();
			final Channel channel = connection.createChannel();
			channel.exchangeDeclare("exchange2", "topic");
			//channel.exchangeDeclare("exchange1", "fanout");
			//channel.queueDeclare("HelloQueue", false, false, false, null);
			final String message = "Hello World!";
			//channel.basicPublish("", "HelloQueue", null, message.getBytes());
			//channel.basicPublish("exchange1", "", null, message.getBytes());
			//channel.basicPublish("exchange1", "", null, message.getBytes());
			channel.basicPublish("exchange2", "de.fhws.fiw.moudles.5011.cancel", null, message.getBytes());
			System.out.println("Message sent");

			channel.close();
			connection.close();

	}
}
