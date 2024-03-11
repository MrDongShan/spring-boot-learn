package com.mrdongshan.milvus.util;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.File;
import java.io.IOException;

/**
 * 图片转向量
 */
public class ImageToVector {

    public static void main(String[] args) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        // 加载预训练的深度学习模型
        String modelPath = "path/to/your/model.h5";
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(modelPath);

        // 加载图片并将其转换为向量
        String imagePath = "path/to/your/image.jpg";
        INDArray imageVector = imageToVector(imagePath, model);
        System.out.println("Image vector: " + imageVector);
    }

    public static INDArray imageToVector(String imagePath, MultiLayerNetwork model) throws IOException {
        // 加载图片
        NativeImageLoader imageLoader = new NativeImageLoader(224, 224, 3);
        INDArray image = imageLoader.asMatrix(new File(imagePath));

        // 对图片进行预处理（例如归一化）
        INDArray preprocessedImage = preprocessImage(image);

        // 使用模型将图片转换为向量
        INDArray imageVector = model.output(preprocessedImage);
        return imageVector;
    }

    public static INDArray preprocessImage(INDArray image) {
        // 对图片进行归一化处理
        INDArray normalizedImage = image.div(255.0);
        return normalizedImage;
    }
}
