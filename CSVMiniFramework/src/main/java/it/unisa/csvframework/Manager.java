package it.unisa.csvframework;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    public static <T> T loader(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String className = br.readLine();

            //ottengo i descrittori (attributi) di ciascun campo
            String[] descriptors = br.readLine().split(";");

            Map<Class<?>, List<Object>> entries = new HashMap<>();

            String line;
            while((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] stringFields = line.split(";");

                Class<?> ci = Class.forName("it.unisa.csvframework." + stringFields[0]);

                entries.computeIfAbsent(ci, k -> new ArrayList<>()).add(getParsedInstance(stringFields, descriptors));
            }


            //Reflection
            Class<?> c = Class.forName(className);

            T instance = (T) c.getConstructor().newInstance();

            //analizzo i campi (attributi dell'istanza della classe generata prima)
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {

                if (f.isAnnotationPresent(CSV.class) && f.getType().isArray()) {
                    Class<?> componentType = f.getType().getComponentType();

                    if (entries.containsKey(componentType)) {
                        List<Object> l = entries.get(componentType);

                        Object fieldArrayInstance = Array.newInstance(componentType, l.size());

                        for (int i = 0; i < l.size(); i++)  {
                            Array.set(fieldArrayInstance, i, l.get(i));
                        }

                        f.setAccessible(true);
                        f.set(instance, fieldArrayInstance);
                    }
                }
            }

            return instance;
        } catch (ClassNotFoundException | IOException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T getParsedInstance(String[] fields, String[] descriptors) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName("it.unisa.csvframework." + fields[0]);
        Method[] methods = c.getMethods();
        T instance = (T) c.getDeclaredConstructor().newInstance();

        for (int i = 0; i < fields.length; i++) {
            if (i < descriptors.length) {
                String setterName = "set" + Character.toUpperCase(descriptors[i].charAt(0)) +
                        descriptors[i].substring(1);

                for (Method m : methods) {
                    if (m.getName().equals(setterName) && m.getParameterCount() == 1) {
                        m.invoke(instance, fields[i]);
                        break;
                    }
                }
            }
        }

        return instance;
    }
}
