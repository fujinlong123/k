import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class httpclientafadf {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//RequestConfig config=RequestConfig.custom().
		CloseableHttpClient client=HttpClients.createDefault();
	
		HttpGet get=new HttpGet("http://ursdoccdn.nosdn.127.net/webzj_m163/message_2016052502.js");
		CloseableHttpResponse response=client.execute(get);
		HttpEntity entity=response.getEntity();
		InputStream is=entity.getContent();
		byte[] b=new byte[1024];
		int l=-1;
		while((l=is.read(b))!=-1){
			System.out.println(new String(b,0,l));
		}
		
	}
}
