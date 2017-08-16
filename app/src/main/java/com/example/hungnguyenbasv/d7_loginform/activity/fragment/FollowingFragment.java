package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.adapter.RecycleViewFollowingAdapter;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ShowFollowingResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingFragment extends UpdateFragment {
    Activity context;
    static final String TAG = "FollowingFragment TAG";
    SharedPreferences sharedPreferences;
    RecyclerView listFollowing;

    private OnFragmentInteractionListener mListener;
    private String token;
    private List<ShowFollowingResponse.User> followings = new ArrayList<>();
    private RecycleViewFollowingAdapter adapter;
    private APIService apiService;

    public FollowingFragment(Activity context) {
        // Required empty public constructor
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getActivity().
                getSharedPreferences(LoginActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        adapter = new RecycleViewFollowingAdapter(getContext(), followings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        try {
//            Toast.makeText(getContext(), "InitView Following", Toast.LENGTH_SHORT).show();
            listFollowing = (RecyclerView) view.findViewById(R.id.listFollowing);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            listFollowing.setLayoutManager(layoutManager);
            listFollowing.setAdapter(adapter);
            apiService = APIUtils.getAPIService();
            apiService.showFollowing(token).enqueue(new Callback<ShowFollowingResponse>() {
                @Override
                public void onResponse(Call<ShowFollowingResponse> call, Response<ShowFollowingResponse> response) {
                    if (response.isSuccessful()) {
                        followings.clear();
                        followings.addAll(response.body().getData().getUser());
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ShowFollowingResponse> call, Throwable t) {
                    Toast.makeText(
                            getActivity(),
//                        getResources().getString(R.string.get_following_failure),
                            "Get list following failure",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.e(TAG, "Get list following failure");
                }
            });
        } catch (Exception e){

        }
    }

    public void update(){
        apiService = APIUtils.getAPIService();
        apiService.showFollowing(token).enqueue(new Callback<ShowFollowingResponse>() {

            public void onResponse(Call<ShowFollowingResponse> call, Response<ShowFollowingResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();
                    followings.clear();
                    followings.addAll(response.body().getData().getUser());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ShowFollowingResponse> call, Throwable t) {
                Log.e(TAG, "Get list following failure");
            }
        });
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
