package telran.io;

import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

import java.io.*;

class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	long id;
	String name;
	Person person;
}
public class ObjectOrientedStream  {
	
	@Test
	@Disabled
	void writePerson() throws Exception{
		Person person = new Person();
		person.id = 123;
		person.name = "Vasya";
		person.person = person;
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("person.data"))){
			output.writeObject(person);
			
		}
	}
	@Test
	void readPersonTest() throws Exception {
		Person person = null;
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("person.data"))){
			person = (Person) input.readObject();
		}
		assertEquals(123, person.person.person.person.id);
	}
}
