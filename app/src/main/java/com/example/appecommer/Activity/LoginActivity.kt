package com.example.appecommer.Activity



class LoginActivity : BaseAct() {
   /* private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    //    val repository = AuthRepository(firebase)
        viewModel = AuthViewModelFactory(repository).create(AuthViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.emailEdt.text.toString()
            val password = binding.passWordEdt.text.toString()

            viewModel.login(username, password)
        }

        viewModel.loginStatus.observe(this) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }*/
}