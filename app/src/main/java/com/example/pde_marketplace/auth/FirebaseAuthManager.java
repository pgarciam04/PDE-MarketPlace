package com.example.pde_marketplace.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.pde_marketplace.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public FirebaseAuthManager() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    // =========================
    // REGISTRO DE USUARIO
    // =========================
    public void registerUser(String email,
                             String password,
                             String name,
                             String phone,
                             String address,
                             Activity activity) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser == null) return;

                        String uid = firebaseUser.getUid();
                        User user = new User(email, name, phone, address);

                        db.collection("users")
                                .document(uid)
                                .set(user)
                                .addOnSuccessListener(v -> {

                                    // ✅ MENSAJE INMEDIATO
                                    Toast.makeText(
                                            activity,
                                            "Cuenta registrada correctamente",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    // ⏳ ESPERA 2 SEGUNDOS Y VUELVE AL LOGIN
                                    new Handler(Looper.getMainLooper())
                                            .postDelayed(() -> {

                                                Intent intent = new Intent(
                                                        activity,
                                                        LoginActivity.class
                                                );

                                                // 🔴 LIMPIA EL BACK STACK
                                                intent.addFlags(
                                                        Intent.FLAG_ACTIVITY_NEW_TASK |
                                                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                );

                                                activity.startActivity(intent);
                                                activity.finish();

                                            }, 2000);
                                })
                                .addOnFailureListener(e ->
                                        Toast.makeText(
                                                activity,
                                                "Error guardando datos del usuario",
                                                Toast.LENGTH_LONG
                                        ).show()
                                );

                    } else {
                        Toast.makeText(
                                activity,
                                "Error: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
    }

    // =========================
    // LOGIN
    // =========================
    public void loginUser(String email, String password,
                          Activity activity, Intent nextActivity) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(
                                activity,
                                "Inicio de sesión correcto",
                                Toast.LENGTH_SHORT
                        ).show();
                        activity.startActivity(nextActivity);
                        activity.finish();
                    } else {
                        Toast.makeText(
                                activity,
                                "Error: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
    }

    // =========================
    // LOGOUT
    // =========================
    public void logout(Activity activity, Intent loginActivity) {
        mAuth.signOut();
        activity.startActivity(loginActivity);
        activity.finish();
    }

    // =========================
    // USUARIO ACTUAL
    // =========================
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
}
