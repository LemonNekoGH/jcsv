package moe.lemonneko.jcsv.test;

public class TestModel {
    private String name;
    private String age;
    private String gender;

    public TestModel(){

    }

    public TestModel(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", gender: " + gender;
    }
}
