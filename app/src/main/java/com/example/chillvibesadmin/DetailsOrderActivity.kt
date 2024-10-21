package com.example.chillvibesadmin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chillvibesadmin.adapter.OrderDetailAdapter
import com.example.chillvibesadmin.databinding.ActivityDetailsOrderBinding
import com.example.chillvibesadmin.model.OrderDetails

class DetailsOrderActivity : AppCompatActivity() {

    private var userName: String? = null
    private var address: String? = null
    private var phoneNumber: String? = null
    private var totalPrice: String? = null
    private var foodNames: ArrayList<String> = arrayListOf()
    private var foodImages: ArrayList<String> = arrayListOf()
    private var foodQuantity: ArrayList<Int> = arrayListOf()
    private var foodPrices: ArrayList<String> = arrayListOf()


    private lateinit var binding: ActivityDetailsOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        getDataFromIntent()
    }

    private fun getDataFromIntent() {
        val receiveOderDetails = intent.getSerializableExtra("UserOrderDetails") as OrderDetails
        receiveOderDetails?.let { orderDetails ->
            userName = receiveOderDetails.userName
            foodNames = receiveOderDetails.foodNames as ArrayList<String>
            foodImages = receiveOderDetails.foodImages as ArrayList<String>
            foodQuantity = receiveOderDetails.foodQuantities as ArrayList<Int>
            address = receiveOderDetails.address
            phoneNumber = receiveOderDetails.phoneNumber
            foodPrices = receiveOderDetails.foodPrices as ArrayList<String>
            totalPrice = receiveOderDetails.totalPrice

            setUserDetails()
            setAdapter()
        }
    }

    private fun setAdapter() {
        binding.orderDetailsRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = OrderDetailAdapter(this, foodNames, foodImages, foodQuantity, foodPrices)
        binding.orderDetailsRecyclerView.adapter = adapter
    }

    private fun setUserDetails() {
        binding.name.text = userName
        binding.address.text = address
        binding.phone.text = phoneNumber
        binding.totalPay.text = totalPrice
    }
}