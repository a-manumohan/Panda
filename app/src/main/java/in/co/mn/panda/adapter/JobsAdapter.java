package in.co.mn.panda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.co.mn.panda.R;
import in.co.mn.panda.model.Job;
import in.co.mn.panda.util.Utils;

/**
 * Created by manuMohan on 10/03/2016.
 */
public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {

    private List<Job> mJobs;

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Job job = mJobs.get(position);

        holder.customerTextView.setText(job.getCustomerName());
        holder.priceTextView.setText(job.getPrice());
        holder.timeTextView.setText(job.getOrderTime());
        String recurrenceString = Utils.getRecurrenceString(holder.recurrenceTextView.getContext(), job.getRecurrency());
        holder.recurrenceTextView.setText(recurrenceString);
    }

    @Override
    public int getItemCount() {
        return mJobs == null ? 0 : mJobs.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.customer)
        TextView customerTextView;

        @Bind(R.id.price)
        TextView priceTextView;

        @Bind(R.id.job_time)
        TextView timeTextView;

        @Bind(R.id.recurrence)
        TextView recurrenceTextView;

        public JobViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<Job> getJobs() {
        return mJobs;
    }

    public void setJobs(List<Job> jobs) {
        this.mJobs = jobs;
    }
}
