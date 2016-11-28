package MongoDb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class Cassandra {

	public static void main(String[] args) {
		Cluster cluster;
		Session session;

		// Connect to the cluster and keyspace "demo"
//		cluster = Cluster.builder().addContactPoint("localhost").build();
//		session = cluster.connect("cassandra");
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		final Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
		for (final Host host : metadata.getAllHosts()) {
			System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(),
					host.getRack());
		}
		session = cluster.connect();
	}
	// // Insert one record into the users table
	// session.execute(
	// "INSERT INTO users (lastname, age, city, email, firstname) VALUES
	// ('Jones', 35, 'Austin', 'bob@example.com', 'Bob')");
	//
	// // Use select to get the user we just entered
	// ResultSet results = session.execute("SELECT * FROM users WHERE
	// lastname='Jones'");
	// for (Row row : results) {
	// System.out.format("%s %d\n", row.getString("firstname"),
	// row.getInt("age"));
	// }

}
