import java.net.URI;
import java.net.URISyntaxException;

public class Uri {
	public static void main(String[] args) throws URISyntaxException {
		URI uri=new URI("http://www.baiud.com/adfsadf/asdfasdf/sdfsadf");
		URI uri1=new URI("http://mial.qq.com");
		URI uril=uri.resolve(uri1);
		System.out.println();
	}
}
