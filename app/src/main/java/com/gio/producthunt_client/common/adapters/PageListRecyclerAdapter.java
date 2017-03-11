package com.gio.producthunt_client.common.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gio.producthunt_client.R;
import com.gio.producthunt_client.model.Post;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public class PageListRecyclerAdapter extends RecyclerView.Adapter<PageListRecyclerAdapter.CategoryItemViewHolder> {

    private List<Post> postList;
    private Context context;

    public PageListRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.supply_item, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryItemViewHolder holder, int position) {

        final Supply supply = getSupply(position);

        int typeOfAccount = preferences.getInt(Config.PREF_ACCOUNT_TYPE, 0);

        // отображаем данные в заголовке и описании
        final String wasteTitle = supply.getWaste() + " " + supply.getQuantity() + " " + supply.getUnit(); // данные о виде и количестве отходов
        final String formattedExportDate = Utils.formatDateTime("EEE, d MMM HH:mm", supply.getExport()); // дата и время вывоза
        final String formattedPerformedDate = Utils.formatDateTime("EEE, d MMM HH:mm", supply.getExport()); // дата и время завершения заказа
        if (pageType == PageType.NEW || pageType == PageType.WAIT) {
            holder.titleTV.setText(wasteTitle);
            holder.descTV.setText(formattedExportDate);
        } else {
            holder.titleTV.setText(formattedPerformedDate);
            holder.descTV.setText(wasteTitle);
        }

        // отображаем кнопки
        holder.secondTVBtn.setVisibility(pageType == PageType.HISTORY || typeOfAccount == TypeOfAccount.SELLER ? View.GONE : View.VISIBLE);
        if (pageType == PageType.WAIT) holder.secondTVBtn.setText(R.string.pay);
        RxView.clicks(holder.secondTVBtn).subscribe(aVoid -> {
            if (pageType == PageType.NEW) {
                bus.send(new MainPerformEvent(supply.getId()));
            } else {
                bus.send(new PayEvent(supply));
            }
        });
        RxView.clicks(holder.firstTVBtn).subscribe(aVoid -> {
            bus.send(new MoreEvent(supply));
        });
    }

    @Override
    public int getItemCount() {
        return supplies.size();
    }

    private Supply getSupply(int position) {
        return supplies.get(position);
    }

    public void update(List<Supply> supplies) {
        this.supplies.clear();
        this.supplies.addAll(supplies);
        notifyDataSetChanged();
    }

    // view holder class ======================
    class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;
        TextView descTV;
        TextView firstTVBtn;
        TextView secondTVBtn;

        CategoryItemViewHolder(View itemView) {
            super(itemView);
            titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            descTV = (TextView) itemView.findViewById((R.id.descTV));
            firstTVBtn = (TextView) itemView.findViewById(R.id.firstTVBtn);
            secondTVBtn = (TextView) itemView.findViewById(R.id.secondTVBtn);
        }
    }
}
