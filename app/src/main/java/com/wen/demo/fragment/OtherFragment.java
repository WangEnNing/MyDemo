package com.wen.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wen.demo.R;

/**
 * Created by wangenning on 15/11/17.
 */
public class OtherFragment  extends Fragment{
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        NewsAdapter recyclerViewAdapter = new NewsAdapter(getActivity(), setList());
//        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

//    private List<String> setList() {
//        List<String> dataList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            dataList.add(getActivity().getString(R.string.test_data) + i);
//        }
//        return dataList;
//
//    }
}
