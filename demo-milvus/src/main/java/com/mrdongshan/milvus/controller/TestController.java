package com.mrdongshan.milvus.controller;

import com.google.common.collect.Lists;
import com.mrdongshan.milvus.pojo.FaceArchive;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.DataType;
import io.milvus.grpc.GetIndexBuildProgressResponse;
import io.milvus.grpc.MutationResult;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import io.milvus.param.collection.*;
import io.milvus.param.dml.DeleteParam;
import io.milvus.param.index.CreateIndexParam;
import io.milvus.param.index.GetIndexBuildProgressParam;
import io.milvus.param.partition.CreatePartitionParam;
import io.milvus.param.partition.LoadPartitionsParam;
import io.milvus.param.partition.ReleasePartitionsParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final MilvusServiceClient milvusServiceClient;

    //判断集合是否已经存在
    @GetMapping("hasCollection")
    public void hasCollection(String collectionName) {
        R<Boolean> rest = milvusServiceClient.hasCollection(
                HasCollectionParam.newBuilder()
                        .withCollectionName(collectionName)
                        .build());
        System.err.println(rest);
    }

    //创建集合
    @GetMapping("createCollection")
    public void createCollection() {
        FieldType archiveId = FieldType.newBuilder()
                .withName(FaceArchive.Field.ARCHIVE_ID)
                .withDescription("主键id")
                .withDataType(DataType.Int64)
                .withPrimaryKey(true)
                .withAutoID(false)
                .build();
        FieldType orgId = FieldType.newBuilder()
                .withName(FaceArchive.Field.ORG_ID)
                .withDescription("组织id")
                .withDataType(DataType.Int32)
                .build();
        FieldType archiveFeature = FieldType.newBuilder()
                .withName(FaceArchive.Field.ARCHIVE_FEATURE)
                .withDescription("档案特征值")
                .withDataType(DataType.FloatVector)
                .withDimension(FaceArchive.FEATURE_DIM)
                .build();
        CreateCollectionParam createCollectionReq = CreateCollectionParam.newBuilder()
                .withCollectionName(FaceArchive.COLLECTION_NAME)
                .withDescription("档案集合")
                .withShardsNum(FaceArchive.SHARDS_NUM)
                .addFieldType(archiveId)
                .addFieldType(orgId)
                .addFieldType(archiveFeature)
                .build();
        R<RpcStatus> response = milvusServiceClient.createCollection(createCollectionReq);

        System.err.println(response);
    }


    /**
     * 创建分区
     * 这里我理解的意思就跟关系型数据库分表一样的,在插入数据时指定插入到哪个分区,查询的时候也一样,这样可以在查询的时候减少数据量
     *
     * @param collectionName 集合名称
     * @param partitionName  分区名称
     */
    @GetMapping("createPartition")
    public void createPartition(String collectionName, String partitionName) {
        R<RpcStatus> response = milvusServiceClient.createPartition(CreatePartitionParam.newBuilder()
                .withCollectionName(collectionName) //集合名称
                .withPartitionName(partitionName) //分区名称
                .build());
        System.err.println(response);
    }


    /**
     * 创建索引
     * 调用 create_index() 方法后，Milvus 会为后续新增向量自动构建索引的任务。每当新增数据量达到一个完整的 segment 时即触发这一任务，Milvus 为新插入的向量构建索引。
     * 新增向量的索引文件与前期构建的索引文件相互独立。
     */
    @GetMapping("createIndex")
    public R<RpcStatus> createIndex() {
        R<RpcStatus> response = milvusServiceClient.createIndex(CreateIndexParam.newBuilder()
                .withCollectionName(FaceArchive.COLLECTION_NAME)
                .withFieldName(FaceArchive.Field.ARCHIVE_FEATURE)
                .withIndexType(IndexType.IVF_FLAT)
                .withMetricType(MetricType.IP)
                //nlist 建议值为 4 × sqrt(n)，其中 n 指 segment 最多包含的 entity 条数。
                .withExtraParam("{\"nlist\":16384}")
                .withSyncMode(Boolean.FALSE)
                .build());
        System.err.println("createIndex-------------------->" + response);
        R<GetIndexBuildProgressResponse> idnexResp = milvusServiceClient.getIndexBuildProgress(
                GetIndexBuildProgressParam.newBuilder()
                        .withCollectionName(FaceArchive.COLLECTION_NAME)
                        .build());
        System.err.println("getIndexBuildProgress---------------------------->" + idnexResp);
        return response;
    }

    /**
     * 数据插入
     * 这里就我自己用的插入代码，因为是按组织ID分区的，所以每个组织分一个组，然后再批量插入，其中的向量值是通过虹软人脸识别SDK计算出来的特征值转换成List，
     * 因为虹软特征值本身就是归一化处理了的，只需要把字节转Float就行了
     */
    @GetMapping("insert")
    public boolean insert() {
        /*List<MilvusArchiveDto> data = new ArrayList<>();
        Map<Integer, List<MilvusArchiveDto>> map =
                data.stream().filter(item -> ArrayUtil.isNotEmpty(item.getArcsoftFeature())).collect(Collectors.groupingBy(MilvusArchiveDto::getOrgId));
        map.forEach((orgId, list) -> {
            //插入数据
            List<InsertParam.Field> fields = new ArrayList<>();
            List<Long> archiveIds = Lists.newArrayList();
            List<Integer> orgIds = Lists.newArrayList();
            List<List<Float>> floatVectors = Lists.newArrayList();
            for (MilvusArchiveDto dto : list) {
                archiveIds.add(dto.getArchiveId());
                orgIds.add(dto.getOrgId());
                //虹软特征值转Float向量
                floatVectors.add(MilvusUtil.arcsoftToFloat(dto.getArcsoftFeature()));
            }
            //档案ID
            fields.add(new InsertParam.Field(FaceArchive.Field.ARCHIVE_ID, DataType.Int64, archiveIds));
            //小区id
            fields.add(new InsertParam.Field(FaceArchive.Field.ORG_ID, DataType.Int32, orgIds));
            //特征值
            fields.add(new InsertParam.Field(FaceArchive.Field.ARCHIVE_FEATURE, DataType.FloatVector, floatVectors));
            //插入
            InsertParam insertParam = InsertParam.newBuilder()
                    .withCollectionName(FaceArchive.COLLECTION_NAME)
                    .withPartitionName(FaceArchive.getPartitionName(orgId))
                    .withFields(fields)
                    .build();
            R<MutationResult> insert = milvusClient.insert(insertParam);
            log.info("插入:{}", insert);
        });*/

//        milvusServiceClient.insert();
        return true;
    }

    //把集合加载到内存中(milvus查询前必须把数据加载到内存中)
    @GetMapping("loadCollection")
    public void loadCollection(String collectionName) {
        R<RpcStatus> response = milvusServiceClient.loadCollection(LoadCollectionParam.newBuilder()
                //集合名称
                .withCollectionName(collectionName)
                .build());
        System.err.println("loadCollection------------->" + response);
    }

    //加载分区数据
    @GetMapping("loadPartitions")
    public void loadPartitions(String collectionName, String partitionsName) {
        R<RpcStatus> response = milvusServiceClient.loadPartitions(
                LoadPartitionsParam
                        .newBuilder()
                        //集合名称
                        .withCollectionName(collectionName)
                        //需要加载的分区名称
                        .withPartitionNames(Lists.newArrayList(partitionsName))
                        .build()
        );
        System.err.println("loadCollection------------->" + response);
    }

    //释放集合(从内存中释放)
    @GetMapping("releaseCollection")
    public void releaseCollection(String collectionName) {
        R<RpcStatus> response = milvusServiceClient.releaseCollection(ReleaseCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .build());
        System.err.println("releaseCollection------------->" + response);
    }


    //释放分区
    @GetMapping("releasePartition")
    public void releasePartition(String collectionName, String partitionsName) {
        R<RpcStatus> response = milvusServiceClient.releasePartitions(ReleasePartitionsParam.newBuilder()
                .withCollectionName(collectionName)
                .addPartitionName(partitionsName)
                .build());
        System.err.println("releasePartition------------->" + response);
    }

    //删除数据
    public void deleteEntity(String collectionName, String partitionName, String expr) {
        R<MutationResult> response = milvusServiceClient.delete(
                DeleteParam.newBuilder()
                        //集合名称
                        .withCollectionName(collectionName)
                        //分区名称
                        .withPartitionName(partitionName)
                        //条件 如: id == 1
                        .withExpr(expr)
                        .build()
        );
        System.err.println("deleteEntity------------->" + response);
    }

    // 搜索
    /*public SearchTallestSimilarityDto searchTallestSimilarity(byte[] arcsoftFeature, Integer orgId) {
//        MilvusUtil milvusUtil = new MilvusUtil();
        List<Float> arcsoftToFloat = MilvusUtil.arcsoftToFloat(arcsoftFeature);
        List<List<Float>> list = new ArrayList<>();
        list.add(arcsoftToFloat);
        SearchParam.Builder builder = SearchParam.newBuilder()
                //集合名称
                .withCollectionName(FaceArchive.COLLECTION_NAME)
                //计算方式
                // 欧氏距离 (L2)
                // 内积 (IP)
                .withMetricType(MetricType.IP)
                //返回多少条结果
                .withTopK(1)
                //搜索的向量值
                .withVectors(list)
                //搜索的Field
                .withVectorFieldName(FaceArchive.Field.ARCHIVE_FEATURE)
                //https://milvus.io/cn/docs/v2.0.0/performance_faq.md
                .withParams("{\"nprobe\":512}");
        if (orgId != null) {
            //如果只需要搜索某个分区的数据,则需要指定分区
            builder
                    .withExpr(FaceArchive.Field.ORG_ID + " == " + orgId)
                    .withPartitionNames(Lists.newArrayList(FaceArchive.getPartitionName(orgId)));
        }
        R<SearchResults> search = milvusServiceClient.search(builder.build());
        if (search.getData() == null) return null;
        SearchResultsWrapper wrapper = new SearchResultsWrapper(search.getData().getResults());
        for (int i = 0; i < list.size(); ++i) {
            List<SearchResultsWrapper.IDScore> scores = wrapper.GetIDScore(i);
            if (scores.size() > 0) {
                System.err.println(scores);
                SearchResultsWrapper.IDScore idScore = scores.get(0);
                return new SearchTallestSimilarityDto(idScore.getLongID(), idScore.getScore());
            }
        }
        return null;
    }*/


}
