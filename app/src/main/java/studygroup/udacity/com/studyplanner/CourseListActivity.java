package studygroup.udacity.com.studyplanner;

import android.content.Context;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import studygroup.udacity.com.studyplanner.data.Courses;

public class CourseListActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        // TODO: 리스트뷰 가져오고, 리스트뷰에 CourseListAdapter 어뎁터 연결 할 것
        // TODO: 아이템클릭 리스너도 연결할 것.

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
            // TODO: refresh 버튼 기능 구현 하시오.
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        // TODO: fetch API data from network.
        // hint AsyncTask를 사용하시오.
        /* URL url = new URL("https://www.udacity.com/public-api/v0/courses");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(stream); */


        // TODO 리스트 어뎁터에 값을 밀어넣을 것.
        // hint new Gson().fromJson(jsonString,Courses.class); 하면 얻을 수 있음.
    }

    private static class CourseListAdapter extends ArrayAdapter<Courses.Course> implements AdapterView.OnItemClickListener{

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
            // 이건 고한종이 즐겨 쓰는 방법으로, 어뎁터가 아이템클릭 리스너의 역할도 하게끔 하는 것.
            // 이러면 getItem(position); 메서드를 바로 쓸 수 있어서 타입변환이 필요 없는 장점이 있다.

            // TODO: CourseDetailActivity를 실행 시키는 Intent를 작성해서 작동시킬 것.

        }
    }
}
