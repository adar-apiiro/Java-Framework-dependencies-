package com.example;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        // Set up an in-memory H2 DataSource
        DataSource ds = new DriverManagerDataSource(
            "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", ""
        );

        // Use Spring Data JDBC’s template
        JdbcAggregateTemplate template = new JdbcAggregateTemplate(ds);

        // Define and save a simple entity
        Person p = new Person(null, "Alice");
        Person saved = template.insert(p);

        System.out.println("Saved Person: " + saved);
    }

    @Table("person")
    public static class Person {
        private Long id;
        private String name;

        public Person(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        // getters/setters omitted for brevity

        @Override
        public String toString() {
            return "Person{id=" + id + ", name='" + name + "'}";
        }
    }
}
