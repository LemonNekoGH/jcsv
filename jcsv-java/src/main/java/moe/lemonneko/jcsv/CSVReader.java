package moe.lemonneko.jcsv;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CSVReader extends FileReader {
    private final BufferedReader mReader = new BufferedReader(this);

    public CSVReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public CSVReader(File file) throws FileNotFoundException {
        super(file);
    }

    public <T> List<T> readAll(Class<T> clazz) throws IOException, IllegalAccessException, InstantiationException {
        CSVObjectList objList = readAll();
        List<T> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (CSVObject obj : objList) {
            T instance = clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                CSVField annotation = field.getAnnotation(CSVField.class);
                if (obj.containsKey(field.getName())) {
                    field.set(instance, obj.get(field.getName()));
                } else if (annotation != null && obj.containsKey(annotation.name())){
                    field.set(instance, obj.get(annotation.name()));
                } else{
                    field.set(instance, null);
                }
                field.setAccessible(false);
            }
            list.add(instance);
        }
        return list;
    }

    public CSVObjectList readAll() throws IOException {
        CSVObjectList list = new CSVObjectList();
        String[] keys = null;
        String line = mReader.readLine();
        boolean isFirstLine = true;
        while (line != null) {
            line = removeWhiteSpace(line);
            if (isFirstLine) {
                keys = line.split(",");
                isFirstLine = false;
            } else {
                String[] values = line.split(",");
                CSVObject object = new CSVObject();
                for (int k = 0; k < values.length; k++) {
                    object.set(keys[k], values[k]);

                }
                list.add(object);
            }
            line = mReader.readLine();
        }
        return list;
    }

    private String removeWhiteSpace(String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                continue;
            }
            builder.append(string.charAt(i));
        }
        return builder.toString();
    }
}
