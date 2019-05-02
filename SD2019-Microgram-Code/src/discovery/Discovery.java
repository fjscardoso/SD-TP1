package discovery;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class Discovery {

	private static Logger Log = Logger.getLogger(Discovery.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s\n");
	}


	static final InetSocketAddress DISCOVERY_ADDR = new InetSocketAddress("226.226.226.226", 2266);
	static final int DISCOVERY_PERIOD = 1000;
	static final int DISCOVERY_TIMEOUT = 30000;
	static final int PORT = 2266;
	static final int MAX_DATAGRAM_SIZE = 65536;

	private static final String DELIMITER = "\t";

	/**
	 * Announces periodically a service in a separate thread .
	 *
	 * @param serviceName the name of the service being announced.
	 * @param serviceURI  the location of the service
	 */
	public static void announce(String serviceName, String serviceURI) {
		Log.info(String.format("Starting Discovery announcements on: %s for: %s -> %s", DISCOVERY_ADDR, serviceName, serviceURI));

		byte[] pktBytes = String.format("%s%s%s", serviceName, DELIMITER, serviceURI).getBytes();

		DatagramPacket pkt = new DatagramPacket(pktBytes, pktBytes.length, DISCOVERY_ADDR);
		new Thread(() -> {
			try (DatagramSocket ms = new DatagramSocket()) {
				for (; ; ) {
					ms.send(pkt);
					Thread.sleep(DISCOVERY_PERIOD);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}


	/**
	 * Performs discovery of instances of the service with the given name.
	 *
	 * @param serviceName      the name of the service being discovered
	 * @param minRepliesNeeded the required number of service replicas to find.
	 * @return an array of URI with the service instances discovered. Returns an empty, 0-length, array if the service is not found within the alloted time.
	 */
	public static URI[] findUrisOf(String serviceName, int minRepliesNeeded) {

		URI[] uris = new URI[minRepliesNeeded];

		try{
			final InetAddress group = InetAddress.getByName("226.226.226.226");
			if(!group.isMulticastAddress()) {
				System.out.println( "Not a multicast address (use range : 224.0.0.0 -- 239.255.255.255)");
				System.exit(1);
			}

			MulticastSocket socket = new MulticastSocket(PORT);
			socket.joinGroup(group);
			int nreplies = 0;
			while (nreplies < minRepliesNeeded) {
				byte[] buffer = new byte[MAX_DATAGRAM_SIZE];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socket.receive(request);
				String message = new String (request.getData(), 0, request.getLength());
				String[] get = message.split(DELIMITER);
				if(get[0].equals(serviceName)) {
					URI myURI = new URI(get[1]);
					uris[nreplies] = myURI;
					nreplies++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return uris;
	}
}