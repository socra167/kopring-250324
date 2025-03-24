package com.example.upload.domain.base.genFile.genFile.entity;

import java.util.Objects;

import com.example.upload.global.app.AppConfig;
import com.example.upload.global.entity.BaseTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class GenFile extends BaseTime {

    private int fileNo;
    private String originalFileName;
    private String metadata;
    private String fileDateDir;
    private String fileExt;
    private String fileExtTypeCode;
    private String fileExtType2Code;
    private String fileName;
    private long fileSize;

    public GenFile(int fileNo, String originalFileName, String metadata, String fileDateDir, String fileExt,
        String fileExtTypeCode, String fileExtType2Code, String fileName, long fileSize) {
        this.fileNo = fileNo;
        this.originalFileName = originalFileName;
        this.metadata = metadata;
        this.fileDateDir = fileDateDir;
        this.fileExt = fileExt;
        this.fileExtTypeCode = fileExtTypeCode;
        this.fileExtType2Code = fileExtType2Code;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return AppConfig.getGenFileDirPath() + "/" + getModelName() + "/" + getTypeCodeAsStr() + "/" + fileDateDir + "/" + fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (getId() != null) return super.equals(o);

        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GenFile that = (GenFile) o;
        return fileNo == that.getFileNo() && Objects.equals(getTypeCodeAsStr(), that.getTypeCodeAsStr());
    }

    @Override
    public int hashCode() {
        if (getId() != null) return super.hashCode();

        return Objects.hash(super.hashCode(), getTypeCodeAsStr(), fileNo);
    }

    private String getOwnerModelName() {
        return this.getModelName().replace("GenFile", "");
    }

    public String getDownloadUrl() {
        return AppConfig.getSiteBackUrl() + "/" + getOwnerModelName() + "/genFile/download/" + getOwnerModelId() + "/" + fileName;
    }

    public String getPublicUrl() {
        return AppConfig.getSiteBackUrl() + "/gen/" + getModelName() + "/" + getTypeCodeAsStr() + "/" + fileDateDir + "/" + fileName;
    }


    abstract protected long getOwnerModelId();
    abstract protected String getTypeCodeAsStr();
}