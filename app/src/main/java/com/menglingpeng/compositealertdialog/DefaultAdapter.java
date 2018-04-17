package com.menglingpeng.compositealertdialog;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.menglingpeng.compositealertdialog.Util.DialogUtil;
import com.menglingpeng.compositealertdialog.Util.GravityEnum;

/**
 * Created by mengdroid on 2018/4/17.
 */

public class DefaultAdapter extends RecyclerView.Adapter<DefaultAdapter.DefaultVH> {

    private final CompositeAlertDialog dialog;
    private final int layout;
    private final GravityEnum itemGravity;
    private DialogBuilder builder;

    DefaultAdapter(CompositeAlertDialog dialog, @LayoutRes int layout) {
        this.dialog = dialog;
        this.layout = layout;
        this.itemGravity = builder.itemsGravity;
    }

    @Override
    public DefaultVH onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        DialogUtil.setBackgroundCompat(view, dialog.getListSelector());
        return new DefaultVH(view, this);
    }

    @Override
    public void onBindViewHolder(DefaultVH holder, int position) {
        final View view = holder.itemView;
    }

    @Override
    public int getItemCount() {
        return builder.items != null ? builder.items.size() : 0;
    }

    interface InternalListCallback {

        boolean onItemSelected(
                CompositeAlertDialog dialog,
                View itemView,
                int position,
                @Nullable CharSequence text,
                boolean longPress);
    }

    public static class DefaultVH extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        final CompoundButton control;
        final TextView title;
        final DefaultAdapter adapter;

        DefaultVH(View itemView, DefaultAdapter adapter) {
            super(itemView);
            control = itemView.findViewById(R.id.md_control);
            title = itemView.findViewById(R.id.md_title);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
            if (adapter.builder.listLongCallback != null) {
                itemView.setOnLongClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
