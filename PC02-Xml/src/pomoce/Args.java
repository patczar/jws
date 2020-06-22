package pomoce;

import java.util.Arrays;

public class Args {
	public static String[] argsOrDefault(String[] args, String... defaults) {
		int length = Math.max(args.length, defaults.length);
		String[] newArgs = Arrays.copyOf(args, length);
		for(int i = 0; i < defaults.length; i++) {
			if(newArgs[i] == null) {
				newArgs[i] = defaults[i];
			}
		}
		return newArgs;
	}
}
