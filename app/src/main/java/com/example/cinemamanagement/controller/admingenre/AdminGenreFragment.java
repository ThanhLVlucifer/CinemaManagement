package com.example.cinemamanagement.controller.admingenre;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.GenreAdapter;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.controller.creategenre.CreateGenreActivity;
import com.example.cinemamanagement.controller.updategenre.UpdateGenreActivity;
import com.example.cinemamanagement.databinding.FragmentAdminGenreBinding;
import com.example.cinemamanagement.model.Genre;

import java.util.List;

public class AdminGenreFragment extends Fragment implements AdminGenreView, GenreAdapter.IClickGenreListener {
    private FragmentAdminGenreBinding fragmentAdminGenreBinding;
    private AdminGenrePresenter adminGenrePresenter;
    private List<Genre> listGenre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAdminGenreBinding = FragmentAdminGenreBinding.inflate(inflater, container, false);
        adminGenrePresenter = new AdminGenrePresenter(this);
        adminGenrePresenter.getListGenre(getContext(), "");
        setEvent();

        return fragmentAdminGenreBinding.getRoot();
    }

    private void setEvent() {
        setEventSearch();
        setEventAdd();
    }

    private void setEventAdd() {
        fragmentAdminGenreBinding.fabAdd.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getContext(), CreateGenreActivity.class);
        });
    }

    private void setEventSearch() {
        fragmentAdminGenreBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adminGenrePresenter.getListGenre(getContext(), newText);
                return true;
            }
        });
    }


    private void displayListGere() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        fragmentAdminGenreBinding.rcvGenre.setLayoutManager(layoutManager);

        GenreAdapter genreAdapter = new GenreAdapter(listGenre, this);
        fragmentAdminGenreBinding.rcvGenre.setAdapter(genreAdapter);
    }

    @Override
    public void loadListGenreSuccess(List<Genre> list) {
        listGenre = list;
        displayListGere();
    }

    @Override
    public void loadDataError() {
        GlobalFuntion.showToastMessage(getContext(), getString(R.string.msg_get_data_error));
    }

    @Override
    public void removeSuccess() {
        GlobalFuntion.showToastMessage(getContext(), getString(R.string.msg_remove_data_success));
        GlobalFuntion.startActivity(getContext(), AdminActivity.class);
    }

    @Override
    public void updateGenre(Genre genre) {
        gotoUpdateGenre(genre);
    }

    private void gotoUpdateGenre(Genre genre) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_GENRE_OBJECT, genre);
        GlobalFuntion.startActivity(getContext(), UpdateGenreActivity.class, bundle);
    }

    @Override
    public void deleteGenre(Genre genre) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.confirm_delete_genre))
                .setMessage(getString(R.string.message_delete_genre))
                .setPositiveButton(getString(R.string.delete), (dialogInterface, i) -> adminGenrePresenter.deleteGenre(getContext(), genre))
                .setNegativeButton(getString(R.string.cancel_delete), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
}
