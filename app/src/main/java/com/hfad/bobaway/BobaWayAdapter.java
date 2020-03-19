
package com.hfad.bobaway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfad.bobaway.R;
import com.hfad.bobaway.data.BobaWayItem;
import com.hfad.bobaway.data.BobaWayRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BobaWayAdapter extends RecyclerView.Adapter<BobaWayAdapter.SearchResultViewHolder> {
    private List<BobaWayItem> mSearchResultsList;
    private OnSearchResultClickListener mResultClickListener;

    interface OnSearchResultClickListener {
        void onSearchResultClicked(BobaWayItem repo);
    }

    public BobaWayAdapter(OnSearchResultClickListener listener) {
        mResultClickListener = listener;
    }

    public void updateSearchResults(List<BobaWayItem> searchResultsList) {
        mSearchResultsList = searchResultsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSearchResultsList != null) {
            return mSearchResultsList.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bobashop_list_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(mSearchResultsList.get(position));
    }


    class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mSearchResultTV;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_search_result);
            itemView.setOnClickListener(this);

        }
        void bind(BobaWayItem repo) {
            mSearchResultTV.setText(repo.name);
        }

        @Override
        public void onClick(View view) {
            BobaWayItem boba = mSearchResultsList.get(getAdapterPosition());
            mResultClickListener.onSearchResultClicked(boba);
        }
    }

}

