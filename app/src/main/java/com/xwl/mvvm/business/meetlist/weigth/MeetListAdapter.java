package com.xwl.mvvm.business.meetlist.weigth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.xwl.mvvm.R;
import com.xwl.mvvm.business.meetlist.bean.MeetBean;

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
public class MeetListAdapter extends RecyclerView.Adapter<MeetListAdapter.MeetListViewHolder> {
    private List<MeetBean> list;

    public MeetListAdapter(@NonNull List<MeetBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MeetListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent
                .getContext()), R.layout.meet_list_item, parent, false);
        return new MeetListViewHolder(viewDataBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MeetListViewHolder holder, int position) {
        DataBindingUtil.getBinding(holder.itemView).setVariable(BR.meetBean, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MeetListViewHolder extends RecyclerView.ViewHolder {

        public MeetListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
