import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlConn {
	public static void main(String[] args) {
		try {
			URL url=new URL("http://www.baidu.com");
	
			URLConnection con=url.openConnection();
			InputStream is=con.getInputStream();
			byte[] b=new byte[1024];
			int l=-1;
			while((l=is.read(b))!=-1){
				System.out.println(new String(b,0,l));
			}
			
			//System.out.println(con.);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
