package com.clearnight.oa.filemanage.bean;

/**
 * 文件上传数据封装
 * Created by ChenZhao on 2015/10/25 0025.
 */
public class FileUpload {
    /*当前第几块*/
    private Integer chunk;
    /*文件分割总块数*/
    private Integer chunks;

    /*----------------------setter、getter方法---------------------------*/
    public Integer getChunk() {
        return chunk;
    }
    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }
    public Integer getChunks() {
        return chunks;
    }
    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }
}
