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

import static am.ik.csng.processor.CompileSafeNameProcessor.*;

final class CompileSafeNameTemplate {
	static String template(String target) {
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
}
