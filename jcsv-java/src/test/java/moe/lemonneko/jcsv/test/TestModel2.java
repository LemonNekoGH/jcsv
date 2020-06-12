package moe.lemonneko.jcsv.test;

import moe.lemonneko.jcsv.CSVField;

public class TestModel2 {
    @CSVField(name = "name") private String n;
    @CSVField(name = "age") private String a;
    @CSVField(name = "gender") private String g;

    public TestModel2(){

    }

    public TestModel2(String n, String a, String g) {
        this.n = n;
        this.a = a;
        this.g = g;
    }

    @Override
    public String toString() {
        return "name: " + n + ", age: " + a + ", gender: " + g;
    }
}
