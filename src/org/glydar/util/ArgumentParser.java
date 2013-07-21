package org.glydar.util;

import java.util.Arrays;

import org.glydar.Glydar;

public class ArgumentParser {
	public static void parse(String[] args) {
		java.util.List<String> list = Arrays.asList(args);

		if (list.contains("-debug")) {
			Glydar.debugmode = true;
		}
	}
}
