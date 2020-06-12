package moe.lemonneko.jcsv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CSVWriter extends FileWriter {
    private final BufferedWriter mWriter = new BufferedWriter(this);

    public CSVWriter(String fileName) throws IOException {
        super(fileName, false);
    }

    public CSVWriter(File file) throws IOException {
        super(file, false);
    }

    public <T> void writeAll(List<T> objects, Class<T> clazz) throws IllegalAccessException, IOException {
        CSVObjectList list = new CSVObjectList();
        Field[] fields = clazz.getDeclaredFields();
        for (T object : objects) {
            CSVObject csvObject = new CSVObject();
            for (Field field : fields) {
                field.setAccessible(true);
                CSVField annotation = field.getAnnotation(CSVField.class);
                if (annotation != null) {
                    csvObject.set(annotation.name(), (String) field.get(object));
                } else {
                    csvObject.set(field.getName(), (String) field.get(object));
                }
            }
            list.add(csvObject);
        }
        writeAll(list);
    }

    public void writeAll(CSVObjectList objects) throws IOException {
        CSVObject firstObject = objects.get(0);
        Object[] keys = firstObject.keySet().toArray();
        StringBuilder header = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            header.append(keys[i]);
            if (i != keys.length - 1){
                header.append(",");
            }else{
                header.append("\n");
            }
        }
        mWriter.write(header.toString());
        for (CSVObject csvObject : objects) {
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < keys.length; i++) {
                content.append(csvObject.get((String) keys[i]));
                if (i == keys.length - 1) {
                    content.append("\n");
                }else{
                    content.append(",");
                }
            }
            mWriter.write(content.toString());
        }
        mWriter.flush();
    }
}
