package in.co.mn.panda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by manuMohan on 10/03/2016.
 */
public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {
    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        public JobViewHolder(View itemView) {
            super(itemView);
        }
    }
}
