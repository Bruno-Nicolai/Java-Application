package com.example.myapplication.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.BannerAdapter;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.services.FeedService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class BuscaFragment extends Fragment {
    List<Obra> obras = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.busca_fragment, container, false);
        TextView nomeUsuario = view.findViewById(R.id.nomeUsuarioId);
        EditText editTextBusca = view.findViewById(R.id.editTextBusca);

        progressBar = view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        editTextBusca.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String searchText = editTextBusca.getText().toString();

                    if (searchText.length() > 3) {
                        progressBar.setVisibility(View.VISIBLE);

                        RepositorioObras repositorioObras = RepositorioObras.getInstance();
                        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBusca);

                        FeedService.carregarPesquisa(searchText, onServiceDone -> {
                            progressBar.setVisibility(View.GONE);

                            if (onServiceDone.codigo != 500) {
                                // Aqui vou atualizar o array da View
                                obras = repositorioObras.listaFiltro;

                                Log.wtf("MEH", "onFocusChange: puts");

                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                });
                                recyclerView.setAdapter(new BannerAdapter(getActivity(), obras));
                            }
                        });
                    }
                }
            }
        });


        return view;
    }
}