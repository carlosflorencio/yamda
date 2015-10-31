package isel.pdm.yamda.data.entity;

import java.util.List;

/**
 * Images Data Trasnfer Object
 * Used to store data of images of a specific movie from TMDb
 */
public class ImagesDTO {

    public class ImageDTO {
        private String file_path;
        private int width;
        private int height;
        private float aspect_ratio;

        public String getFile_path() {
            return file_path;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public float getAspect_ratio() {
            return aspect_ratio;
        }
    }

    /**
     * id of the specific movie
     */
    private int id;

    /**
     * List of data of several poster images specific to one movie
     */
    private List<ImageDTO> posters;

    public int getId() {
        return id;
    }

    public List<ImageDTO> getPosters() {
        return posters;
    }

    public ImageDTO getSmallestPoster(){
        ImageDTO image = posters.get(0);
        for (int i = 1; i < posters.size(); i++) {
            if (Float.compare(image.aspect_ratio, posters.get(i).aspect_ratio) > 0) {
                image = posters.get(i);
            }
        }
        return image;
    }

}
