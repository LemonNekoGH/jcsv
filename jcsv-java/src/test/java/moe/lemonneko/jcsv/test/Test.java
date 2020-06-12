package moe.lemonneko.jcsv.test;

import moe.lemonneko.jcsv.CSVReader;
import moe.lemonneko.jcsv.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    @org.junit.Test
    public void commonReadTest() throws IOException, IllegalAccessException, InstantiationException {
        CSVReader reader = new CSVReader(new File("D:\\Projects\\IdeaProjects\\jcsv\\jcsv-java\\src\\test\\resources\\test.csv"));
        List<TestModel> models = reader.readAll(TestModel.class);
        models.forEach(System.out::println);
    }

    @org.junit.Test
    public void annotationReadTest() throws IOException, IllegalAccessException, InstantiationException {
        CSVReader reader = new CSVReader(new File("D:\\Projects\\IdeaProjects\\jcsv\\jcsv-java\\src\\test\\resources\\test.csv"));
        List<TestModel2> models = reader.readAll(TestModel2.class);
        models.forEach(System.out::println);
    }

    @org.junit.Test
    public void commonWriteTest() throws IOException, IllegalAccessException {
        CSVWriter writer = new CSVWriter(new File("D:\\Projects\\IdeaProjects\\jcsv\\jcsv-java\\src\\test\\resources\\test2.csv"));
        List<TestModel> models = new ArrayList<>();
        models.add(new TestModel("Lemon","95","female"));
        models.add(new TestModel("Lemon1","95","female"));
        models.add(new TestModel("Lemon2","95","male"));
        models.add(new TestModel("Lemon3","95","male"));
        writer.writeAll(models,TestModel.class);
    }

    @org.junit.Test
    public void annotationWriteTest() throws IOException, IllegalAccessException {
        CSVWriter writer = new CSVWriter(new File("D:\\Projects\\IdeaProjects\\jcsv\\jcsv-java\\src\\test\\resources\\test2.csv"));
        List<TestModel2> models = new ArrayList<>();
        models.add(new TestModel2("Lemon","95","female"));
        models.add(new TestModel2("Lemon1","95","female"));
        models.add(new TestModel2("Lemon2","95","male"));
        models.add(new TestModel2("Lemon3","95","male"));
        writer.writeAll(models,TestModel2.class);
    }
}
