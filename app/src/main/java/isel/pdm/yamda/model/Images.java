package isel.pdm.yamda.model;

import java.util.List;

/**
 * Created by Nuno on 29/10/2015.
 */
public class Images {

    public class Image{
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

    private int id;

    private List<Image> posters;

    public int getId() {
        return id;
    }

    public List<Image> getPosters() {
        return posters;
    }

    public Image getSmallestPoster(){
        Image image = posters.get(0);
        for (int i = 1; i < posters.size(); i++) {
            if (Float.compare(image.aspect_ratio, posters.get(i).aspect_ratio) > 0) {
                image = posters.get(i);
            }
        }
        return image;
    }

}
