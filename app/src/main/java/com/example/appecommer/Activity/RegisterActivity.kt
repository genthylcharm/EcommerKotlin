package com.example.appecommer.Activity

import com.example.appecommer.databinding.ActivityRegisterBinding

class RegisterActivity : BaseAct() {
   /* private lateinit var binding: ActivityRegisterBinding
    private val userHelper = UserHelper()
    private val authRepository = AuthRepository(userHelper)
    private val authViewModel: AuthViewModel by viewModels { AuthViewModelFactory(authRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val user = User(
                username = binding.emailEdt.text.toString().trim(),
                password = binding.passwordEdt.text.toString().trim(),
                confirmPassword = binding.cfpassWordEdt.text.toString().trim(),
                name = binding.fullNameEdt.text.toString().trim(),
                phoneNumber = binding.numberPhoneEdt.text.toString().toIntOrNull() ?: 0
            )

            authViewModel.register(user)
        }

        // Quan sát trạng thái đăng ký
        authViewModel.registerStatus.observe(this, Observer { success ->
            if (success) {
                // Đăng ký thành công
            } else {
                // Thất bại
                authViewModel.errorMessage.observe(this, Observer { errorMessage ->
                    // Hiển thị thông báo lỗi
                })
            }
        })
    }*/
}