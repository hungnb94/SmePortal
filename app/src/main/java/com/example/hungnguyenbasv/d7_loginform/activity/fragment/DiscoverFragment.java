package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.adapter.HintAdapter;
import com.example.hungnguyenbasv.d7_loginform.activity.adapter.RecycleViewProjectAdapter;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ListProjectResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    Spinner listFilter, listSortBy, listRole;
    RadioButton rbtEveryWhere, rbtNearMe, rbtNear;
    EditText edtNear;
    List<ListProjectResponse.Data> listProject;
    String token;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    RecycleViewProjectAdapter viewAdapter;

    public DiscoverFragment() {
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
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        initView(view);
        initListener();
        getListProject();
        return view;
    }

    private void getListProject() {
        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences(LoginActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        APIService apiService = APIUtils.getAPIService();
        apiService.getListProjects(
                token,
                "5|Mọi thứ|1|1",
                "1|Phổ biến|2|3",
                "4|Giám đốc nhân sự|3|4",
                "1|EveryWhere|4|5",
                "en"
        ).enqueue(new Callback<ListProjectResponse>() {
            @Override
            public void onResponse(Call<ListProjectResponse> call, Response<ListProjectResponse> response) {
                listProject = response.body().getData();
                viewAdapter = new RecycleViewProjectAdapter(getContext(), listProject);
                recyclerView.setAdapter(viewAdapter);// set adapter on recyclerview
            }

            @Override
            public void onFailure(Call<ListProjectResponse> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        listFilter = (Spinner) view.findViewById(R.id.spinnerFilter);
        listSortBy = (Spinner) view.findViewById(R.id.spinnerSortBy);
        listRole = (Spinner) view.findViewById(R.id.spinnerRoleAvailable);
        HintAdapter adapter = new HintAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.list_filter)
        );
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        listFilter.setAdapter(adapter);

// show hint
        listFilter.setSelection(adapter.getCount());
        // Tái sử dụng lại adapter
        adapter = new HintAdapter(getContext(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.list_sort_by)
        );
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        listSortBy.setAdapter(adapter);
        listSortBy.setSelection(adapter.getCount());
        // Tiếp tục sử dụng lại
        adapter = new HintAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.list_role)
        );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        listRole.setAdapter(adapter);
        listRole.setSelection(adapter.getCount());

        rbtEveryWhere = (RadioButton) view.findViewById(R.id.rbtEveryWhere);
        rbtNearMe = (RadioButton) view.findViewById(R.id.rbtNearMe);
        rbtNear = (RadioButton) view.findViewById(R.id.rbtNear);

        edtNear = (EditText) view.findViewById(R.id.edtNear);

        recyclerView = (RecyclerView)
                view.findViewById(R.id.listProject);
        recyclerView.setHasFixedSize(true);

        //Set RecyclerView type according to intent value
        recyclerView.setLayoutManager(
                new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                ));
        listProject = new ArrayList<>();
        viewAdapter = new RecycleViewProjectAdapter(getContext(), listProject);
        recyclerView.setAdapter(viewAdapter);// set adapter on recyclerview
        viewAdapter.notifyDataSetChanged();// Notify the adapter
    }

    private void initListener() {
        listFilter.setOnItemSelectedListener(this);
        listSortBy.setOnItemSelectedListener(this);
        listRole.setOnItemSelectedListener(this);

        rbtEveryWhere.setOnCheckedChangeListener(this);
        rbtNearMe.setOnCheckedChangeListener(this);
        rbtNear.setOnCheckedChangeListener(this);
    }

    public void clickExit() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            //get the id of the checked button for later reference
            int id = compoundButton.getId();

            //uncheck the other RadioButtons
            rbtEveryWhere.setChecked(id == R.id.rbtEveryWhere);
            rbtNearMe.setChecked(id == R.id.rbtNearMe);
            rbtNear.setChecked(id == R.id.rbtNear);

            if (rbtNear.isChecked()) {
                edtNear.setEnabled(true);
//                edtNear.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
            } else {
                edtNear.setEnabled(false);
//                edtNear.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGray));
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
