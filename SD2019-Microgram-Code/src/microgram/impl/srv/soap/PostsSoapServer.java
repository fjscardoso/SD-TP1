package microgram.impl.srv.soap;

import java.util.logging.Logger;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.xml.ws.Endpoint;

import com.sun.net.httpserver.HttpServer;
import utils.IP;

public class PostsSoapServer {
	private static Logger Log = Logger.getLogger(PostsSoapServer.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s");
	}
	
	public static final int PORT = 7777;
	public static final String SERVICE = "Microgram-Posts";
	public static String SERVER_BASE_URI = "http://%s:%s/soap";
	
	public static void main(String[] args) throws Exception {
/**
		// Create an HTTP server, accepting requests at PORT (from all local interfaces)
		HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);

		// Create the SOAP Endpoint
		Endpoint soapEndpoint = Endpoint.create(new _TODO_PostsWebService());

		// Publish the SOAP webservice, under the "http://<ip>:<port>/soap"
		soapEndpoint.publish(server.createContext("/soap/posts"));

		// Provide an executor to create threads as needed...
		server.setExecutor(Executors.newCachedThreadPool());

		// Start Serving Requests: both SOAP Requests
		server.start();

		String ip = IP.hostAddress();
		Log.info(String.format("%s Soap Server ready @ %s\n", SERVICE, ip + ":" + PORT));
	*/
 	}
}
