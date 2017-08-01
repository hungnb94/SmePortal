package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.model.GetProfileResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class ProfileFragment extends Fragment {
    static final String TAG = "ProfileFragment TAG";
    String token;
    TextView tvName, tvBiography;
    ImageView ivAvatar;
    Button btnRole1, btnRole2,
            btnFollower, btnFollowing, btnProject;


    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);
        getInfor();
        return view;
    }

    private void initView(View view) {
        ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvBiography = (TextView) view.findViewById(R.id.tvBiography);
        btnRole1 = (Button) view.findViewById(R.id.btnRole1);
        btnRole2 = (Button) view.findViewById(R.id.btnRole2);
        btnFollower = (Button) view.findViewById(R.id.btnFollower);
        btnFollowing = (Button) view.findViewById(R.id.btnFollowing);
        btnProject = (Button) view.findViewById(R.id.btnProject);
    }

    public void getInfor() {
        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences(
                        LoginActivity.SHARED_PREFERENCES,
                        Context.MODE_PRIVATE
                );
        token = sharedPreferences.getString("token", "");
        // Sử dụng retrofit để lấy thông tin
        APIService mAPIService = APIUtils.getAPIService();
        mAPIService.getProfile(token, "vi").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Gson gson = new Gson();
                        GetProfileResponse profileResponse = gson.fromJson(res, GetProfileResponse.class);
                        updateViewProfile(profileResponse);
                    } catch (Exception e) {
                        Log.e(TAG, "Exception get infor " + e.toString());
                        e.printStackTrace();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Failure get profile " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateViewProfile(GetProfileResponse profileResponse) {
        // update lại giao diện
        Picasso.with(getContext())
                .load(profileResponse.getData().getAvatarURL())
                .placeholder(R.drawable.refresh)
                .error(R.drawable.error)
                .into(ivAvatar);

        tvName.setText(profileResponse.getData().getUsers().getFirst_name() + " " +
                profileResponse.getData().getUsers().getLast_name());
        tvBiography.setText(profileResponse.getData().getUsers().getBiography());

//        btnFollower.setText(String.valueOf(profileResponse.getData().getu));

        int firstRoleID = profileResponse.getData().getRoles().getRole0();
        if (firstRoleID > 0) {
            switch (firstRoleID) {
                case 1:
                    btnRole1.setText(profileResponse.getData().getroles().get1());
                    break;
                case 2:
                    btnRole1.setText(profileResponse.getData().getroles().get2());
                    break;
                case 3:
                    btnRole1.setText(profileResponse.getData().getroles().get3());
                    break;
                case 4:
                    btnRole1.setText(profileResponse.getData().getroles().get4());
                    break;
                case 5:
                    btnRole1.setText(profileResponse.getData().getroles().get5());
                    break;
                case 6:
                    btnRole1.setText(profileResponse.getData().getroles().get6());
                    break;
                case 7:
                    btnRole1.setText(profileResponse.getData().getroles().get7());
                    break;
                default:
                    break;
            }
        } else {
            btnRole1.setVisibility(View.GONE);
        }

        int secondRoleID = profileResponse.getData().getRoles().getRole1();
        if (secondRoleID > 0) {
            switch (secondRoleID) {
                case 1:
                    btnRole2.setText(profileResponse.getData().getroles().get1());
                    break;
                case 2:
                    btnRole2.setText(profileResponse.getData().getroles().get2());
                    break;
                case 3:
                    btnRole2.setText(profileResponse.getData().getroles().get3());
                    break;
                case 4:
                    btnRole2.setText(profileResponse.getData().getroles().get4());
                    break;
                case 5:
                    btnRole2.setText(profileResponse.getData().getroles().get5());
                    break;
                case 6:
                    btnRole2.setText(profileResponse.getData().getroles().get6());
                    break;
                case 7:
                    btnRole2.setText(profileResponse.getData().getroles().get7());
                    break;
                default:
                    break;
            }
        } else {
            btnRole2.setVisibility(View.GONE);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
