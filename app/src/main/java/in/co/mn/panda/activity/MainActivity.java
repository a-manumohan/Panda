package in.co.mn.panda.activity;

import android.os.Bundle;

import in.co.mn.panda.PandaApplication;
import in.co.mn.panda.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((PandaApplication) getApplication()).getPandaComponent().inject(this);
    }
}
