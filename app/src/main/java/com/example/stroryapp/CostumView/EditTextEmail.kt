package com.example.stroryapp.CostumView

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.example.stroryapp.R
import com.google.android.material.textfield.TextInputEditText

class EditTextEmail : TextInputEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val emailRegex = "^[A-Za-z0-9]+@[A-Za-z]+\\.com\$"
                val errorEmail = context.getString(R.string.erorEmail)

                if (!s.toString().matches(emailRegex.toRegex())) {
                    setError(errorEmail, null)
                } else {
                    error = null
                }
            }

            override fun afterTextChanged(s: Editable) {
                // Do nothing
            }

        })
    }
}