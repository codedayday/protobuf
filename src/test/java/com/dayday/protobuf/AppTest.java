package com.dayday.protobuf;

import java.util.Arrays;

import com.dayday.protobuf.AddressBookProtos.AddressBook;
import com.dayday.protobuf.AddressBookProtos.Person;
import com.google.protobuf.InvalidProtocolBufferException;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	public static void main(String[] args) {
		// 构建一个Person对象
		Person person = Person.newBuilder().setEmail("zhangsan@163.com").setId(10086).setName("zhangsan")
				.addPhone(Person.PhoneNumber.newBuilder().setNumber("186").setType(Person.PhoneType.HOME).build())
				.build();
		System.out.println("打印输出Person对象信息：");
		System.out.println(person);
		System.out.println("Person对象调用toString()方法：");
		System.out.println(person.toString());

		System.out.println("Person对象字段是否初始化：" + person.isInitialized());

		// 序列号
		System.out.println("Person对象调用toByteString()方法：");
		System.out.println(person.toByteString());

		System.out.println("Person对象调用toByteArray()方法:");
		System.out.println(Arrays.toString(person.toByteArray()));

		try {
			System.out.println("反序列化后的对象信息：");
			// 反序列化
			Person newPerson = Person.parseFrom(person.toByteArray());
			System.out.println(newPerson);
			newPerson = Person.parseFrom(person.toByteString());
			System.out.println(newPerson);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

		// 向地址簿添加两条Person信息
		AddressBook.Builder books = AddressBook.newBuilder();
		books.addPerson(person);
		books.addPerson(Person.newBuilder(person).setEmail("tom@163.com").build());
		System.out.println("AddressBook对象信息：");
		System.out.println(books.build());

	}
}
