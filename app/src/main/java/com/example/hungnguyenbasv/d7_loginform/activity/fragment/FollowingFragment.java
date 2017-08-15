package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class FollowingFragment extends Fragment {
    static final String TAG = "FollowingFragment TAG";
    SharedPreferences sharedPreferences;
    RecyclerView listFollowing;

    private OnFragmentInteractionListener mListener;
    private String token;
    private List<ShowFollowingResponse.User> followings = new ArrayList<>();

    public FollowingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getActivity().
                getSharedPreferences(LoginActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listFollowing = (RecyclerView) view.findViewById(R.id.listFollowing);
        APIService apiService = APIUtils.getAPIService();
        apiService.showFollowing(token).enqueue(new Callback<ShowFollowingResponse>() {
            @Override
            public void onResponse(Call<ShowFollowingResponse> call, Response<ShowFollowingResponse> response) {
                if (response.isSuccessful()) {
                    followings = response.body().getData().getUser();
                    RecycleViewFollowingAdapter adapter = new RecycleViewFollowingAdapter(getContext(), followings);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    listFollowing.setLayoutManager(layoutManager);
                    adapter.notifyDataSetChanged();
                    listFollowing.setAdapter(adapter);
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
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
