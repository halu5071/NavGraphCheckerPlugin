package io.moatwel.android.plugin.navigation.third;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import io.moatwel.android.plugin.navigation.R;

public class ThirdAFragment extends Fragment {

    public ThirdAFragment() {
        super(R.layout.fragment_third_a);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.move_to_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_third_A_to_third_B);
            }
        });
    }
}
