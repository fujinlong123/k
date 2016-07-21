import java.net.URI;
import java.net.URISyntaxException;

public class Uri {
	public static void main(String[] args) throws URISyntaxException {
		URI uri=new URI("http://www.baiud.com/adfsadf/asdfasdf/sdfsadf?asd=asdfasd&sdfadf");
		///System.out.println(uri.get);
		URI uri1=uri.resolve("dfsds=sss");
		
		URI uril=uri.resolve("");
		uril.resolve("").toString();
		System.out.println();
	}
}
