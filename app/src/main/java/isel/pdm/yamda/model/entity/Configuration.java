package isel.pdm.yamda.model.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Nuno on 29/10/2015.
 */
public class Configuration {

    public class ImageConfigurations {
        String base_url;
        //String[] backdrop_sizes;
        //String[] logo_sizes;
        String[] poster_sizes;
    }

    private ImageConfigurations images;

    public ImageConfigurations getImageConfigurations() {
        return images;
    }

    public String getBaseUrl(){
        return images == null ? null : images.base_url;
    }

    public String[] getPosterSizes(){
        return images == null ? null : images.poster_sizes;
    }

    public static Configuration createFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(path));
        //TODO: Read from file
        throw new UnsupportedOperationException();
    }

    public static Configuration createFromCache() throws FileNotFoundException {
        //TODO: Read from cache
        throw new UnsupportedOperationException();
    }

}
