package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap implements Map<String, String> {
    private Map<String, String> map;
    private File file;

    public DiskMap(String filePath) throws IOException {
        this.map = new HashMap<>();
        this.file = new File(filePath);
        loadFromFile();
    }

    private void loadFromFile() throws IOException {
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        map.put(parts[0], parts[1]);
                    }
                }
            }
        }
    }

    private void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        }
    }

    @Override
    public String put(String key, String value) {
        String previousValue = map.put(key, value);
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return previousValue;
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String remove(Object key) {
        String previousValue = map.remove(key);
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return previousValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        map.clear();
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
