package pro.fontoura.cursomc.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	/**
	 * @param var
	 * @return
	 */
	public static String decoded(String var) {
		try {
			return URLDecoder.decode(var, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * @param x
	 * @return
	 */
	public static List<Integer> decodeIntList(String x) {

		return Arrays.asList(x.split(",")).stream().map(o -> Integer.parseInt(o)).collect(Collectors.toList());
	}
}
