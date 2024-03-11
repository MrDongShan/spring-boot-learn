package com.mrdongshan.milvus.service;

import com.mrdongshan.milvus.util.ImageToVector;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.SearchResults;
import io.milvus.param.R;
import io.milvus.param.dml.SearchParam;
import lombok.RequiredArgsConstructor;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageSearchService {
    public static void main(String[] args) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
        List<Float> floats = convertImageToVector(null);
    }

    private final MilvusServiceClient milvusServiceClient;

    public void searchImage(byte[] imageData) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
        // 将图片数据转换为向量
        List<Float> vector = convertImageToVector(imageData);

        // 使用Milvus进行搜索
        SearchParam searchParam = SearchParam.newBuilder()
                .withCollectionName("your_collection_name")
                .withTopK(10)
                .withVectors(vector)
                .build();
        R<SearchResults> search = milvusServiceClient.search(searchParam);
        SearchResults data = search.getData();
        // 返回搜索结果
//        return searchResult.getResultIds();
        System.err.println(data);
    }

    private static List<Float> convertImageToVector(byte[] imageData) throws IOException, UnsupportedKerasConfigurationException, InvalidKerasConfigurationException {
        // 实现图片到向量的转换逻辑
        // 加载预训练的深度学习模型
        String modelPath = "/Users/mrdongshan/Downloads/mobilenetv2.pth";
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(modelPath);

        // 加载图片并将其转换为向量
        String imagePath = "/Users/mrdongshan/Documents/Clelo.jpeg";
        INDArray imageVector = ImageToVector.imageToVector(imagePath, model);
        System.out.println("Image vector: " + imageVector);
        // ...
        return null;
    }
}
