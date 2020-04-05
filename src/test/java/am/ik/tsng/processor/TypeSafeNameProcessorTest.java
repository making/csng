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
package am.ik.tsng.processor;

import com.google.testing.compile.JavaFileObjects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.google.testing.compile.JavaSourcesSubject.assertThat;

class TypeSafeNameProcessorTest {

	@Test
	void processBean() {
		assertThat(JavaFileObjects.forResource("test/CarBean.java"))
				.processedWith(new TypeSafeNameProcessor()) //
				.compilesWithoutError().and()
				.generatesSources(JavaFileObjects.forResource("test/_CarBeanName.java"));
	}

	@Test
	void processImmutable() {
		assertThat(JavaFileObjects.forResource("test/Car.java"))
				.processedWith(new TypeSafeNameProcessor()) //
				.compilesWithoutError().and()
				.generatesSources(JavaFileObjects.forResource("test/_CarName.java"));
	}

	@Test
	void processFiled() {
		assertThat(JavaFileObjects.forResource("test/CarField.java"))
				.processedWith(new TypeSafeNameProcessor()) //
				.compilesWithoutError().and()
				.generatesSources(JavaFileObjects.forResource("test/_CarFieldName.java"));
	}

	@Test
	void processConstructorArguments() {
		assertThat(JavaFileObjects.forResource("test/Car2.java"))
				.processedWith(new TypeSafeNameProcessor()) //
				.compilesWithoutError().and().generatesSources(
						JavaFileObjects.forResource("test/_Car2Properties.java"));
	}

	@Test
	void processMethodArguments() {
		assertThat(JavaFileObjects.forResource("test/UserService.java"))
				.processedWith(new TypeSafeNameProcessor()) //
				.compilesWithoutError().and().generatesSources(JavaFileObjects
						.forResource("test/_UserServiceCreateUserProperties.java"));
	}

	@Test
	void processInnerClass() {
		assertThat(JavaFileObjects.forResource("test/Address.java"))
				.processedWith(new TypeSafeNameProcessor()) //
				.compilesWithoutError().and()
				.generatesSources(JavaFileObjects.forResource("test/_AddressName.java"),
						JavaFileObjects.forResource("test/_Address_CountryName.java"),
						JavaFileObjects
								.forResource("test/_Address_PhoneNumberName.java"));
	}

	@Test
	void testBeanLowerCamel() {
		Assertions.assertThat(TypeSafeNameProcessor.lowerCamel("Name")).isEqualTo("name");
		Assertions.assertThat(TypeSafeNameProcessor.lowerCamel("NAme")).isEqualTo("NAme");
		Assertions.assertThat(TypeSafeNameProcessor.lowerCamel("NAME")).isEqualTo("NAME");
	}

	@Test
	void testBeanUpperCamel() {
		Assertions.assertThat(TypeSafeNameProcessor.upperCamel("name")).isEqualTo("Name");
		Assertions.assertThat(TypeSafeNameProcessor.upperCamel("NAme")).isEqualTo("NAme");
		Assertions.assertThat(TypeSafeNameProcessor.upperCamel("NAME")).isEqualTo("NAME");
	}

}