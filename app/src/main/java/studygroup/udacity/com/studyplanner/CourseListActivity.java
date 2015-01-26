package studygroup.udacity.com.studyplanner;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import studygroup.udacity.com.studyplanner.data.Courses;

public class CourseListActivity extends ActionBarActivity {

    private ListView listView;
    private CourseListAdapter courseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        listView = (ListView) findViewById(R.id.listView);
        courseListAdapter = new CourseListAdapter(this);
        listView.setAdapter(courseListAdapter);
        listView.setOnItemClickListener(courseListAdapter);

        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        new AsyncTask<Void, Void, Courses>() {

            @Override
            protected Courses doInBackground(Void... params) {
                HttpURLConnection connection = null;
                InputStream stream = null;
                InputStreamReader reader = null;
                try {
                    URL url = new URL("https://www.udacity.com/public-api/v0/courses");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    stream = connection.getInputStream();
                    reader = new InputStreamReader(stream);
                    return new Gson().fromJson(reader, Courses.class);
                } catch (IOException e) {
                    e.printStackTrace(); // temp.
                } finally {
                    try {
                        if (connection != null) connection.disconnect(); // need?
                        if (reader != null) reader.close();
                        if (stream != null) stream.close();
                    } catch (Exception ignored) {
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Courses fetched) {
                if (fetched == null) {
                    return; // do nothing.
                }
                courseListAdapter.addAll(fetched.getCourses());
            }
        }.execute();
    }

    private static class CourseListAdapter extends ArrayAdapter<Courses.Course> implements AdapterView.OnItemClickListener {

        public CourseListAdapter(Context context) {
            super(context, R.layout.course_list_item, R.id.course_title);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.course_list_item, parent, false);
            } else {
                view = convertView;
            }

            Courses.Course course = getItem(position);
            ImageView iv = (ImageView) view.findViewById(R.id.course_image);
            Glide.with(getContext())
                    .fromString()
                    .load(course.getImage())
                    .fitCenter()
                    .error(R.drawable.ic_launcher)
                    .into(iv);

            TextView titleView = (TextView) view.findViewById(R.id.course_title);
            TextView subtitleView = (TextView) view.findViewById(R.id.course_subtitle);

            titleView.setText(course.getTitle());
            subtitleView.setText(course.getSubtitle());

            return view;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CourseDetailActivity.startActivityWith(getContext(),getItem(position));
        }
    }

}
