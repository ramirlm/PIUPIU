package info.noip.piupiu.infra;

import com.rosaloves.bitlyj.Url;
import static com.rosaloves.bitlyj.Bitly.*;

public class BitlyShortener {

	public static String shortURL(String URL) {
		Url url = as("o_1a40gtdkg", "R_ba6345da8469187f7481a4b5d0958646")
				.call(shorten(URL));
		return url.getShortUrl();
	}
}
