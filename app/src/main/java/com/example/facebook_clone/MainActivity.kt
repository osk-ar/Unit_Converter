package com.example.facebook_clone

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@SuppressLint("UseSwitchCompatOrMaterialCode")
class MainActivity : AppCompatActivity() {

    // Declare My Variables
    var isDistance = true;
    var fromUnit = 1;

    private lateinit var active_weight_btn: Button
    private lateinit var active_distance_btn: Button
    private lateinit var weight_btn: Button
    private lateinit var distance_btn: Button

    private lateinit var switch_btn: Switch


    private lateinit var distanceUnits: HorizontalScrollView
    private lateinit var weightUnits: HorizontalScrollView

    private lateinit var mili_btn: Button
    private lateinit var centi_btn: Button
    private lateinit var meter_btn: Button
    private lateinit var Kilometer_btn: Button

    private lateinit var gram_btn: Button
    private lateinit var kilogram_btn: Button
    private lateinit var ton_btn: Button


    private lateinit var input_txt: EditText
    private lateinit var submit_btn: Button


    private lateinit var firstView: TextView
    private lateinit var secondView: TextView
    private lateinit var thirdView: TextView
    private lateinit var fourthView: TextView
    // *


    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Initialize Buttons
        active_weight_btn = findViewById<Button>(R.id.active_weight_btn);
        active_distance_btn = findViewById<Button>(R.id.active_distance_btn);
        weight_btn = findViewById<Button>(R.id.weight_btn);
        distance_btn = findViewById<Button>(R.id.distance_btn);

        switch_btn = findViewById<Switch>(R.id.category_switch);


        distanceUnits = findViewById<HorizontalScrollView>(R.id.distanceUnitsLayout);
        weightUnits = findViewById<HorizontalScrollView>(R.id.weightUnitsLayout);

        mili_btn = findViewById<Button>(R.id.Millimeter_btn);
        centi_btn = findViewById<Button>(R.id.Centimeter_btn);
        meter_btn = findViewById<Button>(R.id.Meter_btn);
        Kilometer_btn = findViewById<Button>(R.id.Kilometer_btn);

        gram_btn = findViewById<Button>(R.id.Gram_btn);
        kilogram_btn = findViewById<Button>(R.id.Kilogram_btn);
        ton_btn = findViewById<Button>(R.id.Ton_btn);


        input_txt = findViewById<EditText>(R.id.Input_txt);
        submit_btn = findViewById<Button>(R.id.submit_btn);

        firstView = findViewById<TextView>(R.id.first_output);
        secondView = findViewById<TextView>(R.id.second_output);
        thirdView = findViewById<TextView>(R.id.third_output);
        fourthView = findViewById<TextView>(R.id.fourth_output);
        // *


        // Set View to Distance First
        weightUnits.visibility = View.GONE;
        active_weight_btn.visibility = View.GONE;
        distance_btn.visibility = View.GONE;
        // *


        // Set onClickListeners
        weight_btn.setOnClickListener{ Button ->
            showWeight();
            distance_btn.visibility = View.VISIBLE;
            active_distance_btn.visibility = View.GONE;

            active_weight_btn.visibility = View.VISIBLE;
            weight_btn.visibility = View.GONE;

        }
        distance_btn.setOnClickListener{ Button ->
            showDistance();
            active_distance_btn.visibility = View.VISIBLE;
            distance_btn.visibility = View.GONE;

            weight_btn.visibility = View.VISIBLE;
            active_weight_btn.visibility = View.GONE;
        }
        switch_btn.setOnClickListener {
            switchUnit();
        }

        mili_btn.setOnClickListener{ Button ->
            fromUnit = 1;
        }
        centi_btn.setOnClickListener{ Button ->
            fromUnit = 2;
        }
        meter_btn.setOnClickListener { Button ->
            fromUnit = 3;
        }
        Kilometer_btn.setOnClickListener { Button ->
            fromUnit = 4;
        }

        gram_btn.setOnClickListener{ Button ->
            fromUnit = 5;
        }
        kilogram_btn.setOnClickListener{ Button ->
            fromUnit = 6;
        }
        ton_btn.setOnClickListener { Button ->
            fromUnit = 7;
        }

        submit_btn.setOnClickListener {
            if(input_txt.text.toString()==""){
                firstView.text = "Invalid Unit";
                secondView.text = "Invalid Unit";
                thirdView.text = "Invalid Unit";
                fourthView.text = "Invalid Unit";
            }
            else{
            var value = input_txt.text.toString().toDouble();

            if(fromUnit > 0 && fromUnit < 5){
                firstView.text = "millimeter: " + convertUnits(value, fromUnit, 1).toString();
                secondView.text = "centimeter: " + convertUnits(value, fromUnit, 2).toString();
                thirdView.text = "meter: " + convertUnits(value, fromUnit, 3).toString();
                fourthView.text = "kilometer: " + convertUnits(value, fromUnit, 4).toString();
            }
            else {
                firstView.text = "gram: " + convertUnits(value, fromUnit, 5).toString();
                secondView.text = "kilogram: " + convertUnits(value, fromUnit, 6).toString();
                thirdView.text = "ton: " + convertUnits(value, fromUnit, 7).toString();
                fourthView.text = "";
            }
            }
        }
        // *


    }

    // Category Switching Functions
    private fun switchUnit() {

            if(isDistance){
                distanceUnits.visibility = View.GONE;
                weightUnits.visibility = View.VISIBLE;

                distance_btn.visibility = View.VISIBLE;
                active_distance_btn.visibility = View.GONE;

                active_weight_btn.visibility = View.VISIBLE;
                weight_btn.visibility = View.GONE;

                fromUnit = 5;
                isDistance = !isDistance;
            }
        else{
                weightUnits.visibility = View.GONE;
                distanceUnits.visibility = View.VISIBLE;

                active_distance_btn.visibility = View.VISIBLE;
                distance_btn.visibility = View.GONE;

                weight_btn.visibility = View.VISIBLE;
                active_weight_btn.visibility = View.GONE;

                fromUnit = 1;
                isDistance = !isDistance;
        }
    }
    private fun showWeight() {

        if( distanceUnits.visibility == View.VISIBLE){
            distanceUnits.visibility = View.GONE;
            weightUnits.visibility = View.VISIBLE;
            isDistance = false;
            switch_btn.isChecked = true;
            fromUnit = 5;
        }
    }
    private fun showDistance() {

        if( weightUnits.visibility == View.VISIBLE){
            weightUnits.visibility = View.GONE;
            distanceUnits.visibility = View.VISIBLE;
            isDistance = true;
            switch_btn.isChecked = false;
            fromUnit = 1;
        }
    }
    // *

    // Unit Conversion Function
    private fun convertUnits(value: Double, fromUnit: Int, toUnit: Int): Double {
        when (fromUnit) {
            1 -> {
                when (toUnit) {
                    1 -> return value
                    2 -> return value * 0.1
                    3 -> return value * 0.001
                    4 -> return value * 0.000001
                }
            }

            2 -> {
                when (toUnit) {
                    1 -> return value * 10
                    2 -> return value
                    3 -> return value * 0.01
                    4 -> return value * 0.00001
                }
            }

            3 -> {
                when (toUnit) {
                    1 -> return value * 1000
                    2 -> return value * 100
                    3 -> return value
                    4 -> return value * 0.001
                }

            }

            4 -> {
                when (toUnit) {
                    1 -> return value * 1000000
                    2 -> return value * 100000
                    3 -> return value * 1000
                    4 -> return value
                }
            }
            5 -> {
                when (toUnit) {
                    5 -> return value
                    6 -> return value * 0.001
                    7 -> return value * 0.000001

                }
            }
            6 -> {
                when (toUnit) {
                    5 -> return value * 1000
                    6 -> return value
                    7 -> return value * 0.001
                }
            }
            7 -> {
                when (toUnit) {
                    5 -> return value * 1000000
                    6 -> return value * 1000
                    7 -> return value
                }
            }
            else -> {
                return 0.0
            }
        }
        return 99.123456
        }

    }