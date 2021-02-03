package com.xwl.mvvm.business.cardlist.weigth;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xwl.mvvm.R;
import com.xwl.mvvm.business.cardlist.bean.CardBean;
import com.xwl.mvvm.databinding.CardListItemBinding;

import java.util.List;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist.weigth
 * @ClassName: CardListAdapter
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 14:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 14:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardListViewHolder> {
    private List<CardBean> list;

    public CardListAdapter(@NonNull List<CardBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent
                .getContext()), R.layout.card_list_item, parent, false);
        return new CardListViewHolder(viewDataBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CardListViewHolder holder, int position) {
        CardListItemBinding cardListItemBinding = DataBindingUtil.getBinding(holder.itemView);
        CardBean cardBean = list.get(position);
        cardListItemBinding.setVariable(BR.cardBean, cardBean);
        if (TextUtils.isEmpty(cardBean.getPictureUrl())) {
            cardListItemBinding.ivHead.setImageDrawable(null);
        } else {
            Glide.with(cardListItemBinding.ivHead).load(cardBean.getPictureUrl()).centerCrop().into(cardListItemBinding.ivHead);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CardListViewHolder extends RecyclerView.ViewHolder {

        public CardListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
