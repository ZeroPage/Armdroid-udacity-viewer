package studygroup.udacity.com.studyplanner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import studygroup.udacity.com.studyplanner.data.Courses;

public class CourseDetailActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // 나중에 null check 등등 할 것
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String detail = extras.getString("detail");
        Courses.Course course = new Gson().fromJson(detail, Courses.Course.class);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(course.getTitle());

        TextView subtitle = (TextView) findViewById(R.id.subtitle);
        subtitle.setText(course.getSubtitle());

        ImageView sumnail = (ImageView) findViewById(R.id.sumnail);
        Uri uri = Uri.parse(course.getImage());
        Glide.with(this).load(uri).into(sumnail);

        TextView summary = (TextView) findViewById(R.id.summary);
        summary.setText(course.getSummary());
    }

    public static void startActivityWith(Context context, Courses.Course item) {
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra("detail", new Gson().toJson(item));
        context.startActivity(intent);
    }
}
