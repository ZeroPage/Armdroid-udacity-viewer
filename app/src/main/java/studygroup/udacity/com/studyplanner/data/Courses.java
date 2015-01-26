package studygroup.udacity.com.studyplanner.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Courses {

    private ArrayList<Course> courses;

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public static class Course {
        private String key;
        private String title;
        private String subtitle;
        private String summary;
        private String image;
        private String homepage;
        private TeaserVideo teaser_video;

        public String getKey() {
            return key;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getSummary() {
            return summary;
        }

        public String getImage() {
            return image;
        }

        public String getHomepage() {
            return homepage;
        }

        public TeaserVideo getTeaser_video() {
            return teaser_video;
        }

        public static class TeaserVideo {
            private String youtube_url;

            public String getYoutube_url() {
                return youtube_url;
            }
        }
    }
}