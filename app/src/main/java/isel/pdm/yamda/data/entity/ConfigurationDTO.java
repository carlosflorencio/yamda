package isel.pdm.yamda.data.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Configuration Data Transfer Object
 * Used to store data from configuration of TMDb
 */
public class ConfigurationDTO {

    public class ImageConfigurationsDTO {
        String base_url;
        //String[] backdrop_sizes;
        //String[] logo_sizes;
        String[] poster_sizes;
    }

    private ImageConfigurationsDTO images;

    public ImageConfigurationsDTO getImageConfigurations() {
        return images;
    }

    public String getBaseUrl(){
        return images == null ? null : images.base_url;
    }

    public String[] getPosterSizes(){
        return images == null ? null : images.poster_sizes;
    }

    public static ConfigurationDTO createFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(path));
        //TODO: Read from file
        throw new UnsupportedOperationException();
    }

    public static ConfigurationDTO createFromCache() throws FileNotFoundException {
        //TODO: Read from cache
        throw new UnsupportedOperationException();
    }

}
