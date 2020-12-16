package com.duolingo.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PerfilFragment extends Fragment {


    private static int coins;
    private static int points;

    public static int getCoins() {
        return coins;
    }

    public static void setCoins(int coins) {
        PerfilFragment.coins = coins;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        PerfilFragment.points = points;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textCoin = view.findViewById(R.id.textCoin);
        TextView textPoints = view.findViewById(R.id.textPoints);

        textCoin.setText(": "+ String.valueOf(getCoins()));
        textPoints.setText(": "+ String.valueOf(getPoints()));

    }

}