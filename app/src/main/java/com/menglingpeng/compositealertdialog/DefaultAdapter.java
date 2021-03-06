package com.menglingpeng.compositealertdialog;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
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
    private static DialogBuilder builder;
    private ListCallback callback;
    private Context context;

    public DefaultAdapter(Context context, CompositeAlertDialog dialog, @LayoutRes int layout) {
        this.context = context;
        this.dialog = dialog;
        this.layout = layout;
        this.itemGravity = builder.itemsGravity;
        builder = new DialogBuilder(context);
    }

    void setCallback(ListCallback callback) {
        this.callback = callback;
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
    private boolean isRTL(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return false;
        }
        Configuration config = context.getResources().getConfiguration();
        return config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }

    private void setupGravity(ViewGroup view) {
        final LinearLayout itemRoot = (LinearLayout) view;
        final int gravityInt = itemGravity.getGravityInt();
        itemRoot.setGravity(gravityInt | Gravity.CENTER_VERTICAL);

        if (view.getChildCount() == 2) {
            if (itemGravity == GravityEnum.END
                    && !isRTL(view.getContext())
                    && view.getChildAt(0) instanceof CompoundButton) {
                CompoundButton first = (CompoundButton) view.getChildAt(0);
                view.removeView(first);

                TextView second = (TextView) view.getChildAt(0);
                view.removeView(second);
                second.setPadding(
                        second.getPaddingRight(),
                        second.getPaddingTop(),
                        second.getPaddingLeft(),
                        second.getPaddingBottom());

                view.addView(second);
                view.addView(first);
            } else if (itemGravity == GravityEnum.START
                    && isRTL(view.getContext())
                    && view.getChildAt(1) instanceof CompoundButton) {
                CompoundButton first = (CompoundButton) view.getChildAt(1);
                view.removeView(first);

                TextView second = (TextView) view.getChildAt(0);
                view.removeView(second);
                second.setPadding(
                        second.getPaddingRight(),
                        second.getPaddingTop(),
                        second.getPaddingRight(),
                        second.getPaddingBottom());

                view.addView(first);
                view.addView(second);
            }
        }
    }

    interface ListCallback {

        boolean onItemSelected(
                CompositeAlertDialog dialog,
                View itemView,
                int position,
                @Nullable CharSequence text,
                boolean longPress);
    }

    public static class DefaultVH extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private final CompoundButton control;
        private final TextView title;
        private final DefaultAdapter adapter;

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
        public void onClick(View view) {
            if (adapter.callback != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                CharSequence text = null;
                if (builder.items != null
                        && getAdapterPosition() < builder.items.size()) {
                    text = builder.items.get(getAdapterPosition());
                }
                adapter.callback.onItemSelected(adapter.dialog, view, getAdapterPosition(), text, false);
            }

        }

        @Override
        public boolean onLongClick(View view) {
            if (adapter.callback != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                CharSequence text = null;
                if (builder.items != null
                        && getAdapterPosition() < builder.items.size()) {
                    text = builder.items.get(getAdapterPosition());
                }
                return adapter.callback.onItemSelected(
                        adapter.dialog, view, getAdapterPosition(), text, true);
            }
            return false;
        }
        }
    }

}
