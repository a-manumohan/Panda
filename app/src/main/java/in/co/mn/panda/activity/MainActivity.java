package in.co.mn.panda.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import in.co.mn.panda.PandaApplication;
import in.co.mn.panda.R;
import in.co.mn.panda.network.NetworkManager;

public class MainActivity extends AppCompatActivity {
    @Inject
    NetworkManager mNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((PandaApplication) getApplication()).getPandaComponent().inject(this);
    }
}
