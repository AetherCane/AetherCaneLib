package com.aethercane.aethercanelib.config.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigMapper {

    private final JavaPlugin plugin;

    private final String extension;
    private final ObjectMapper mapper;

    public ConfigMapper(MapperType mapperType, JavaPlugin plugin) {
        this.plugin = plugin;
        this.extension = mapperType.extension;
        this.mapper = mapperType.mapper;
    }

    public <T> T load(String path, Class<T> type) {
        File file = new File(plugin.getDataFolder(), path + extension);

        if (!file.exists()) {
            plugin.saveResource(path + extension, false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        try (InputStream defStream = plugin.getResource(path + extension)) {
            if (defStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defStream));

                config.setDefaults(defConfig);
                config.options().copyDefaults(true);
            }
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void save(String path, T value) {
        File file = new File(plugin.getDataFolder(), path);
        try {
            mapper.writeValue(file, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public enum MapperType {

        YAML(".yml", YAMLMapper.builder().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER).build());

        private final String extension;
        private final ObjectMapper mapper;

        MapperType(String extension, ObjectMapper mapper) {
            this.extension = extension;
            this.mapper = mapper;
        }
    }
}
