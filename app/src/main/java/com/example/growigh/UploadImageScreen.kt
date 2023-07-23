package com.example.growigh

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_upload_image_screen.*

class UploadImageScreen : Fragment() {

    private val REQUEST_IMAGE_PICK = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload_image_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        select_image_button.setOnClickListener { onPickImageClick() }
    }

    private fun onPickImageClick() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            selectedImageUri?.let { uri ->
                startCropActivity(uri)
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val croppedImageUri: Uri = result.uri
                image_picker.setImageURI(croppedImageUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                // Handle cropping error if needed
            }
        }
    }

    private fun startCropActivity(sourceUri: Uri) {
        CropImage.activity(sourceUri)
            .setAspectRatio(1, 1) // Set cropping aspect ratio to 1:1 (square)
            .start(requireContext(), this)
    }
}