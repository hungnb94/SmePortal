package com.example.hungnguyenbasv.d7_loginform.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.model.DistrictResponse.District;
import com.example.hungnguyenbasv.d7_loginform.activity.model.DistrictResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ProfileResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.model.StateResponse.State;
import com.example.hungnguyenbasv.d7_loginform.activity.model.StateResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIService;
import com.example.hungnguyenbasv.d7_loginform.activity.remote.APIUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hung.nguyenba.sv on 7/24/2017.
 */

public class EditProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String TAG = "EditProfileFragment TAG";
    EditText edtFirstName, edtLastName, edtBiography;
    Spinner spinnerCountry, spinnerState, spinnerDistrict, spinnerFirstRoles, spinnerSecondRoles;
    Button btnSaveChange;
    ImageView imvProfile;

    String token;

    TreeMap<String, Integer> countries;
    TreeMap<String, Integer> states;
    TreeMap<String, Integer> districts;
    TreeMap<String, Integer> firstRoles;
    TreeMap<String, Integer> secondRoles;

    private APIService mAPIService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public EditProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        initView(view);
        addEvent();
        getInfor();
        return view;
    }

    private void initView(View view) {
        imvProfile = (ImageView) view.findViewById(R.id.imgvProfilePicture);

        edtFirstName = (EditText) view.findViewById(R.id.edtFirstnameFr);
        edtLastName = (EditText) view.findViewById(R.id.edtLastnameFr);
        edtBiography = (EditText) view.findViewById(R.id.edtBiography);

        spinnerCountry = (Spinner) view.findViewById(R.id.listCountry);
        spinnerState = (Spinner) view.findViewById(R.id.listState);
        spinnerDistrict = (Spinner) view.findViewById(R.id.listDistrict);
        spinnerFirstRoles = (Spinner) view.findViewById(R.id.listRole1);
        spinnerSecondRoles = (Spinner) view.findViewById(R.id.listRole2);

        btnSaveChange = (Button) view.findViewById(R.id.btnSaveChange);
    }

    private void addEvent() {
        btnSaveChange.setOnClickListener(this);

        spinnerCountry.setOnItemSelectedListener(this);
        spinnerState.setOnItemSelectedListener(this);
//        spinnerDistrict.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaveChange:
                editUserInfor();
                break;
        }

    }

    private void editUserInfor() {
        String fname = edtFirstName.getText().toString();
        String lname = edtLastName.getText().toString();
    }

    public void getInfor() {
//        // Lấy dữ liệu từ activity gửi sang
        Bundle args = getArguments();
        token = args.getString("token");

//        // Sử dụng retrofit để lấy thông tin
        mAPIService = APIUtils.getAPIService();
        mAPIService.getProfile(token, "vi").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Log.e(TAG, "Profile: " + res);
                        Gson gson = new Gson();
                        ProfileResponse profileResponse = gson.fromJson(res, ProfileResponse.class);
                        // update lại giao diện
                        Picasso.with(getContext())
                                .load(profileResponse.getData().getAvatarURL())
                                .placeholder(R.drawable.refresh)
                                .error(R.drawable.error)
                                .into(imvProfile);

                        edtFirstName.setText(profileResponse.getData().getUsers().getFirst_name());
                        edtLastName.setText(profileResponse.getData().getUsers().getLast_name());
                        edtBiography.setText(profileResponse.getData().getUsers().getBiography());
                        
                        String arrCountries[] = getCountries(new JSONObject(res)
                                .getJSONObject("data")
                                .getJSONObject("countries"));

                        int countryId = profileResponse.getData().getUsers().getCountry_id();
                        updateSpinner(spinnerCountry, arrCountries);

                        if (countryId > 0) {
                            // Set selection cho spinner phù hợp thông tin đầu vào
                            int i = 0;
                            for (Map.Entry<String, Integer> m : countries.entrySet()) {
                                if (m.getValue() == countryId) {
                                    spinnerCountry.setSelection(i);
                                    break;
                                }
                                i++;
                            }
                        }

                        String arrRole[] = getRole(new JSONObject(res)
                                .getJSONObject("data")
                                .getJSONObject("roles"));
                        updateSpinner(spinnerFirstRoles, arrRole);
                        int firstRoleID = profileResponse.getData().getRoles().getRole0();
                        if (firstRoleID > 0) {
                            // Đặt code set selection vào
                        }

                        updateSpinner(spinnerSecondRoles, arrRole);
                        int secondRoleID = profileResponse.getData().getRoles().getRole1();
                        if (secondRoleID > 0){
                            // Đặt code set selection vào
                        }
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

    private String[] getRole(JSONObject jsonObject) {
        String json = jsonObject.toString();

        // Bỏ mở ngoặc đầu và cuối đoạn
        json = json.substring(1, json.length() - 1);

        // Lấy danh sách các vai trò từ json
        String res[] = json.split(",");
        // Tách phần id và tên quốc gia thành 2 phần và đẩy vào mapCountries
        TreeMap<String, Integer> mapRole = new TreeMap<>();
        for (int i = 0; i < res.length; i++) {
            try {
                StringTokenizer tokenizer = new StringTokenizer(res[i], ":");
                String nextInt = tokenizer.nextToken().trim();
                int value = Integer.parseInt(nextInt.substring(1, nextInt.length() - 1));
                String nextStr = tokenizer.nextToken();
                String key = nextStr.substring(1, nextStr.length() - 1).trim();
                mapRole.put(key, value);
            } catch (Exception e) {
                Log.e(TAG, "Exception parse country into mapCountries" + e.toString());
            }
        }

        firstRoles = mapRole;
        secondRoles = mapRole;

        // Lấy dữ liệu countries và đẩy vào mảng dữ liệu
        res = new String[mapRole.size()];
        int i = 0;
        for (Map.Entry<String, Integer> m : mapRole.entrySet()) {
            String key = m.getKey();
            res[i] = key;
            i++;
        }
        return res;
    }

    private void updateSpinner(Spinner spinner, String[] arr) {
//        Gán dữ liệu source vào Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_item,
                arr);
//        phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
//        Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
    }

    private void updateSpinner(Spinner spinner, List<String> arr) {
//        Gán dữ liệu source vào Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_item,
                arr);
//        phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
//        Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
    }

    /*
    Lấy toàn bộ các countries trong danh sách trả về và nhét vào countries
    params jsonObjCountries dữ liệu đầu vào dạng json của all countries
     */
    private String[] getCountries(JSONObject jsonObjCountries) {
        String json = jsonObjCountries.toString();

        // Bỏ mở ngoặc đầu và cuối đoạn
        json = json.substring(1, json.length() - 1);

        // Lấy danh sách các nước từ json
        String res[] = json.split(",");
        // Tách phần id và tên quốc gia thành 2 phần và đẩy vào mapCountries
        TreeMap<String, Integer> mapCountries = new TreeMap<>();
        for (int i = 0; i < res.length; i++) {
            try {
                StringTokenizer tokenizer = new StringTokenizer(res[i], ":");
                String nextInt = tokenizer.nextToken().trim();
                int value = Integer.parseInt(nextInt.substring(1, nextInt.length() - 1));
                String nextStr = tokenizer.nextToken();
                String key = nextStr.substring(1, nextStr.length() - 1).trim();
                mapCountries.put(key, value);
            } catch (Exception e) {
                Log.e(TAG, "Exception parse country into mapCountries" + e.toString());
            }
        }

        countries = mapCountries;

        // Lấy dữ liệu countries và đẩy vào mảng dữ liệu
        res = new String[mapCountries.size()];
        int i = 0;
        for (Map.Entry<String, Integer> m : mapCountries.entrySet()) {
            String key = m.getKey();
            res[i] = key;
            i++;
        }
        return res;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.listCountry:
                countryClick(adapterView, position);
                break;
            case R.id.listState:
                stateClick(adapterView, position);
            default:
                break;
        }
    }

    private void stateClick(AdapterView<?> adapterView, int position) {
        String item = adapterView.getItemAtPosition(position).toString();
        final int stateID = states.get(item);
//        Toast.makeText(adapterView.getContext(), "Selected: " + item + " " + stateID, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "ID state selected " + stateID);
        getDistrictOfState(stateID);
    }

    private void getDistrictOfState(int stateID) {
        mAPIService = APIUtils.getAPIService();
        mAPIService.getDistrict(String.valueOf(stateID), token).enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                if (response.isSuccessful()) {
                    List<District> districtList = response.body().getData();
                    Collections.sort(districtList);
                    List<String> arrDistrict = new ArrayList<String>();
                    districts = new TreeMap<String, Integer>();
                    for (int i = 0; i < districtList.size(); i++) {
                        arrDistrict.add(districtList.get(i).getName());
                        districts.put(districtList.get(i).getName(), districtList.get(i).getId());
                    }
                    updateSpinner(spinnerDistrict, arrDistrict);
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {

            }
        });
    }

    private void countryClick(AdapterView adapterView, int position) {
        // On selecting a spinner item
        String item = adapterView.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(adapterView.getContext(), "Country selected: " + item, Toast.LENGTH_SHORT).show();

        getStateListOfCountry(item);
    }

    private void getStateListOfCountry(String country) {
        int countryID = countries.get(country);
        Log.e(TAG, "item selected " + country);
        mAPIService = APIUtils.getAPIService();
        mAPIService.getState(String.valueOf(countryID), token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Log.e(TAG, "Get state list response " + res);
                        Gson gson = new Gson();
                        StateResponse stateResponse = gson.fromJson(res, StateResponse.class);
                        List<State> stateList = stateResponse.getData();
                        Collections.sort(stateList);
                        List<String> arrState = new ArrayList<String>();
                        states = new TreeMap<String, Integer>();
                        for (int i = 0; i < stateList.size(); i++) {
                            arrState.add(stateList.get(i).getName());
                            states.put(stateList.get(i).getName(), stateList.get(i).getId());
                        }
                        updateSpinner(spinnerState, arrState);
                    } catch (Exception e) {
                        Toast.makeText(
                                getContext(),
                                "Exception get state of country " + e.toString(),
                                Toast.LENGTH_SHORT)
                                .show();
                        Log.e(TAG, "Exception get state of country " + e.toString());
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(),
                        "Get state of country failure: " + t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

}
