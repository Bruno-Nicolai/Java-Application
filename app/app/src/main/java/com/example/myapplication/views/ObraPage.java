package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.adapters.ComentarioAdapter;
import com.example.myapplication.models.Avaliacao;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioAvalicao;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.AvaliacaoService;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class ObraPage extends AppCompatActivity {
    private ImageView fundo;
    private ImageView card;
    private TextView descricao;
    private TextView numeroPaginas;
    private TextView numeroCurtidas;
    private TextView nota;
    private TextView tituloObraId;
    private View publicarLayout;
    private View comentario;

    private ImageView voltarId2;
    private ImageView curtirIcone;
    private ImageView imagemIcone;
    private TextView nomeUsuarioComentario;
    private TextView comentarioText;
    private TextView periodoComentario;
    private Button publicar;
    private TextView comentarioTextView;

    private ImageView estrelaUmId;
    private ImageView estrelaDoisId;
    private ImageView estrelaTresId;
    private ImageView estrelaQuatroId;
    private ImageView estrelaCincoId;

    private ImageView estrelaUmId2;
    private ImageView estrelaDoisId2;
    private ImageView estrelaTresId2;
    private ImageView estrelaQuatroId2;
    private ImageView estrelaCincoId2;

    private int notaAtual = 0;

    RepositorioObras repositorioObras;
    RepositorioAvalicao repositorioAvalicao;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obra_page);

        int id_obra = getIncomingIntentId();

        this.repositorioObras = RepositorioObras.getInstance();
        this.repositorioAvalicao = RepositorioAvalicao.getInstance();

        if (repositorioAvalicao.comentarioUsuarioLogado == null) {
            AvaliacaoService.buscarComentarios(id_obra, onSuccess -> {
                this.init();
            }, null);
        } else {
            this.init();
        }
    }

    private void init() {
        int id_obra = getIncomingIntentId();

        Obra obra = repositorioObras.filtro(id_obra);

        repositorioObras.filtro(id_obra);
        fundo = findViewById(R.id.ImagemFundoId);
        card = findViewById(R.id.imagemPrincipalId);
        descricao = findViewById(R.id.descricaoInfoId);
        numeroPaginas = findViewById(R.id.numeroPaginasId);
        numeroCurtidas = findViewById(R.id.numeroCurtidasId);
        nota = findViewById(R.id.notaId);
        publicarLayout = findViewById(R.id.publicarLayoutId);
        comentario = findViewById(R.id.layoutComentarioId);
        publicar = findViewById(R.id.publicarId);
        comentarioTextView = findViewById(R.id.comentarioTextViewId);
        nomeUsuarioComentario = findViewById(R.id.nomeUsuarioComentarioId);
        periodoComentario = findViewById(R.id.periodoComentarioId);
        comentarioText = findViewById(R.id.comentarioId);
        tituloObraId = findViewById(R.id.tituloObraId);
        voltarId2 = findViewById(R.id.voltarId2);

        estrelaUmId = findViewById(R.id.estrelaUmId);
        estrelaDoisId = findViewById(R.id.estrelaDoisId);
        estrelaTresId = findViewById(R.id.estrelaTresId);
        estrelaQuatroId = findViewById(R.id.estrelaQuatroId);
        estrelaCincoId = findViewById(R.id.estrelaCincoId);

        estrelaUmId2 = findViewById(R.id.estrelaUmId2);
        estrelaDoisId2 = findViewById(R.id.estrelaDoisId2);
        estrelaTresId2 = findViewById(R.id.estrelaTresId2);
        estrelaQuatroId2 = findViewById(R.id.estrelaQuatroId2);
        estrelaCincoId2 = findViewById(R.id.estrelaCincoId2);

        ApplicationService service = ApplicationService.getInstance();

        estrelaUmId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        estrelaDoisId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        estrelaTresId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        estrelaQuatroId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        estrelaCincoId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);

        estrelaUmId.setOnClickListener(v -> {
            estrelaUmId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaDoisId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaTresId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaQuatroId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaCincoId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);

            notaAtual = 1;
        });

        estrelaDoisId.setOnClickListener(v -> {
            estrelaUmId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaDoisId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaTresId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaQuatroId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaCincoId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);

            notaAtual = 2;
        });

        estrelaTresId.setOnClickListener(v -> {
            estrelaUmId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaDoisId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaTresId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaQuatroId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaCincoId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);

            notaAtual = 3;
        });

        estrelaQuatroId.setOnClickListener(v -> {
            estrelaUmId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaDoisId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaTresId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaQuatroId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaCincoId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);

            notaAtual = 4;
        });

        estrelaCincoId.setOnClickListener(v -> {
            estrelaUmId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaDoisId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaTresId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaQuatroId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaCincoId.setColorFilter(ContextCompat.getColor(service.getContext(), R.color.amarelo), android.graphics.PorterDuff.Mode.MULTIPLY);

            notaAtual = 5;
        });


        voltarId2.setOnClickListener(v -> finish());

        publicar.setOnClickListener(view -> {
            String newComentario = comentarioTextView.getText().toString();

            repositorioAvalicao.comentarioUsuarioLogado.comentario = newComentario;
            repositorioAvalicao.comentarioUsuarioLogado.idObra = id_obra;
            repositorioAvalicao.comentarioUsuarioLogado.nota = notaAtual;

            Log.wtf("Beijinho", repositorioAvalicao.comentarioUsuarioLogado.comentario);
            Log.wtf("Beijinho", newComentario);
            try {
                AvaliacaoService.criarComentario(onServiceDone -> {
                    finish();
                    Intent intentObra = new Intent(service.getContext(), ObraPage.class);
                    intentObra.putExtra("id_obra", id_obra);
                    service.getContext().startActivity(intentObra);
                }, onError -> {
                    Log.wtf("Goiabinha", onError.mensagem);
                });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        if (obra.urlImagem != null) {
            String imagemObra = obra.urlImagem;
            Picasso.get().load(imagemObra).resize(1600, 2272).onlyScaleDown().transform(new BlurTransformation(this)).into(fundo);
            Picasso.get().load(imagemObra).resize(1600, 2272).onlyScaleDown().into(card);
        } else {
            card.setImageResource(R.drawable.blank);
        }
        if (repositorioAvalicao.comentarioUsuarioLogado != null && repositorioAvalicao.comentarioUsuarioLogado.id != 0) {
            publicarLayout.setVisibility(View.GONE);
            comentario.setVisibility(View.VISIBLE);

            Avaliacao avaliacao = repositorioAvalicao.comentarioUsuarioLogado;
            Log.wtf("aaa", avaliacao.comentario + " aquiii");

            nomeUsuarioComentario.setText(repositorioAvalicao.comentarioUsuarioLogado.nome);
            comentarioText.setText(avaliacao.comentario);

            LocalDate dataComentario = LocalDate.parse((avaliacao.created_at).substring(0, 10));
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between(dataComentario, dataAtual);
            String periodoFormatado;
            if (periodo.getYears() >= 1) {
                periodoFormatado = "Há " + periodo.getYears() + " ano/s";
            } else if (periodo.getMonths() >= 1) {
                periodoFormatado = "Há " + periodo.getMonths() + " meses";
            } else if (periodo.getDays() >= 31) {
                int semanas = periodo.getDays() / 7;
                periodoFormatado = "Há " + semanas + " semana/s";
            } else {
                periodoFormatado = "Há " + periodo.getDays() + " dias";
            }
            periodoComentario.setText(periodoFormatado);

            estrelaUmId2.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 1 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaDoisId2.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 2 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaTresId2.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 3 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaQuatroId2.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 4 ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
            estrelaCincoId2.setColorFilter(ContextCompat.getColor(service.getContext(), avaliacao.nota >= 5
                    ? R.color.amarelo : R.color.cinza_2), android.graphics.PorterDuff.Mode.MULTIPLY);
        }

        descricao.setText(repositorioObras.filtro(id_obra).descricao);

        if (repositorioObras.filtro(id_obra).titulo.length() > 15)
            tituloObraId.setText(repositorioObras.filtro(id_obra).titulo.substring(0, 15) + "...");
        else
            tituloObraId.setText(repositorioObras.filtro(id_obra).titulo);

        numeroPaginas.setText(String.valueOf(repositorioObras.filtro(id_obra).qtVolumes));

        numeroCurtidas.setText(String.valueOf(repositorioObras.filtro(id_obra).qtAvaliacoes));

        nota.setText(new DecimalFormat("#.#").format(repositorioObras.filtro(id_obra).nota) + "/5");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewComentariosId);
        List<Avaliacao> avaliacoes = repositorioAvalicao.avaliacaoLista;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new ComentarioAdapter(this, avaliacoes));
    }

    private int getIncomingIntentId() {
        RepositorioObras repositorioObras = RepositorioObras.getInstance();
        int id = getIntent().getIntExtra("id_obra", 0);
        Log.wtf("Feijoada", String.valueOf(id));
        return id;
    }
}