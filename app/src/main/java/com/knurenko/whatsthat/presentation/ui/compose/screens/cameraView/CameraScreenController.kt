package com.knurenko.whatsthat.presentation.ui.compose.screens.cameraView

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCaseGroup
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.knurenko.whatsthat.presentation.camerax.analyzer.FrameAnalyzer
import com.knurenko.whatsthat.presentation.navigation.LocalRouter
import com.knurenko.whatsthat.presentation.ui.compose.components.drawer.NavigationDrawerContent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

/**
 * @author Knurenko Bogdan 31.08.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreenController() {
    val vm: CameraViewModel = koinViewModel()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()

    val previewView = remember { PreviewView(context) }

    val onObjectDetectedListener: FrameAnalyzer.ImageProcessorListener = koinInject { parametersOf(scope) }
    val imagePreview: Preview = koinInject { parametersOf(previewView) }
    val imageCapture: ImageCapture = koinInject()
    val imageAnalysis: ImageAnalysis = koinInject { parametersOf(onObjectDetectedListener) }

    LaunchedEffect(previewView) {
        startCamera(
            context = context,
            imagePreview = imagePreview,
            previewView = previewView,
            imageAnalysis = imageAnalysis,
            imageCapture = imageCapture,
            lifecycleOwner = lifecycleOwner
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val router = LocalRouter.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavigationDrawerContent() }
    ) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
        CameraScreenView(
            objectToHighlight = vm.detectedObject.collectAsState(initial = null).value,
            onMenuClick = {
                scope.launch {
                    drawerState.apply { if (isClosed) open() else close() }
                }
            },
            onGalleryClick = {
                router.navigateToGallery()
            }
        )
    }
}

private fun startCamera(
    context: Context,
    imagePreview: Preview,
    previewView: PreviewView,
    imageAnalysis: ImageAnalysis,
    imageCapture: ImageCapture,
    lifecycleOwner: LifecycleOwner
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener(
        {
            initCameraProvider(
                cameraProviderFuture,
                imagePreview,
                previewView,
                imageAnalysis,
                imageCapture,
                lifecycleOwner
            )
        },
        ContextCompat.getMainExecutor(context)
    )
}

private fun initCameraProvider(
    cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    imagePreview: Preview,
    previewView: PreviewView,
    imageAnalysis: ImageAnalysis,
    imageCapture: ImageCapture,
    lifecycleOwner: LifecycleOwner
) {
    // Used to bind the lifecycle of cameras to the lifecycle owner
    val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

    // Preview
    imagePreview.setSurfaceProvider(previewView.surfaceProvider)

    // Select back camera as a default
    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    try {
        // Unbind use cases before rebinding
        cameraProvider.unbindAll()

        previewView.viewPort?.let { viewport ->
            val useCaseGroup =
                UseCaseGroup.Builder()
                    .addUseCase(imageCapture)
                    .addUseCase(imageAnalysis)
                    .addUseCase(imagePreview)
                    .setViewPort(viewport)
                    .build()

            // Bind use cases to camera
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                useCaseGroup
            )
        }

    } catch (exc: Exception) {
        Log.e("check", "Use case binding failed", exc)
    }
}
