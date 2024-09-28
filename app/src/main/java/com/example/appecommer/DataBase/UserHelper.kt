package com.example.appecommer.DataBase

import com.example.appecommer.Model.User

class UserHelper {

    // Kiểm tra xem mật khẩu của người dùng có trùng khớp với mật khẩu xác nhận không
    fun isPasswordMatching(user: User): Boolean {
        return user.password == user.confirmPassword
    }

    // Kiểm tra tính hợp lệ của các trường cần thiết khi đăng ký
    fun isValidForRegister(user: User): Pair<Boolean, String?> {
        return when {
            user.name.isEmpty() -> {
                Pair(false, "Hãy điền thông tin tên")
            }
            user.username.isEmpty() -> {
                Pair(false, "Hãy điền email")
            }
            !user.username.endsWith("@gmail.com") -> {
                Pair(false, "Email không hợp lệ. Vui lòng sử dụng địa chỉ email @gmail.com")
            }
            user.password.isEmpty() -> {
                Pair(false, "Hãy điền mật khẩu")
            }
            user.password.length < 6 || !user.password.matches(".*[!@#$%^&*()].*".toRegex()) -> {
                Pair(false, "Mật khẩu phải có ít nhất 6 ký tự và chứa ít nhất một ký tự đặc biệt")
            }
            user.confirmPassword.isEmpty() -> {
                Pair(false, "Hãy điền xác nhận mật khẩu")
            }
            user.password != user.confirmPassword -> {
                Pair(false, "Mật khẩu và xác nhận mật khẩu phải giống nhau")
            }
            user.phoneNumber <= 0 -> {
                Pair(false, "Số điện thoại không hợp lệ")
            }
            else -> {
                Pair(true, null) // Tất cả hợp lệ
            }
        }
    }

    // Kiểm tra tính hợp lệ của thông tin đăng nhập
    fun isValidForLogin(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}
