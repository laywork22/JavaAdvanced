import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CSVLoader {
    public static <T> T load(String file)  {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String className = br.readLine();


            String[] descriptors = br.readLine().split(";");

            Map<String, String[]> entries = new HashMap<>();

            String line;
            while((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                entries.put(fields[1], fields); //nome classe, campi
            }

            //reflection
            Class<?> c = Class.forName(className);

            T myInstance = (T) c.getDeclaredConstructor().newInstance();

            Field[] fs = c.getDeclaredFields();

            for (Field f : fs) {
                if (f.isAnnotationPresent(CSV.class) &&
                        entries.containsKey(f.getName()) &&
                        entries.get(f.getName())[0].equals(f.getType().getSimpleName())) {
                    Class<?> fieldType = f.getType();
                    Object fieldInstance = fieldType.getDeclaredConstructor().newInstance();

                    String[] csvFields = entries.get(f.getName());

                    Method[] m = fieldType.getMethods();

                    for (Method mc : m) {
                        for (int i = 2; i < csvFields.length; i++) {
                            String setterName = "set" + Character.toUpperCase(descriptors[i].charAt(0)) + descriptors[i].substring(1);

                            if (mc.getName().equals(setterName)) {
                                mc.invoke(fieldInstance, csvFields[i]);
                            }
                        }

                    }
                    f.setAccessible(true);
                    f.set(myInstance, fieldInstance);
                }
            }
            return myInstance;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
