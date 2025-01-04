package com.nitishsharma.customsnacks

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.nitishsharma.customsnacks.databinding.LayoutCustomSnacksBinding

@SuppressLint("RestrictedApi")
fun View.showSnack(
    snackMessage: String,
    showSuccessErrorIcon: Boolean = false, //to show error and success icon
    isSuccess: Boolean = false, //to show green check, or red exclamation
    isActionButtonEnabled: Boolean = false, //to do some action on click of action button in snackBar
    actionButtonText: String = "", //to set text of action button
    snackBarBackgroundColor: Int? = null,
    titleFontFamily: Typeface? = null,
    actionFontFamily: Typeface? = null,
    titleFontTextColor: Int? = null,
    actionFontTextColor: Int? = null,
    onActionButtonClick: (() -> Unit)? = null //to receive a callback on the click of action button
) {
    val snackBar = Snackbar.make(this, "", Snackbar.LENGTH_LONG)
    val snackBarBinding = LayoutCustomSnacksBinding.inflate(LayoutInflater.from(context))
    val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout

    snackBarBackgroundColor?.let { snackBarBinding.cardView.setCardBackgroundColor(it) }

    snackBarLayout.setBackgroundColor(Color.TRANSPARENT)
    snackBarLayout.setPadding(0, 0, 0, 0)

    snackBarBinding.title.text = snackMessage
    snackBarBinding.viewDownload.visibility = if (isActionButtonEnabled) View.VISIBLE else View.GONE
    snackBarBinding.viewDownload.text = actionButtonText
    titleFontFamily?.let { snackBarBinding.title.typeface = it }
    actionFontFamily?.let { snackBarBinding.viewDownload.typeface = it }
    titleFontTextColor?.let { snackBarBinding.title.setTextColor(it) }
    actionFontTextColor?.let { snackBarBinding.viewDownload.setTextColor(it) }

    if (showSuccessErrorIcon) {
        if (isSuccess) {
            snackBarBinding.done.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_green_check
                )
            )
            snackBarBinding.done.visibility = View.VISIBLE
        } else {
            snackBarBinding.done.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_red_cross
                )
            )
            snackBarBinding.done.visibility = View.VISIBLE
        }
    } else {
        snackBarBinding.done.visibility = View.GONE
    }

    snackBarBinding.viewDownload.setOnClickListener {
        onActionButtonClick?.invoke()
    }

    snackBarLayout.removeAllViews()
    snackBarLayout.addView(snackBarBinding.root, 0)
    snackBarLayout.elevation = resources.getDimension(R.dimen.siq_10)
    snackBar.show()
}