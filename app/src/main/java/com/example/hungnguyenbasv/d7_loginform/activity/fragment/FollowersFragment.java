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
import com.example.hungnguyenbasv.d7_loginform.activity.adapter.RecycleViewFollowerAdapter;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ShowFollowingResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersFragment extends Fragment {
    static final String TAG = "FollowersFragment TAG";
    SharedPreferences sharedPreferences;
    RecyclerView listFollower;

    private OnFragmentInteractionListener mListener;
    private String token;
    private List<ShowFollowingResponse.User> followers;


    public FollowersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_followers, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        listFollower = (RecyclerView) view.findViewById(R.id.listFollower);
        APIService apiService = APIUtils.getAPIService();
        apiService.showFollower(token).enqueue(new Callback<ShowFollowingResponse>() {
            @Override
            public void onResponse(Call<ShowFollowingResponse> call, Response<ShowFollowingResponse> response) {
                if (response.isSuccessful()) {
                    followers = response.body().getData().getUser();
//                    Toast.makeText(getContext(), followings.get(1).getName(), Toast.LENGTH_SHORT).show();
                    RecycleViewFollowerAdapter adapter = new RecycleViewFollowerAdapter(getContext(), followers);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    listFollower.setLayoutManager(layoutManager);
                    adapter.notifyDataSetChanged();
                    listFollower.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ShowFollowingResponse> call, Throwable t) {
                Toast.makeText(
                        getActivity(),
//                        getResources().getString(R.string.get_following_failure),
                        "Get list follower failure",
                        Toast.LENGTH_SHORT
                ).show();
                Log.e(TAG, "Get list follower failure");
            }
        });
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
