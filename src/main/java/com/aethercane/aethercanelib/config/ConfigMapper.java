package com.aethercane.aethercanelib.config;

import com.aethercane.aethercanelib.AetherCaneLib;
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

public enum ConfigMapper {

    YAML(".yml", YAMLMapper.builder().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER).build());

    private final String extension;
    private final ObjectMapper mapper;

    private static final JavaPlugin plugin = AetherCaneLib.getPlugin();

    ConfigMapper(String extension, ObjectMapper mapper) {
        this.extension = extension;
        this.mapper = mapper;
    }

    public <T> T load(String path, Class<T> type) {
        File file = new File(plugin.getDataFolder(), path + extension);
        String resourcePath = plugin.getDataFolder().toPath().relativize(file.toPath()).toString().replace(File.separatorChar, '/');

        if (!file.exists()) {
            plugin.saveResource(resourcePath, false);
            try {
                return mapper.readValue(file, type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        InputStream stream = plugin.getResource(resourcePath);
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));
        configuration.options().copyDefaults(true);
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public <T> void save(String path, T value) {
//        File file = new File(plugin.getDataFolder(), path);
//        try {
//            mapper.writeValue(file, value);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
