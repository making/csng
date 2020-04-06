/*
 * Copyright (C) 2020 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package am.ik.csng.processor;

import java.nio.CharBuffer;

final class CompileSafeNameTemplate {
	static String templateClass(String simpleClassName) {
		final String[] split = simpleClassName.split("\\.");
		if (split.length >= 2) {
			simpleClassName = split[1];
		}
		final String lowerCamel = lowerCamel(simpleClassName);
		final String upperCamel = upperCamel(simpleClassName);
		final String lowerUnderscore = lowerUnderscore(lowerCamel);
		final String upperUnderscore = lowerUnderscore.toUpperCase();

		return String.format("\tpublic static final String LOWER_CAMEL = \"%s\";\n" + //
				"\tpublic static final String UPPER_CAMEL = \"%s\";\n" + //
				"\tpublic static final String LOWER_UNDERSCORE = \"%s\";\n" + //
				"\tpublic static final String UPPER_UNDERSCORE = \"%s\";\n", lowerCamel,
				upperCamel, lowerUnderscore, upperUnderscore);
	}

	static String templateTarget(String target) {
		final String lowerCamel = lowerCamel(target);
		final String upperCamel = upperCamel(target);
		final String lowerUnderscore = lowerUnderscore(lowerCamel);
		final String upperUnderscore = lowerUnderscore.toUpperCase();

		return String.format("\tpublic static final class %s {\n" + //
				"\t\tpublic static final String LOWER_CAMEL = \"%s\";\n" + //
				"\t\tpublic static final String UPPER_CAMEL = \"%s\";\n" + //
				"\t\tpublic static final String LOWER_UNDERSCORE = \"%s\";\n" + //
				"\t\tpublic static final String UPPER_UNDERSCORE = \"%s\";\n" + //
				"\t}\n", upperCamel, lowerCamel, upperCamel, lowerUnderscore,
				upperUnderscore);
	}

	static String lowerCamel(String s) {
		if (s.length() >= 2) {
			final String firstTwo = s.substring(0, 2);
			if (firstTwo.equals(firstTwo.toUpperCase())) {
				return s;
			}
		}
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	static String upperCamel(String s) {
		if (s.length() >= 2) {
			final String firstTwo = s.substring(0, 2);
			if (firstTwo.equals(firstTwo.toUpperCase())) {
				return s;
			}
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	static String lowerUnderscore(String text) {
		if (text == null || text.isEmpty()) {
			return text;
		}
		final StringBuilder s = new StringBuilder();
		final CharBuffer buffer = CharBuffer.wrap(text);
		while (buffer.hasRemaining()) {
			final char c = buffer.get();
			s.append(Character.toLowerCase(c));
			buffer.mark();
			if (buffer.hasRemaining()) {
				final char c2 = buffer.get();
				if (Character.isLowerCase(c) && Character.isUpperCase(c2)) {
					s.append("_");
				}
				buffer.reset();
			}
		}
		return s.toString();
	}
}
