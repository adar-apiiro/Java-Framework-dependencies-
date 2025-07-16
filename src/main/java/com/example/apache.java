package com.example.digester;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.io.IOUtils;

/**
 * A simple example that:
 * 1) Loads an XML file via IOUtils
 * 2) Uses Digester to parse it into Java objects
 */
public class DigesterExample {

    public static class Person {
        private String name;
        private int age;

        public void setName(String name) { this.name = name; }
        public void setAge(int age)   { this.age = age; }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    public static void main(String[] args) throws Exception {
        // 1. Load XML from the classpath using IOUtils
        InputStream in = DigesterExample.class.getResourceAsStream("/example.xml");
        if (in == null) {
            System.err.println("Could not find example.xml on the classpath.");
            return;
        }
        // Read into a String (not strictly necessary for Digester, but demonstrates IOUtils)
        String xml = IOUtils.toString(in, "UTF-8");
        System.out.println("XML content:\n" + xml);

        // 2. Set up Digester
        Digester digester = new Digester();
        digester.addObjectCreate("people", ArrayList.class);
        digester.addObjectCreate("people/person", Person.class);
        digester.addSetProperties("people/person"); // if attributes used
        digester.addBeanPropertySetter("people/person/name", "name");
        digester.addBeanPropertySetter("people/person/age", "age");
        digester.addSetNext("people/person", "add");

        // 3. Parse from a fresh InputStream
        try (InputStream xmlStream = IOUtils.toInputStream(xml, "UTF-8")) {
            @SuppressWarnings("unchecked")
            List<Person> people = (List<Person>) digester.parse(xmlStream);
            System.out.println("\nParsed objects:");
            for (Person p : people) {
                System.out.println("  " + p);
            }
        }
    }
}
